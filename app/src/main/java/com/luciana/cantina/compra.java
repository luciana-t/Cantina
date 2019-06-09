package com.luciana.cantina;

public class compra {
    private String Preco;
    private String DataCompra;

    public compra(String dt, String pc) {
        Preco = pc;
        DataCompra= dt;
    }

    public String getPreco() {
        return Preco;
    }

    public String getDataCompra() {
        return DataCompra;
    }
}