package printshopmanager;

import database.Admin;
import database.ClientesDAO;
import database.FornecedoresDAO;
import database.FuncionariosDAO;
import database.ProdutosFornecidosDAO;
import database.TelefonesClientesDAO;
import database.TelefonesFornecedoresDAO;
import database.TelefonesFuncionariosDAO;
import database.UsuariosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaLoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private HBox hypForgetU;

    @FXML
    private Text hypForgetP;

    // Instância da classe de alerta de mensagens ao usuário
    PrintAlert alerta = new PrintAlert();
    // Váriável que recebe o código de recuperação
    private static String cod;
    // Instância da classe UsuariosDAO
    private UsuariosDAO user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Continuação da instância ao inicializar aplicação
        Admin a = new Admin();
        this.user = new UsuariosDAO();
        ClientesDAO c = new ClientesDAO();
        TelefonesClientesDAO tc = new TelefonesClientesDAO();
        FuncionariosDAO func = new FuncionariosDAO();
        TelefonesFuncionariosDAO tfunc = new TelefonesFuncionariosDAO();
        FornecedoresDAO fornec = new FornecedoresDAO();
        TelefonesFornecedoresDAO tfornec = new TelefonesFornecedoresDAO();
        ProdutosFornecidosDAO pf = new ProdutosFornecidosDAO();
        
    }

    // Evento de botão login
    @FXML
    void login(ActionEvent event) {
        // Tratamento de erro
        try {
            // Recebe os valores e os atribui nas variáveis
            String login = this.txtUser.getText();
            String senha = this.txtPass.getText();
            // Verifica as credênciais com o Banco de Dados
            if (this.user.login(login, senha)) {
                PrintShopManager menu = new PrintShopManager();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                menu.setLoader("TelaPrincipal.fxml");
                menu.start(new Stage());
            } else {
                alerta.alertError("Encontramos um problema!", "O login ou senha estão incorretos!");
            }
        } catch (Exception e) {
            System.err.println(" " + e.getStackTrace());
        }
    }
}
