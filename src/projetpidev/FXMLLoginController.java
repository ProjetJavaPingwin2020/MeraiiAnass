/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.FosUser;
import Entity.Login;
import Services.UserSevice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private Button register;
   
 

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
         UserSevice s1 = new UserSevice();
         s1.deletelogin();
         FosUser u1 = s1.existLogin(username.getText(), password.getText());
         Login.setUsername(username.getText());
   if (u1 != null)
       {
         s1.ajouterlogin(u1);
       String ss= s1.getlogin() ;
       int result = Integer.parseInt(ss);			
			
                        System.out.println(result);

        
           }      
       else 
       {
           System.out.println("Invalid Cred");
       }
       
     try {
               
               if (u1.getRoles().equals("Admin"))
               {
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLMenuBack.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               Login.setUsername(username.getText());
               }
               else {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLMenuFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               Login.setUsername(username.getText());
               }
               
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                ((Node) event.getSource()).getScene().getWindow().hide();  
     //((Node) event.getSource()).getScene().getWindow().hide();
    }
    public void redirect(ActionEvent event)
    {
         try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLRegister.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                ((Node) event.getSource()).getScene().getWindow().hide();   
    
                         
    }
               
          

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}


