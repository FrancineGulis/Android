package com.example.francine.oscarapp;

import java.io.Serializable;

/**
 * Created by Francine on 15/11/2017.
 */

public class Diretor implements Serializable {
    private String id;
    private String nome;

    public Diretor(String id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }
}
