package database;

/*import static java.lang.String.format;*/
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManipularBancoDados {
    private static ManipularBancoDados manipulador = null;
    private static final String BD_URL = "jdbc:derby:database;create=true";
    private static Connection cn = null;
    private static Statement st = null;

    ManipularBancoDados() {
        this.criarConexao();

        String nomeTabela[] = {"USUARIOS", "CLIENTES", "FORNECEDORES", "FUNCIONARIOS"};
        for (int i = 0; i < nomeTabela.length; i++) {
            executarAcao("drop table " + nomeTabela[i]);
        }
        String sql[] = {
            "CREATE TABLE USUARIOS ( "
            + "id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
            + "login varchar(60), "
            + "senha INTEGER, "
            + "CONSTRAINT primary_key PRIMARY KEY(id) "
            + ")",
            "CREATE TABLE CLIENTES ( \n"
            + "id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
            + "nome varchar(40), \n"
            + "tipo varchar(15) CONSTRAINT tipoCliente CHECK (tipo IN ('Pessoa Física', 'Pessoa Jurídica')), \n"
            + "nomeFantasia varchar(60), \n"
            + "endereco varchar(120), \n"
            + "bairro varchar(60), \n"
            + "cidade varchar(60), \n"
            + "uf varchar(2), \n"
            + "complemento varchar(60), \n"
            + "CEP varchar(9), \n"
            + "email varchar(60), \n"
            + "cpfcnpj varchar(18) \n"
            + " )",
            "CREATE TABLE FORNECEDORES ( "
            + "id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
            + "nomeFornec varchar(40), "
            + "razao varchar(40), "
            + "produto varchar(50), "
            + "cpfcnpj varchar(18), "
            + "endereco varchar(60), "
            + "bairro varchar(50), "
            + "telefone varchar(15), "
            + "uf varchar(20), "
            + "CONSTRAINT idFornecedor PRIMARY KEY(id) "
            + ")",
            "CREATE TABLE FUNCIONARIOS ( "
            + "id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
            + "nomeFunc varchar(40), "
            + "cpf varchar(14), "
            + "cargo varchar(20), "
            + "cep varchar(9), "
            + "endereco varchar(60), "
            + "bairro varchar(50), "
            + "cidade varchar(30), "
            + "uf varchar(20), "
            + "email varchar(40), "
            + "telefone varchar(15), "
            + "CONSTRAINT ifFuncionario PRIMARY KEY(id) "
            + ")",};
        for (int i = 0; i < nomeTabela.length; i++) {
            System.out.println(i);
            this.configTabela(nomeTabela[i], sql[i]);
        }
    }

    public static ManipularBancoDados getInstancia() {
        if (manipulador == null) {
            manipulador = new ManipularBancoDados();
        }
        return manipulador;
    }

    private void criarConexao() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
            this.cn = DriverManager.getConnection(this.BD_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configTabela(String nomeTabela, String sql) {
        try {
            this.st = (Statement) this.cn.createStatement();
            DatabaseMetaData bdm = this.cn.getMetaData();
            ResultSet tabela = bdm.getTables(null, null, nomeTabela.toUpperCase(), null);

            if (tabela.next()) {
                System.out.println("A tabela " + nomeTabela + " já existe, pronto para começar");
            } else {
                this.st.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + "...configuração " + nomeTabela.toLowerCase());
        }
    }

    public boolean executarAcao(String sql) {
        try {
            this.st = this.cn.createStatement();
            this.st.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao executar o manipulador de dados" + ex.getLocalizedMessage());
            return false;
        } finally {

        }
    }

    public ResultSet execConsulta(String query) {
        ResultSet resultado;

        try {
            this.st = cn.createStatement();
            resultado = this.st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

        }
        return resultado;
    }
}