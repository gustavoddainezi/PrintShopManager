
package database;

import static java.lang.String.format;
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

    private ManipularBancoDados() {
        this.criarConexao();
        this.configTabelaUsuarios();
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
    
    private void configTabelaUsuarios(){
            String nomeTabela = "USUARIOS";
            
        try {
            this.st = (Statement) this.cn.createStatement();
            DatabaseMetaData bdm = this.cn.getMetaData();
            ResultSet tabela = bdm.getTables(null, null, nomeTabela.toUpperCase(), null);
            
            if (tabela.next()) {
                System.out.println("A tabela " + nomeTabela + " já existe, pronto para começar");
            }
            else{
                
            String sql = format("CREATE TABLE %s ( \n"
                    + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
                    + "login varchar(60), \n"
                    + "senha INTEGER, \n"
                    + "CONSTRAINT primary_key PRIMARY KEY(id)"
                    + ")", nomeTabela.toUpperCase());
            
            this.st.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + "...configuração usuários");
        }
    }
    
    public boolean executarAcao(String qu){
        try {
            this.st = this.cn.createStatement();
            this.st.execute(qu);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao executar o manipulador de dados" + ex.getLocalizedMessage());
            return false;
        } finally{
            
        }  
    }
    
    public ResultSet execConsulta(String query){
        ResultSet resultado;
        
        try {
            this.st = cn.createStatement();
            resultado = this.st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(ManipularBancoDados.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
        
        }
        return resultado;
    }
    
}