
package printshopmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrintComboBox {
    
    public ObservableList getPessoas(){
        ObservableList<String> options = FXCollections.observableArrayList(
            "Pessoa Física",
            "Pessoa Jurídica"
        );
        return options;
        
    }
    
    public ObservableList getUF(){
        ObservableList<String> options = FXCollections.observableArrayList(
            "Acre (AC)",
            "Alagoas (AL)",
            "Amapá (AP)",
            "Amazonas (AM)",
            "Bahia (BA)",
            "Ceará (CE)",
            "Distrito Federal (DF)",
            "Espírito Santo (ES)",
            "Goiás (GO)",
            "Maranhão (MA)",
            "Mato Grosso (MT)",
            "Mato Grosso do Sul (MS)",
            "Minas Gerais (MG)",
            "Pará (PA)",
            "Paraíba (PB)",
            "Paraná (PR)",
            "Pernambuco (PE)",
            "Piauí (PI)",
            "Rio de Janeiro (RJ)",
            "Rio Grande do Norte (RN)",
            "Rio Grande do Sul (RS)",
            "Rondônia (RO)",
            "Roraima (RR)",
            "Santa Catarina (SC)",
            "São Paulo (SP)",
            "Sergipe (SE)",
            "Tocantins (TO)"
        );
        return options;
    }
}
