/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Services.SMS;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SMSController implements Initializable {
    @FXML
    private TextArea Message;
    @FXML
    private Label monnum;
    @FXML
    private TextField num;
    private Button EspecesBtn;
    private Button EvenementsBtn;
    private Button BoutiqueBtn;
    private Button FormationsBtn;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Label Lhello;
    @FXML
    private ImageView sms;
    @FXML
    private ImageView sms1;
    @FXML
    private Button mescadeaux;
    @FXML
    private Button listeformations;
    @FXML
    private Button mesres;

    UserService usrser=new UserService();
    @FXML
    private Text username;
    @FXML
    private Label Ltest;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView panier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        try {
             username.setText(usrser.getloginusername());
           

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        backgroundAnimation();
        try {
         //   Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {
        }
        
        
        
        
    }    
    private void backgroundAnimation() {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                    FadeTransition fadeTransition0 = new FadeTransition(Duration.seconds(3), pane2);
                    fadeTransition0.setToValue(1);
                    fadeTransition0.setFromValue(0);
                    fadeTransition0.play();

                    fadeTransition0.setOnFinished(event3 -> {

                        FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(3), pane3);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {

                            FadeTransition fadeTransition22 = new FadeTransition(Duration.seconds(3), pane4);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                backgroundAnimation();
                            });

                        });

                    });

                });
            });

        });

    }  


    private void send(ActionEvent event) {
         SMS.sendTrait(Message.getText());
        

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Envoyer Message");
        alert.setHeaderText("Dialogue information");
        alert.setContentText("Envoi du message avec succés");
        alert.showAndWait();
    }


    @FXML
    private void envoyersms(MouseEvent event) {
           SMS.sendTrait(Message.getText());
        

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Envoyer Message");
        alert.setHeaderText("Dialogue information");
        alert.setContentText("Envoi du message avec succés");
        alert.showAndWait();
    }

    @FXML
    private void mescadeaux(ActionEvent event) {
        Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Pas de choix!");
            alertu.setHeaderText("Vous devez consulter la liste des formations pour obtenir le QrCode");
            Optional<ButtonType> result = alertu.showAndWait();
    }

    @FXML
    private void mesres(ActionEvent event) {
    }

    private void redirectionEvent(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        Parent root = loader.load();
        EvenementController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }
    

    private void redirectionBoutique(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontProduit.fxml"));
        Parent root = loader.load();
        FXMLFrontProduitController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }

    private void redirectionEspece(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherCategorie_espece.fxml"));
        Parent root = loader.load();
        FXMLAfficherCategorie_especeController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void panier(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPanier.fxml"));
        Parent root = loader.load();
        FXMLPanierController acc = loader.getController();
        panier.getScene().setRoot(root);
    }

    private void redirectionformation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void listeformations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        listeformations.getScene().setRoot(root);
    }
    
}
