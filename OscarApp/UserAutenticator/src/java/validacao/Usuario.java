/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;

import java.io.Serializable;


public class Usuario implements Serializable {

    private int id;
    private String nome;
    private String senha;
    private String filme;
    private String diretor;

    public Usuario() {

    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setFilme(String filme) {
        this.filme = filme;
    }
        
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSenha() {
        return this.senha;
    }
    
    public String getFilme() {
        return this.filme;
    }
        
    public String getDiretor() {
        return this.diretor;
    }
}
