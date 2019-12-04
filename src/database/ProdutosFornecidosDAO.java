
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosFornecidosDAO extends TabelaDAO{

    public ProdutosFornecidosDAO() {
        super("PRODUTOSFORNECIDOS");
    }
    
    public boolean cadastrar(String telefone, int idCliente){
        String sql = "INSERT INTO PRODUTOSFORNECIDOS VALUES (DEFAULT, ?, ?)";
        
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
        String sqlTabela = "CREATE TABLE PRODUTOSFORNECIDOS ( \n"
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
            + "nome varchar(60) NOT NULL, \n"
            + "idFornecedor INTEGER NOT NULL, \n"
            + "CONSTRAINT idProdutosFornecidos PRIMARY KEY(id), \n"
            + "CONSTRAINT idFornecedorFKProdutosFornecidos FOREIGN KEY(idFornecedor) REFERENCES FORNECEDORES(id)"
            + " )";
        return sqlTabela;
    }
    
}
