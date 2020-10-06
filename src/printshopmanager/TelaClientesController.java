package printshopmanager;

import database.ClientesDAO;
import database.TelefonesClientesDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cliente;
import model.Cor;
import model.Produto;

public class TelaClientesController implements Initializable {

    @FXML
    private AnchorPane anpCadastro;

    @FXML
    private TextField txtNomeFantasia;

    @FXML
    private ComboBox<?> cboTipo;

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
    private TextArea txaObs;

    @FXML
    private ListView<String> lstTelefones;

    @FXML
    void addTelefone(ActionEvent event) {
        this.lstTelefones.getItems().add(this.txtTelefone.getText());
        this.txtTelefone.setText("");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String cpfcnpj;
        String nomeFantasia = null;
        if (this.cboTipo.getSelectionModel().getSelectedIndex() == 0) {
            cpfcnpj = this.txtCPF.getText();
        } else {
            cpfcnpj = this.txtCNPJ.getText();
            nomeFantasia = this.txtNomeFantasia.getText();
        }

        int idCliente = this.client.getId(cpfcnpj);
        ObservableList<String> telefones = lstTelefones.getItems();

        if (this.client.cadastrar(this.txtNome.getText(), String.valueOf(this.cboTipo.getSelectionModel().getSelectedItem()), nomeFantasia, this.txtEndereco.getText(), this.txtBairro.getText(), this.txtCidade.getText(), String.valueOf(this.cboUF.getSelectionModel().getSelectedItem()), this.txtComplemento.getText(), this.txtCEP.getText(), this.txtEmail.getText(), cpfcnpj, this.txaObs.getText())) {
            for (int i = 0; i < telefones.size(); i++) {
                this.telefoneClient.cadastrar(telefones.get(i), idCliente);
            }
            alerta.alertInf("Cadastro Realizado com sucesso!", "O Cliente foi cadastrado com sucesso!");
        } else {
            alerta.alertError("Encontramos um problema!", "O cliente não foi cadastrado!");
        }
        
        this.client.mostrar();

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
            this.txtNomeFantasia.setEditable(false);
            this.txtNomeFantasia.setText("");
            this.txtCPF.setEditable(true);
            this.txtCNPJ.getStyleClass().add(styleClass);
            this.txtNomeFantasia.getStyleClass().add(styleClass);
            this.txtCPF.getStyleClass().remove(styleClass);
        } else {
            this.txtCPF.setEditable(false);
            this.txtCPF.setText("");
            this.txtCNPJ.setEditable(true);
            this.txtNomeFantasia.setEditable(true);
            this.txtCPF.getStyleClass().add(styleClass);
            this.txtCNPJ.getStyleClass().remove(styleClass);
            this.txtNomeFantasia.getStyleClass().remove(styleClass);
        }
    }

    private ClientesDAO client = new ClientesDAO();
    private TelefonesClientesDAO telefoneClient = new TelefonesClientesDAO();
    private PrintAlert alerta = new PrintAlert();

    private void limpar() {
        this.txaObs.setText("");
        ObservableList<String> options = FXCollections.observableArrayList();
        this.lstTelefones.setItems(options);
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
    
    @FXML
    private TableView<Cliente> tvProdutos;
    
    public void initTabelaProdutos(){
        TableColumn<Cliente, Integer> colCod = new TableColumn("Código Cliente");
        colCod.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCod()).asObject());
        
        TableColumn<Cliente, String> colNome = new TableColumn("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        
        TableColumn<Cliente, String> colTipo = new TableColumn("Tipo de Cliente");
        colTipo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoCliente()));
        
        TableColumn<Cliente, String> colCpf = new TableColumn("CPF / CNPJ");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        TableColumn<Cliente, String> colFan = new TableColumn("Nome Fantasia");
        colFan.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomeFantasia()));
        
        TableColumn<Cliente, String> colEnd = new TableColumn("Endereço");
        colEnd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));
        
        TableColumn<Cliente, String> colBairro = new TableColumn("Bairro");
        colBairro.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBairro()));
        
        TableColumn<Cliente, String> colCep = new TableColumn("CEP");
        colCep.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCep()));
        
        TableColumn<Cliente, String> colCidade = new TableColumn("Cidade");
        colCidade.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCidade()));
     
        TableColumn<Cliente, String> colUF = new TableColumn("UF");
        colUF.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUf()));
     
        TableColumn<Cliente, String> colComplemento = new TableColumn("Complemento");
        colComplemento.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getComplemento()));
     
        
        this.tvProdutos.getColumns().addAll(colCod, colNome, colTipo, colCpf, colFan, colCep, colEnd, colBairro, colCidade, colUF, colComplemento);
        Cliente c = new Cliente();
        c.setCod(1);
        c.setNome("Ana Caroline Freitas");
        c.setTipoCliente("Fisíco");
        c.setCpf("569.145.654-45");
        c.setEndereco("R. Washington Luís, 17");
        c.setNomeFantasia("");
        c.setBairro("Vila Santa Lusia");
        c.setCep("08596-166");
        c.setCidade("Ferraz de Vasconcelos");
        c.setUf("SP");
        c.setComplemento("2° Andar");
        this.tvProdutos.getItems().add(c);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initTabelaProdutos();
        try {
            PrintComboBox cbo = new PrintComboBox();
            this.cboTipo.setItems(cbo.getPessoas());
            this.cboUF.setItems(cbo.getUF());
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
