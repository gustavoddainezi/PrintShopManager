
package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelefonesFornecedoresDAO extends TabelaDAO {

    public TelefonesFornecedoresDAO() {
        super("TELEFONESFORNECEDORES");
    }

    public boolean cadastrar(String telefone, int idCliente){
        String sql = "INSERT INTO TELEFONESFORNECEDORES VALUES (DEFAULT, ?, ?)";
        
        try {
            PreparedStatement stmt = this.getMbd().preparedStatement(sql);
            stmt.setString(1, telefone);
            stmt.setInt(2, idCliente);
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    protected String getSqlTabela() {
        String sqlTabela = "CREATE TABLE TELEFONESFORNECEDORES ( \n"
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
            + "numero varchar(20) NOT NULL, \n"
            + "idFornecedor INTEGER NOT NULL, \n"
            + "CONSTRAINT idTelefoneFornecedor PRIMARY KEY(id), \n"
            + "CONSTRAINT idFornecedorFKTelefone FOREIGN KEY(idFornecedor) REFERENCES FORNECEDORES(id)"
            + " )";
        return sqlTabela;
    }
    
}
