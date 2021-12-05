/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proiect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Raluca
 */
public class Conexiune {

    public static Connection connect() throws ClassNotFoundException,
            SQLException

             {
                 try {
                     Class.forName("org.apache.derby.jdbc.ClientDriver");
                 } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }
                 String dbUrl = "jdbc:derby://localhost:1527/ProiectDB";
                 Properties props = new Properties();
                 props.put("user", "root");
                 props.put("password", "Fitmahitwailh!!<3");

                 Connection conn = DriverManager.getConnection(dbUrl,props);

                 return conn;

    }
}
