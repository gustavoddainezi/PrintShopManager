package printshopmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class TelaFerramentasController implements Initializable {

    @FXML
    void abrirExcel(MouseEvent event) throws IOException {
        Runtime.getRuntime().exec("EXCEL.EXE");
    }

    @FXML
    void abrirWord(MouseEvent event) throws IOException {
        Runtime.getRuntime().exec("WINWORD.EXE");
        
    }

    @FXML
    void abrirNotepad(MouseEvent event) throws IOException {
        Runtime.getRuntime().exec("notepad");
    }
    
    @FXML
    void abrirCalc(MouseEvent event) throws IOException {
        Runtime.getRuntime().exec("calc");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}