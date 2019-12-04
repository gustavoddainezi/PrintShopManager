package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDAO extends TabelaDAO {

    // Váriável que recebe o código de recuperação
    private static String codRec;

    public UsuariosDAO() {
        super("USUARIOS");
        this.inserirUsuario("admin", "1234");
    }
    
    public boolean login(String login, String senha) {

        // Tratamento de erro
        try {
            // Variável de consulta e pesquisa
            String sql = "SELECT login, senha FROM USUARIOS";
            ResultSet rs = this.getMbd().execConsulta(sql);

            // Enquanto houver valor no ResultSet o loop continua
            while (rs.next()) {
                String logins = rs.getString("login");
                String senhas = rs.getString("senha");

                // Verifica as credênciais com o Banco de Dados
                if ((senhas.equals(senha) || senha.equals(this.codRec)) && logins.equals(login)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println(" " + e.getStackTrace());
        } finally {
            this.getMbd().fecharConexao();
        }
        return false;
    }

    // Método insere os dados na tabela USUARIOS do Banco de Dados
    private void inserirUsuario(String login, String senha) {
        try {
            // Código sql de inserção de dados
            String sql = "INSERT INTO USUARIOS VALUES(DEFAULT, ?, ?)";

            PreparedStatement stmt = this.getMbd().preparedStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            // Cadastra e verifica o usuário retornando mensagem de êxito ou erro
            if (this.getMbd().executarAcao(stmt)) {
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar usuário");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected String getSqlTabela() {
        String sqlTabela = "CREATE TABLE USUARIOS ( "
                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
                + "login varchar(60), \n"
                + "senha varchar(20), \n"
                + "CONSTRAINT idUsuario PRIMARY KEY(id) \n"
                + ")";
        return sqlTabela;
    }

}
