package com.luciana.cantina;

public class item {
    private String Nome;
    private String Comentario;

    public item(String nome, String comentario) {
        Nome = nome;
        Comentario = comentario;
    }

    public String getNomeDisciplina() {
        return Nome;
    }

    public String getComentario() {
        return Comentario;
    }
}