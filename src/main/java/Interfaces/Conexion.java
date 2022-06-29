/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author madel
 */
public class Conexion {
    Connection con;
    String url= "jdbc:mysql://localhost:3306/evalti";
    String user= "root";
    String pass="";
    
    public Connection conectar () {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection (url,user,pass);
            
        } catch (Exception e) {
            System.out.println("no funciona");
        }
        return con;
    }
}
