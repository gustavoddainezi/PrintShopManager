package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class TabelaDAO {

    private ManipularBancoDados mbd;
    private String nomeTabela;

    public TabelaDAO(String nomeTabela) {
        this.mbd = ManipularBancoDados.getInstancia();
        this.nomeTabela = nomeTabela;
        this.mbd.configTabela(this.nomeTabela, this.getSqlTabela());
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public ManipularBancoDados getMbd() {
        return mbd;
    }

    public void mostrar() {
        String sql = "Select * FROM "+this.nomeTabela;
        try {
            ResultSet rs = this.getMbd().execConsulta(sql);
            System.out.println(this.nomeTabela);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.println(rs.getMetaData().getColumnName(i)+": "+rs.getString(i));
                }
                System.out.println("Fim registro");
                System.out.println("");
            }
            System.out.println("\n");
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract String getSqlTabela();
}
