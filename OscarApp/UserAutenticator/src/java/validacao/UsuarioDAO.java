/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private final String stmtConsultar = "SELECT * FROM usuario WHERE nome = ? and senha = ?";
    private final String stmtVotar = "UPDATE usuario SET filme = ?, diretor = ? WHERE nome = ?";

    public Usuario consultarUsuario(String user, String sen) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario u = null;
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtConsultar);
            stmt.setString(1, user);
            stmt.setString(2, sen);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");                
                String senha = rs.getString("senha");
                String filme = rs.getString("filme");
                String diretor = rs.getString("diretor");

                u = new Usuario();
                u.setId(id);
                u.setNome(nome);
                u.setSenha(senha);
                u.setFilme(filme);
                u.setDiretor(diretor);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um usuário no banco de dados. Origem=" + ex.getMessage());

        }
        return u;
    }
    
    public int Votar(String filme, String diretor, String nome) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(stmtVotar);
            stmt.setString(1, filme);
            stmt.setString(2, diretor);
            stmt.setString(3, nome);
            int i = stmt.executeUpdate();
            
            return i;
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao gravar voto. Origem=" + ex.getMessage());
        }
    }
}