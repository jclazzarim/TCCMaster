/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author mauriverti
 */
public class SQliteJDBC {

    public SQliteJDBC() {
    
    }

    
    
    public void createDB() {
        Connection c = null;
        
        Statement stm;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            
            stm = c.createStatement();
//            String sql = "  CREATE TABLE teste ("
//                    + "         id  integer primary key not null, "
//                    + "         name varchar" 
//                    + " ); ";
            
//         String sql = "  INSERT INTO teste VALUES (0, 'teste zero'); " ;
            
          String sql = "  SELECT * FROM teste " ;
            
            ResultSet rs = stm.executeQuery(sql);
            
            int id = rs.getInt("id");
            String name = rs.getNString("name");
            
            System.out.println(id + ": " + name);
            
            stm.close();
            c.close();
            
            
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }
}
