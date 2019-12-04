
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionariosDAO extends TabelaDAO {

    public FuncionariosDAO() {
        super("FUNCIONARIOS");
    }
    
    public boolean cadastrar(String nome, String cargo, String ctps, String endereco, String bairro, String cidade, String uf, String complemento, String cep, String email, String rg, String cpf, String observacao) {
        String sql = "INSERT INTO FUNCIONARIOS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.getMbd().preparedStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cargo);
            stmt.setString(3, ctps);
            stmt.setString(4, endereco);
            stmt.setString(5, bairro);
            stmt.setString(6, cidade);
            stmt.setString(7, uf);
            stmt.setString(8, complemento);
            stmt.setString(9, cep);
            stmt.setString(10, email);
            stmt.setString(11, rg);
            stmt.setString(12, cpf);
            stmt.setString(13, observacao);
            return this.getMbd().executarAcao(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getId(String cpfcnpj) {
        String sql = "Select (id) from FUNCIONARIOS WHERE cpf = ?";
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
        String sqlTabela = "CREATE TABLE FUNCIONARIOS ( \n"
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
            + "nome varchar(40), \n"
            + "cargo varchar(20), \n"
            + "ctps varchar(20), \n"
            + "endereco varchar(60), \n"
            + "bairro varchar(50), \n"
            + "cidade varchar(30), \n"
            + "uf varchar(20), \n"
            + "complemento varchar(60), \n"
            + "cep varchar(9), \n"
            + "email varchar(40), \n"
            + "rg varchar(16), \n"
            + "cpf varchar(14), \n"
            + "observacao varchar(200), \n"
            + "CONSTRAINT ifFuncionario PRIMARY KEY(id)"
            + ")";
        return sqlTabela;
    }
    
}
