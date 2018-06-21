
package validacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    public static Connection getConnection() throws SQLException, ClassNotFoundException{        
        
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/oscar";
            String usuario = "postgres";
            String senha = "senha";
                    
            Connection con = DriverManager.getConnection(url, usuario, senha);
            return con;
        } catch (SQLException ex) {
            throw new RuntimeException("Problemas ao conectar com o BD. Exceção: " + ex.getMessage());}
    }
}