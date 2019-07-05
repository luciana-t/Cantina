package com.luciana.cantina;

public class comida{

    public int drawableID;
    public float preco;
    private String nome;
    private int amount;

    public comida(String nome, float price, int draw) {
        this.nome = nome;
        this.preco = price;
        this.amount=0;
        this.drawableID=draw;
    }
    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
