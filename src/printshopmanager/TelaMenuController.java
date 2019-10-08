
package printshopmanager;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class TelaMenuController implements Initializable {
    
    @FXML
    private AnchorPane main;
    
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane anchorDrag;
    
    private void loadScreen(int i){
        String[] nameScreen = {"TelaOrcamentos", "TelaFuncionarios","TelaProdutos", "TelaFornecedores", ""};
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nameScreen[i]+".fxml"));
            main.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(TelaMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("ContentDrawerMenu.fxml"));
            drawer.setSidePane(vbox);
            for(int i = 0; i < vbox.getChildren().size(); i++) {
                int j = i;
                vbox.getChildren().get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    loadScreen(j);
                });
            }
            
            HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
            
                if(drawer.isOpened()){
                    drawer.close();
                    anchorDrag.setId("close");
                }else{
                    drawer.open();
                    anchorDrag.setId("open");
                }  
            });
        } catch (IOException ex) {
            Logger.getLogger(TelaMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
