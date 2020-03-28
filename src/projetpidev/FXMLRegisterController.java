/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.FosUser;
import Services.Cryptage;
import Services.UserSevice;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLRegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> role = FXCollections.observableArrayList("Admin","Client");
    
     @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private TextField email;

    @FXML
    private TextField username;
    
    @FXML
    void register(ActionEvent event) throws Exception {
        
                 try {
                
                           
                                UserSevice s1 = new UserSevice();
                                Cryptage Cryptage = new Cryptage("lv39eptlvuhaqqsr");
                                String encdata = Cryptage.encrypt(password.getText());
                                        String r = roles.getSelectionModel().getSelectedItem().toString();
                            
                                
                               
                                
                                FosUser u = new FosUser(username.getText(),email.getText(),encdata,r);
                               
                                s1.ajouterUser(u);
                                   
                                Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                                
                                
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLRegisterController.class.getName()).log(Level.SEVERE, null, ex);
                            }
((Node) event.getSource()).getScene().getWindow().hide();
    }
    
     
               
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         roles.setItems(role);
        // TODO
    }    
    
}
