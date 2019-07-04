package com.luciana.cantina;

public class produto {
    //Variaveis
    private String codBarra;
    private int qt;
    private String unidade;
    private float preco;
    private String dataAquisicao;

    //Setters
    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }
    public void setQt(int qt) {
        this.qt = qt;
    }
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    //Getters
    public String getCodBarra() {
        return codBarra;
    }
    public int getQt() {
        return qt;
    }
    public String getUnidade() {
        return unidade;
    }
    public float getPreco() {
        return preco;
    }
    public String getDataAquisicao() { return dataAquisicao; }
}
