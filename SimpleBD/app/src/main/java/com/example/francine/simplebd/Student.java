package com.example.francine.simplebd;

/**
 * Created by Francine on 30/10/2017.
 */

public class Student {
    private int id;
    private String name;

    public long getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
