/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppbo_projectakhir;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;

/**
 *
 * @author Yn
 */
public class Konfig {
    private static Connection MySQLConfig;
    public static Connection configDB()throws SQLException{
        try {
            String url="jdbc:mysql://localhost:3306/ppbo_tokobuku"; //url database
            String user="root"; //user database
            String pass=""; //password database
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            MySQLConfig=(Connection) DriverManager.getConnection(url,user,pass);
        
        } catch (SQLException e){
            System.out.println("Koneksi gagal " + e.getMessage()); //perintah menampilkan error pada koneksi
        }
        
        return MySQLConfig;
        
        
    }
}
