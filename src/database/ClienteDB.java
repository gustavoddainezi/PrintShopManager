package database;

public class ClienteDB {
    
    private ManipularBancoDados cn;
    String nomeTabela = "CLIENTES";
    
    ClienteDB (){
        this.cn = ManipularBancoDados.getInstancia();
    }
}