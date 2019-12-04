package database;

/*import static java.lang.String.format;*/
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

class ManipularBancoDados {

    private static ManipularBancoDados manipulador = null;
    private static final String BD_URL = "jdbc:derby:database;create=true";
    private static Connection cn = null;

    static ManipularBancoDados getInstancia() {
        if (manipulador == null) {
            manipulador = new ManipularBancoDados();
        }
        return manipulador;
    }

    void criarConexao() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
            this.cn = DriverManager.getConnection(this.BD_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fecharConexao() {
        try {
            this.cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void configTabela(String nomeTabela, String sql) {
        try {
            this.criarConexao();
            Statement st = (Statement) this.cn.createStatement();
            DatabaseMetaData bdm = this.cn.getMetaData();
            ResultSet tabela = bdm.getTables(null, null, nomeTabela.toUpperCase(), null);

            if (tabela.next()) {
                System.out.println("A tabela " + nomeTabela + " já existe, pronto para começar");
            } else {
                st.execute(sql);
                System.out.println("Tabela "+nomeTabela+" foi criada.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + "...configuração " + nomeTabela.toLowerCase());
        } finally {
            this.fecharConexao();
        }
    }

    boolean executarAcao(String sql) {
        try {
            this.criarConexao();
            Statement st = this.cn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao executar o manipulador de dados" + ex.getLocalizedMessage());
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    boolean executarAcao(PreparedStatement stmt) {
        try {
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao executar o manipulador de dados" + ex.getLocalizedMessage());
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    ResultSet execConsulta(String query) {
        ResultSet resultado;

        try {
            this.criarConexao();
            Statement st = cn.createStatement();
            resultado = st.executeQuery(query);
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    ResultSet execConsulta(PreparedStatement stmt) {
        try {
            ResultSet resultado = stmt.executeQuery();
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    PreparedStatement preparedStatement(String sql) throws SQLException {
        criarConexao();
        PreparedStatement stmt = this.cn.prepareStatement(sql);
        return stmt;
    }
}
