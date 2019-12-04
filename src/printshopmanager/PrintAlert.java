
package printshopmanager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class PrintAlert {
    
    private Alert alertaError = new Alert(Alert.AlertType.ERROR);
    private Alert alertaInf = new Alert(Alert.AlertType.INFORMATION);
    
    public void alertInf(String headerText, String text){
        alertaInf.setTitle("Mensagem de Confirmação");
        alertaInf.setHeaderText(headerText);
        alertaInf.setContentText(text);
        alertaInf.showAndWait();
    }
    
    public void alertError(String headerText, String text){
        alertaError.setTitle("Mensagem de erro");
        alertaError.setHeaderText(headerText);
        alertaError.setContentText(text);
        alertaError.showAndWait();
    }
    
    public boolean alertConfirm(String text){
        Alert alertaConfirm = new Alert(AlertType.CONFIRMATION, text, ButtonType.YES, ButtonType.NO);
        alertaConfirm.showAndWait();
        return alertaConfirm.getResult() == ButtonType.YES;
    }
    
}
