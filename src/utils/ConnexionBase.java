/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Yassiine
 */
public class ConnexionBase 
        {

    private static ConnexionBase instance;

    private final static String url = "jdbc:mysql://localhost:3308/test1.1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private String login = "root";
    private String mdp = "";
    private Connection cnx;

    private ConnexionBase() {
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static ConnexionBase getInstance(){
        if (instance == null) {
            instance = new ConnexionBase();
        }
        return instance ;
    }

    public Connection getCnx() {
        return cnx;
    }
   

}