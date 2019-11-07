
package printshopmanager;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrintShopManager extends Application {
    
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
    
    private String fxmlLoader = "TelaLogin.fxml";
    
    public void setLoader(String loader){
        this.fxmlLoader = loader;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(this.fxmlLoader));
        
        Scene scene = new Scene(root);
        
        dragDrop(stage, root);
        stage.centerOnScreen();
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

	stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}