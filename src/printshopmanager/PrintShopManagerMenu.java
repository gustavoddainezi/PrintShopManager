
package printshopmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrintShopManagerMenu extends Application {
    
    private double x, y;
    private void dragDrop(Stage stage, Parent root) throws Exception {
        
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        dragDrop(stage, root);
        stage.centerOnScreen();
        
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
