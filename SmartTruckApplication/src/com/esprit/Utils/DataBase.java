package com.esprit.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aa
 */
public class DataBase {
    


     String url = "jdbc:mysql://localhost:8889/SmartTruck";
     String login = "root";
     String pwd = "root";
    public  static DataBase db;
    public Connection con;
    private DataBase() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static DataBase getInstance()
    {if(db==null)
        db=new DataBase();
    return db;
    }     
     
}
