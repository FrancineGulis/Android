package com.example.francine.oscarapp;

/**
 * Created by Francine on 15/11/2017.
 */

public class Votos {
    private String filme;
    private String diretor;

    public Votos(String filme, String diretor){
        this.filme = filme;
        this.diretor = diretor;
    }

    public String getFilme(){
        return this.filme;
    }

    public String getDiretor(){
        return this.diretor;
    }

    public void setFilme(String filme){
        this.filme = filme;
    }

    public void setDiretor(String diretor){
        this.diretor = diretor;
    }
}
