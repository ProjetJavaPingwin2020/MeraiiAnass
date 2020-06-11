/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Chat;
import Services.ChatService;
import Services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLChatController implements Initializable {
   @FXML
    private TextField input;
@FXML
    private AnchorPane anchor;

    @FXML
    private TextArea messages;

    @FXML
    private Button send;
    UserService s1= new UserService();
    ChatService s2= new ChatService();
    
    String username;

    public FXMLChatController() throws SQLException {
        this.username = s1.getloginusername();
    }
    public void send() throws SQLException
{
    
     LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    { send.setOnMouseClicked(e -> {
        if(input.getText() != null)
        {
            
        Chat p = new Chat(username,"ad",input.getText(),now);
            s2.ajouterChat(p);
            afficher();
        }
     //messages.appendText("Message de " +username+ " : " +input.getText()+"\r\n");

               }) ;
     }
     
}
    
     public void afficher()
     {messages.clear();    
         List<Chat> listC=s2.afficherChat(username,"ad");
         
         for (int i=0;i<listC.size();i++)
         {
             messages.appendText("Message de " +listC.get(i).getSender()+ " Envoyé le " +listC.get(i).getD()+ " : " +listC.get(i).getMessage()+"\r\n");
         }
     }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messages.setEditable(false);
       
 messages.clear();    
 afficher();
        
        
        send.setOnMouseClicked(e -> {
             messages.clear();    
            try {
                send();
               

            } catch (SQLException ex) {
                Logger.getLogger(FXMLChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
             afficher();
 }) ;
        // TODO
     anchor.setOnMouseMoved(e -> {
             messages.clear();    
           
        afficher();

            
 }) ;   
     input.setOnMouseMoved(e -> {
             messages.clear();    
           
        afficher();

            
 }) ;   
     messages.setOnMouseMoved(e -> {
             messages.clear();    
           
        afficher();

            
 }) ; 
    
}
}
    

/*/
 
    

  
  

}
/*/