package printshopmanager;

import database.FornecedoresDAO;
import database.ProdutosFornecidosDAO;
import database.TelefonesFornecedoresDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TelaFornecedoresController implements Initializable {

    @FXML
    private AnchorPane anpCadastro;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnCadastrar;

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
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnAddTelefone;

    @FXML
    private TextField txtRazaoSocial;

    @FXML
    private ComboBox<?> cboTipo;

    @FXML
    private TextField txtProdutosFornecidos;

    @FXML
    private Button btnAddProdutosFornecidos;

    @FXML
    private ListView<String> lstTelefones;

    @FXML
    private ListView<String> lstProdutosFornecidos;

    @FXML
    void addProdutosFornecidos(ActionEvent event) {
        this.lstProdutosFornecidos.getItems().add(this.txtProdutosFornecidos.getText());
        this.txtProdutosFornecidos.setText("");
    }

    @FXML
    void addTelefone(ActionEvent event) {
        this.lstTelefones.getItems().add(this.txtTelefone.getText());
        this.txtTelefone.setText("");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String cpfcnpj;
        String razaoSocial = null;
        if (this.cboTipo.getSelectionModel().getSelectedIndex() == 0) {
            cpfcnpj = this.txtCPF.getText();
        } else {
            cpfcnpj = this.txtCNPJ.getText();
            razaoSocial = this.txtRazaoSocial.getText();
        }

        int idCliente = this.fornec.getId(cpfcnpj);
        ObservableList<String> telefones = lstTelefones.getItems();
        ObservableList<String> produtosfornecidos = lstProdutosFornecidos.getItems();

        if (this.fornec.cadastrar(this.txtNome.getText(), razaoSocial, String.valueOf(this.cboTipo.getSelectionModel().getSelectedItem()), this.txtEndereco.getText(), this.txtBairro.getText(), this.txtCidade.getText(), String.valueOf(this.cboUF.getSelectionModel().getSelectedItem()), this.txtComplemento.getText(), this.txtCEP.getText(), this.txtEmail.getText(), cpfcnpj)) {
            for (int i = 0; i < telefones.size(); i++) {
                this.telefoneFornec.cadastrar(telefones.get(i), idCliente);
            }

            for (int i = 0; i < produtosfornecidos.size(); i++) {
                this.produtosFornecidos.cadastrar(produtosfornecidos.get(i), idCliente);
            }
            alerta.alertInf("Cadastro Realizado com sucesso!", "O Fornecedor foi cadastrado com sucesso!");
        } else {
            alerta.alertError("Encontramos um problema!", "O Fornecedor não foi cadastrado!");
        }
        
        this.fornec.mostrar();

        this.limpar();
    }

    @FXML
    void limpar(ActionEvent event) {
        this.limpar();
    }

    @FXML
    void selectedTipo(ActionEvent event) {
        String styleClass = "font-disabled";
        if (this.cboTipo.getSelectionModel().getSelectedIndex() == 0) {
            this.txtCNPJ.setEditable(false);
            this.txtCNPJ.setText("");
            this.txtRazaoSocial.setEditable(false);
            this.txtRazaoSocial.setText("");
            this.txtNome.setPromptText("Nome Completo");
            this.txtCPF.setEditable(true);
            this.txtCNPJ.getStyleClass().add(styleClass);
            this.txtRazaoSocial.getStyleClass().add(styleClass);
            this.txtCPF.getStyleClass().remove(styleClass);
        } else {
            this.txtCPF.setEditable(false);
            this.txtCPF.setText("");
            this.txtCNPJ.setEditable(true);
            this.txtRazaoSocial.setEditable(true);
            this.txtNome.setPromptText("Nome Fantasia");
            this.txtCPF.getStyleClass().add(styleClass);
            this.txtCNPJ.getStyleClass().remove(styleClass);
            this.txtRazaoSocial.getStyleClass().remove(styleClass);
        }
    }

    private FornecedoresDAO fornec = new FornecedoresDAO();
    private TelefonesFornecedoresDAO telefoneFornec = new TelefonesFornecedoresDAO();
    private ProdutosFornecidosDAO produtosFornecidos = new ProdutosFornecidosDAO();
    private PrintAlert alerta = new PrintAlert();

    private void limpar() {
        this.lstTelefones.setItems(null);
        this.lstProdutosFornecidos.setItems(null);
        this.cboTipo.getSelectionModel().select(-1);
        this.cboUF.getSelectionModel().select(-1);
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
            this.cboTipo.setItems(cbo.getPessoas());
            this.cboUF.setItems(cbo.getUF());
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
