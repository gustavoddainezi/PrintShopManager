
package model;

public class Produto {
    private int codItem;
    private String tipoItem;
    private String descricao;
    private String tamanho;
    private int quantidade;
    private double precoUnit;
    private int estoque;
    private String fornecedor;

    public int getCodItem() {
        return codItem;
    }

    public void setCodItem(int codItem) {
        this.codItem = codItem;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto(int codItem, String tipoItem, String descricao, String tamanho, int quantidade, double precoUnit, int estoque, String fornecedor) {
        this.codItem = codItem;
        this.tipoItem = tipoItem;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.precoUnit = precoUnit;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
    }
}
