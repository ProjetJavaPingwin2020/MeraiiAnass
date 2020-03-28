/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import projetpidev.FXMLLoginController;

/**
 *
 * @author milim
 */
public class session {
      static String user="";
      
      static String nomasso="";

    public static String getUser() {
        return user;
    }

    public static void setUser(String us) {
        session.user = us;
    }

    public static String getNomasso() {
        return nomasso;
    }

    public static void setNomasso(String nomasso) {
        session.nomasso = nomasso;
    }
    
    
      
     
           
   
}
