package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    //Método que vai retorna oque o drivermanager conseguir fazer
    //getConnection retorna um objeto do tipo connection
    public Connection getConnection(){
        
        try{
            
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas","usuariocurso","123");
            
        }catch(SQLException e){
            
            throw new RuntimeException(e);  
            
        }
    }
  
}
