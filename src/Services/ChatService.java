/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Chat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

/**
 *
 * @author Yassiine
 */
public class ChatService {
    
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public ChatService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }
    
   
    
       public void ajouterChat(Chat p) {

        try {
           String req = "INSERT INTO chat (sender, receiver, message,date) VALUES "
                    + "('" + p.getSender() + "', '" + p.getReceiver() + "', '" + p.getMessage()+ "', '" + p.getSend_date() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
public List<Chat> afficherChat(String sender, String receiver) {

        List<Chat> listC = new ArrayList<>();

        try {

            String req = "SELECT * FROM chat  ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if(res.getString("sender").equals(sender) || res.getString("receiver").equals(sender))
                {
                Chat p = new Chat();
                p.setId(res.getInt("id"));
               p.setMessage(res.getString("message"));
               p.setReceiver(res.getString("receiver"));
               p.setSender(res.getString("sender"));
               p.setD(res.getDate("date"));

                listC.add(p);
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }

    
    
    
    
    
}
