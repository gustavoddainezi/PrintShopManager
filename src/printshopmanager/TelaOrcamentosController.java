package printshopmanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cor;
import model.Produto;

public class TelaOrcamentosController implements Initializable {
    @FXML
    private TableView<Produto> tvProdutos;
    
    @FXML
    private TableView<Cor> tvCor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initTabelaProdutos();
        //this.initTabelaCor();
    }
    
    public void initTabelaProdutos(){
        TableColumn<Produto, Integer> colCodItem = new TableColumn("Código Item");
        colCodItem.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCodItem()).asObject());
        
        TableColumn<Produto, String> colTipoItem = new TableColumn("Tipo Item");
        colTipoItem.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoItem()));
        
        TableColumn<Produto, String> colDescricao = new TableColumn("Descrição");
        colDescricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));
        
        TableColumn<Produto, String> colTamanho = new TableColumn("Tamanho");
        colTamanho.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTamanho()));
        
        TableColumn<Produto, Integer> colQuantidade = new TableColumn("Quantidade");
        colQuantidade.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantidade()).asObject());
        
        TableColumn<Produto, Double> colPrecoUnit = new TableColumn("Preço Unitário");
        colPrecoUnit.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecoUnit()).asObject());
        
        TableColumn<Produto, Integer> colEstoque = new TableColumn("Estoque");
        colEstoque.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getEstoque()).asObject());
        
        TableColumn<Produto, String> colFornecedor = new TableColumn("Fornecedor");
        colFornecedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFornecedor()));
        
        this.tvProdutos.getColumns().addAll(colCodItem, colTipoItem, colDescricao, colTamanho, colQuantidade, colPrecoUnit, colEstoque, colFornecedor);
        
        this.tvProdutos.getItems().add(new Produto(1, "Flayer", "Papel...", "33x48", 50000, 0.3, 5000, "Casa de Papel"));

    }
    
    public void initTabelaCor(){
        TableColumn<Cor, Integer> colCodTinta = new TableColumn("Código Tinta");
        colCodTinta.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCod()).asObject());
        
        TableColumn<Cor, String> colCor = new TableColumn("Cor");
        colCor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCor()));
        
        TableColumn<Cor, String> colPreco = new TableColumn("Preço");
        colPreco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPreco()));
        
        this.tvCor.getColumns().addAll(colCodTinta, colCor, colPreco);
        
        this.tvCor.getItems().add(new Cor(1, "Magento", "R$ 0,04"));
        this.tvCor.getItems().add(new Cor(2, "Ciano", "R$ 0,40"));
        this.tvCor.getItems().add(new Cor(3, "Amarelo", "R$ 0,20"));
    }
}
