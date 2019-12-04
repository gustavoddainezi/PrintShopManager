
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FornecedoresDAO extends TabelaDAO{

    public FornecedoresDAO() {
        super("FORNECEDORES");
    }
    
    public boolean cadastrar(String nome, String razaoSocial, String tipo, String endereco, String bairro, String cidade, String uf, String complemento, String cep, String email, String cpfcnpj){
        String sql = "INSERT INTO FORNECEDORES VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.getMbd().preparedStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, razaoSocial);
            stmt.setString(3, tipo);
            stmt.setString(4, endereco);
            stmt.setString(5, bairro);
            stmt.setString(6, cidade);
            stmt.setString(7, uf);
            stmt.setString(8, complemento);
            stmt.setString(9, cep);
            stmt.setString(10, email);
            stmt.setString(11, cpfcnpj);
            return this.getMbd().executarAcao(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getId(String cpfcnpj) {
        String sql = "Select (id) from FORNECEDORES WHERE cpfcnpj = ?";
        try {
            PreparedStatement stmt = this.getMbd().preparedStatement(sql);
            stmt.setString(1, cpfcnpj);
            ResultSet rs = this.getMbd().execConsulta(stmt);
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    protected String getSqlTabela() {
        String sqlTabela = "CREATE TABLE FORNECEDORES ( "
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
            + "nome varchar(60) NOT NULL, \n"
            + "razaoSocial varchar(60), \n"
            + "tipo varchar(15) NOT NULL CONSTRAINT tipoFornecedor CHECK (tipo IN ('Pessoa Física', 'Pessoa Jurídica')), \n"
            + "endereco varchar(120) NOT NULL, \n"
            + "bairro varchar(60) NOT NULL, \n"
            + "cidade varchar(60) NOT NULL, \n"
            + "uf varchar(27) NOT NULL, \n"
            + "complemento varchar(60), \n"
            + "CEP varchar(9), \n"
            + "email varchar(60), \n"
            + "cpfcnpj varchar(18) NOT NULL, \n"
            + "CONSTRAINT idFornecedor PRIMARY KEY(id) "
            + ")";
        return sqlTabela;
    }
    
}
