package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDAO extends TabelaDAO {

    public ClientesDAO() {
        super("CLIENTES");
    }

    public boolean cadastrar(String nome, String tipo, String nomeFantasia, String endereco, String bairro, String cidade, String uf, String complemento, String cep, String email, String cpfcnpj, String observacao) {
        String sql = "INSERT INTO CLIENTES VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.getMbd().preparedStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            stmt.setString(3, nomeFantasia);
            stmt.setString(4, endereco);
            stmt.setString(5, bairro);
            stmt.setString(6, cidade);
            stmt.setString(7, uf);
            stmt.setString(8, complemento);
            stmt.setString(9, cep);
            stmt.setString(10, email);
            stmt.setString(11, cpfcnpj);
            stmt.setString(12, observacao);
            return this.getMbd().executarAcao(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getId(String cpfcnpj) {
        String sql = "Select (id) from CLIENTES WHERE cpfcnpj = ?";
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
        String sqlTabela = "CREATE TABLE CLIENTES ( \n"
                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
                + "nome varchar(60) NOT NULL, \n"
                + "tipo varchar(15) NOT NULL CONSTRAINT tipoCliente CHECK (tipo IN ('Pessoa Física', 'Pessoa Jurídica')), \n"
                + "nomeFantasia varchar(60), \n"
                + "endereco varchar(120) NOT NULL, \n"
                + "bairro varchar(60) NOT NULL, \n"
                + "cidade varchar(60) NOT NULL, \n"
                + "uf varchar(27) NOT NULL, \n"
                + "complemento varchar(60), \n"
                + "CEP varchar(9), \n"
                + "email varchar(60), \n"
                + "cpfcnpj varchar(18) NOT NULL, \n"
                + "observacao varchar(200), \n"
                + "CONSTRAINT idCliente PRIMARY KEY(id) \n"
                + " )";
        return sqlTabela;
    }

}
