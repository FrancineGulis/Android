package com.example.francine.oscarapp;

import java.io.Serializable;

/**
 * Created by Francine on 14/11/2017.
 */

public class Filme implements Serializable {
    private String id;
    private String nome;
    private String genero;
    private String imagem;

    public Filme(String id, String nome, String genero, String imagem){
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.imagem = imagem;
    }

    public String getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getGenero(){
        return this.genero;
    }

    public String getImagem(){
        return this.imagem;
    }
}
