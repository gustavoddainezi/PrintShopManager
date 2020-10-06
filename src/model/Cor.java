
package model;

public class Cor {
    private int cod;
    private String cor;
    private String preco;

    public Cor(int cod, String cor, String preco) {
        this.cod = cod;
        this.cor = cor;
        this.preco = preco;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    
    
}
