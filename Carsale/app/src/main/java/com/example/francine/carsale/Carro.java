package com.example.francine.carsale;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Francine on 29/10/2017.
 */

public class Carro implements Serializable {
    private String id;
    private String imagem;
    private String modelo;
    private String fabricante;
    private String ano;
    private String cor;
    private String preco;


    public Carro(String id, String imagem, String modelo, String fabricante, String ano, String cor, String preco){
        this.id = id;
        this.imagem = imagem;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
    }

    public String getId(){
        return this.id;
    }

    public String getImagem(){
        return this.imagem;
    }

    public String getModelo(){
        return this.modelo;
    }

    public String getFabricante(){
        return this.fabricante;
    }

    public String getAno(){
        return this.ano;
    }

    public String getCor(){
        return this.cor;
    }

    public String getPreco(){
        return this.preco;
    }

}
