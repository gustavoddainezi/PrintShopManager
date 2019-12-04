package printshopmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane main;

    @FXML
    private Label firstPiece;

    @FXML
    private Label secondPiece;

    @FXML
    private Label thirdPiece;

    @FXML
    private VBox menu;

    @FXML
    void clickedBackup(Event event) {
        /*loadScreen("TelaBackup");*/
    }

    @FXML
    void clickedClientes(Event event) {
        loadScreen("TelaClientes");
    }

    @FXML
    void clickedEstoque(Event event) {
        loadScreen("TelaEstoque");
    }

    @FXML
    void clickedFerramentas(Event event) {
        loadScreen("TelaFerramentas");
    }

    @FXML
    void clickedFornecedores(Event event) {
        loadScreen("TelaFornecedores");
    }

    @FXML
    void clickedFuncionarios(Event event) {
        loadScreen("TelaFuncionarios");
    }

    @FXML
    void clickedOrcamento(Event event) {
        loadScreen("TelaOrcamentos");
    }

    @FXML
    void clickedProdutos(Event event) {
        loadScreen("TelaProdutos");
    }

    @FXML
    void clickedRelatorios(Event event) {
        /*loadScreen("TelaRelatorios");*/
    }

    @FXML
    void clickedSair(Event event) {
        PrintAlert alerta = new PrintAlert();
        if (alerta.alertConfirm("Deseja realmente fazer Logoff?")) {
            try {
                PrintShopManager login = new PrintShopManager();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                login.setLoader("TelaLogin.fxml");
                login.start(new Stage());
            } catch (Exception e) {
                System.err.println(" " + e.getStackTrace());
            }
        }
    }

    @FXML
    void clickedSobre(Event event) {
        /*loadScreen("TelaSobre");*/
    }

    @FXML
    void clickedHamburger(ActionEvent event) {
        Duration duracao = new Duration(300);
        int xMenu = 0;
        int xMain = 260;
        int ang = 40;
        int xf = -10;
        int yf = 5;
        int xt = 2;
        int yt = -15;
        int fValue = 1;
        int tValue = 0;
        if (this.isMenu) {
            xMenu = -270;
            xMain = 0;
            ang *= -1;
            xf = 0;
            yf = 0;
            xt = 0;
            yt = 0;
            fValue = 0;
            tValue = 1;
        }
        transition(translateTransition(duracao, this.menu, xMenu, 0));
        transition(rotateHamburger(duracao, ang, firstPiece));
        transition(translateTransition(duracao, firstPiece, xf, yf));
        ang *= -1;
        transition(rotateHamburger(duracao, ang, thirdPiece));
        transition(translateTransition(duracao, thirdPiece, xt, yt));
        transition(fadeTransition(duracao, secondPiece, fValue, tValue));
        transition(translateTransition(duracao, main, xMain, 0));
        this.isMenu = !this.isMenu;
    }
    private boolean isMenu = false;

    private void loadScreen(String nameScreen) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nameScreen + ".fxml"));
            main.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private RotateTransition rotateHamburger(Duration duracao, int grau, Node node) {
        RotateTransition rotateTransition = new RotateTransition(duracao, node);
        rotateTransition.setByAngle(grau);
        return rotateTransition;
    }

    private TranslateTransition translateTransition(Duration duracao, Node node, int toX, int toY) {
        TranslateTransition translateTransition = new TranslateTransition(duracao, node);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);
        return translateTransition;
    }

    private FadeTransition fadeTransition(Duration duracao, Node node, int fValue, int tValue) {
        FadeTransition fadeTransition = new FadeTransition(duracao, node);
        fadeTransition.setFromValue(fValue);
        fadeTransition.setToValue(tValue);
        return fadeTransition;
    }

    private void transition(Transition t) {
        t.setAutoReverse(true);
        t.setCycleCount(1);
        t.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.menu.setTranslateX(-270);
        this.loadScreen("TelaOrcamentos");
    }

}
