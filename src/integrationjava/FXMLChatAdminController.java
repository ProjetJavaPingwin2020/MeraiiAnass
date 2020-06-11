/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Categorie;
import Entity.Chat;
import Services.ChatService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLChatAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ChoiceBox<String> clients;
      
    @FXML
    private TextField input;

    @FXML
    private TextArea messages;

    @FXML
    private Button send;
   
     @FXML
    private Pane pane;
       @FXML
    private AnchorPane anchor;
    
    UserService s1= new UserService();
    ChatService s2= new ChatService();
    
    String username;

    public FXMLChatAdminController() throws SQLException {
        this.username = s1.getloginusername();
    }
    public void send(String user) throws SQLException
{
    
     LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    { send.setOnMouseClicked(e -> {
        if(input.getText() != null)
        {
            
        Chat p = new Chat(username,user,input.getText(),now);
            s2.ajouterChat(p);
            afficher(user);
        }
  
               }) ;
     }
}
    
     public void afficher(String user)
     {messages.clear(); 
         List<Chat> listC=s2.afficherChat(user,user);
         
         for (int i=0;i<listC.size();i++)
         {
             messages.appendText("Message de " +listC.get(i).getSender()+ " Envoyé le " +listC.get(i).getD()+ " : " +listC.get(i).getMessage()+"\r\n");
         }
     }
    
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        ObservableList<String> client_name = FXCollections.observableArrayList();
        
   List<String> d3 = s1.listeuser();
   
   
   
   for(int i=0;i<d3.size();i++)
   
   {
       String val=(d3.get(i));
       
       client_name.add(val);
       
   }
   
    clients.setItems(client_name);
    
    
    
   
    messages.setOnMouseMoved(e -> {
        messages.clear();
         String user= clients.getSelectionModel().getSelectedItem();
       
        afficher(user);

               }) ;
    
     send.setOnMouseClicked(e -> {
            try {
                String user= clients.getSelectionModel().getSelectedItem();
                send(user);
                afficher(user);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLChatAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

               }) ;
    
    anchor.setOnMouseMoved(e -> {
        messages.clear();
         String user= clients.getSelectionModel().getSelectedItem();
       
        afficher(user);

               }) ;
    input.setOnMouseMoved(e -> {
        messages.clear();
         String user= clients.getSelectionModel().getSelectedItem();
       
        afficher(user);

               }) ;
}

}
