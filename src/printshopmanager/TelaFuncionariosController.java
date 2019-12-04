package printshopmanager;

import database.FuncionariosDAO;
import database.TelefonesFuncionariosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TelaFuncionariosController implements Initializable {

    @FXML
    private AnchorPane anpCadastro;

    @FXML
    private TextField txtCTPS;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtComplemento;

    @FXML
    private ComboBox<?> cboUF;

    @FXML
    private TextField txtRG;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtTelefone;

    @FXML
    private ListView<String> lstTelefones;

    @FXML
    private TextArea txaObs;

    @FXML
    private TextField txtCargo;

    @FXML
    void addTelefone(ActionEvent event) {
        this.lstTelefones.getItems().add(this.txtTelefone.getText());
        this.txtTelefone.setText("");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        int idCliente = this.func.getId(this.txtCPF.getText());
        ObservableList<String> telefones = lstTelefones.getItems();
        if (this.func.cadastrar(this.txtNome.getText(), this.txtCargo.getText(), this.txtCTPS.getText(), this.txtEndereco.getText(), this.txtBairro.getText(), this.txtCidade.getText(), String.valueOf(this.cboUF.getSelectionModel().getSelectedItem()), this.txtComplemento.getText(), this.txtCEP.getText(), this.txtEmail.getText(), this.txtRG.getText(), this.txtCPF.getText(), this.txaObs.getText())) {
            for (int i = 0; i < telefones.size(); i++) {
                this.telefoneFornec.cadastrar(telefones.get(i), idCliente);
            }
            alerta.alertInf("Cadastro Realizado com sucesso!", "O Fornecedor foi cadastrado com sucesso!");
        } else {
            alerta.alertError("Encontramos um problema!", "O Fornecedor não foi cadastrado!");
        }
        
        this.func.mostrar();

        this.limpar();
    }

    @FXML
    void limpar(ActionEvent event) {
        this.limpar();
    }

    private FuncionariosDAO func = new FuncionariosDAO();
    private TelefonesFuncionariosDAO telefoneFornec = new TelefonesFuncionariosDAO();
    private PrintAlert alerta = new PrintAlert();
    
    private void limpar() {
        this.lstTelefones.setItems(null);
        this.cboUF.getSelectionModel().select(-1);
        this.txaObs.setText("");
        for (int i = 0; i < this.anpCadastro.getChildren().size(); i++) {
            Node node = this.anpCadastro.getChildren().get(i);
            if (node instanceof TextField) {
                TextField field = (TextField) node;
                field.clear();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            PrintComboBox cbo = new PrintComboBox();
            this.cboUF.setItems(cbo.getUF());
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
