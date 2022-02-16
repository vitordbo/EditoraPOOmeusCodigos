package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	
	private static Connection con = null;  
    private static final String url = "jdbc:postgresql://localhost:5432/editora";
    private static final String user = "postgres";
    private static final String senha = "Flamengo2021";

    public static Connection getConnection(){
        if (con == null){  
            try {
            con = DriverManager.getConnection(url,user,senha);
                System.out.println("Conectado com sucesso");
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
            return con; 
        }
        else { 
            return con;
        }
        
    }
    
}
