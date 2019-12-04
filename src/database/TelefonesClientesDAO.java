
package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelefonesClientesDAO extends TabelaDAO {

    public TelefonesClientesDAO() {
        super("TELEFONESCLIENTES");
    }
    
    public boolean cadastrar(String telefone, int idCliente){
        String sql = "INSERT INTO TELEFONESCLIENTES VALUES (DEFAULT, ?, ?)";
        
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
        String sqlTabela = "CREATE TABLE TELEFONESCLIENTES ( \n"
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
            + "numero varchar(20) NOT NULL, \n"
            + "idCliente INTEGER NOT NULL, \n"
            + "CONSTRAINT idTelefoneCliente PRIMARY KEY(id), \n"
            + "CONSTRAINT idClienteFKTelefone FOREIGN KEY(idCliente) REFERENCES CLIENTES(id)"
            + " )";
        return sqlTabela;
    }
    
}
