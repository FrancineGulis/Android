package com.example.francine.artman;

import java.io.Serializable;

/**
 * Created by Francine on 01/11/2017.
 */

public class Artigo implements Serializable {
    private static final long serialVersionUID = 1633833011084400384L;
    int id;
    String revista;
    String nome;
    String edicao;
    int status;
    int pago;
}
