package com.example.francine.jsoncar;

/**
 * Created by Francine on 20/10/2017.
 */

public class Carro {
    private int id;
    private String modelo;
    private  String placa;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }

    //Aqui fizemos o Override do metodo toString() para
    //visualizar a impressão com o System.out.println()

    @Override
    public String toString(){
        return "[id = " + id + ", modelo= " + modelo + ", placa = " + placa + "]";
    }
}
