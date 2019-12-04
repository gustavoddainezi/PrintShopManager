
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelefonesFuncionariosDAO extends TabelaDAO {

    public TelefonesFuncionariosDAO() {
        super("TELEFONESFUNCIONARIOS");
    }
    
    public boolean cadastrar(String telefone, int idCliente){
        String sql = "INSERT INTO TELEFONESFUNCIONARIOS VALUES (DEFAULT, ?, ?)";
        
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
        String sqlTabela = "CREATE TABLE TELEFONESFUNCIONARIOS ( \n"
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
            + "numero varchar(20) NOT NULL, \n"
            + "idFuncionario INTEGER NOT NULL, \n"
            + "CONSTRAINT idTelefoneFuncionario PRIMARY KEY(id), \n"
            + "CONSTRAINT idFuncionarioFKTelefone FOREIGN KEY(idFuncionario) REFERENCES FUNCIONARIOS(id)"
            + " )";
        return sqlTabela;
    }
    
}
