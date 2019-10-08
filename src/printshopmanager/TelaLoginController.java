
package printshopmanager;

import database.ManipularBancoDados;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaLoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Continuação da instância ao inicializar aplicação
        this.mbd = ManipularBancoDados.getInstancia();
        // Chama o método e insere os dados na tabela usuários
        this.inserirDadosTabela();
    }
    
    // Instância da classe de alerta de mensagens ao usuário
    Alert alerta = new Alert(AlertType.ERROR);
    // Váriável que recebe o código de recuperação
    private static String cod;
    // Instância do Banco de Dados
    private ManipularBancoDados mbd;
    
    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Button btnLogin;

    @FXML
    private HBox hypForgetU;

    @FXML
    private Text hypForgetP;
    
    double x = 0, y = 0;
    
    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
    
    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    // Evento de botão login
    @FXML
    void login(ActionEvent event) {
        // Tratamento de erro
        try{
            // Recebe os valores e os atribui nas variáveis
            String login = valueOf(this.txtUser.getText());
            String senha = valueOf(this.txtPass.getText());
            
            // Variável de consulta e pesquisa
            String q = "SELECT * FROM USUARIOS";
            ResultSet rs = this.mbd.execConsulta(q);

            // Enquanto houver valor no ResultSet o loop continua
            while(rs.next()){
                String logins = rs.getString("login");
                String senhas = valueOf(rs.getString("senha"));
                
                // Verifica as credênciais com o Banco de Dados
                if ((senhas.equals(senha) || senha.equals(this.cod)) && logins.equals(login)) {
                    PrintShopManager menu = new PrintShopManager();
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    menu.setLoader("TelaMenu.fxml");
                    menu.start(new Stage());
                    break;
                }
                else{
                    alerta.setTitle("Mensagem de erro");
                    alerta.setHeaderText("Encontramos um problema!");
                    alerta.setContentText("O login ou senha estão incorretos!");
                    alerta.showAndWait();
                    break;
                }
            }
        }catch(Exception e){
                System.err.println(" " + e.getStackTrace());
                }
        finally{
            
        }
    }
    
    // Método insere os dados na tabela USUARIOS do Banco de Dados
    private void inserirDadosTabela(){
        // Credênciais do usuário
        String log = "admin";
        int sen = 1234;
            
        // Código sql de inserção de dados
        String sql = "INSERT INTO USUARIOS VALUES(DEFAULT, '" + log + "', " + sen + ")";
        
        // Mostra no console a linha de comando preenchida
        System.out.println(sql);
            
        // Cadastra e verifica o usuário retornando mensagem de êxito ou erro
        if (this.mbd.executarAcao(sql)) {
            System.out.println("Usuário cadastrado com sucesso!");
        }
        else{
            System.out.println("Erro ao cadastrar usuário");
        }
    }
}