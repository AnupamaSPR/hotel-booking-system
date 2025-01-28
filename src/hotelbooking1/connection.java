/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking1;

/**
 *
 * @author HP-DV6
 */import java.sql.*; 
import java.sql.DriverManager;
import java.sql.Statement;


public class connection

{
     Statement s;
     Connection c;
   
    
    connection(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "Roshini@123");
           s=c.createStatement();
           
           
           
        }catch(Exception E)
            
        {
            E.printStackTrace();
    }
    }
    public static void main(String[]args)
    {
 
    }
}
