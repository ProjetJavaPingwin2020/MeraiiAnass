/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Categorie_espece;
import Entity.Espece;
import Services.ServiceCategorie_espece;
import Services.ServiceEspece;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author TH3OMAR
 */
public class FXMLAfficherEspeceController implements Initializable {

    @FXML
    private TableView<Espece> especetable;

    /**
     * Initializes the controller class.
     */
    private TableView<Espece> categorie_espece;
    public ObservableList<Espece> cls = FXCollections.observableArrayList();
    public static Espece e;
    @FXML
    private Label Lhello;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView panier;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button list_Categorie;
    @FXML
    private Button list_espece;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");

        backgroundAnimation();
     
      
    
       ServiceEspece se = new ServiceEspece();
       ServiceCategorie_espece sce = new ServiceCategorie_espece();
       
       
       TableColumn nom = new TableColumn("Nom");
       especetable.getColumns().addAll(nom);
       TableColumn type = new TableColumn("Type");
       especetable.getColumns().addAll(type);
       TableColumn description = new TableColumn("Description");
       especetable.getColumns().addAll(description);
       TableColumn categorie = new TableColumn("categorie");
       especetable.getColumns().addAll(categorie);
        
        
        
      //  System.out.println(e.getNomCategorie(categorie));
        categorie.setCellValueFactory(new PropertyValueFactory<Espece, String>("categorie"));
        nom.setCellValueFactory(new PropertyValueFactory<Espece, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Espece, String>("type"));
        description.setCellValueFactory(new PropertyValueFactory<Espece, String>("description"));
     
     
        try {
         especetable.setItems(se.getAllEspeces());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherEspeceController.class.getName()).log(Level.SEVERE, null, ex);
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
    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void redirectionFormation(ActionEvent event) {
    }

   @FXML
    private void redirectionEspece(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherCategorie_espece.fxml"));
        Parent root = loader.load();
        FXMLAfficherCategorie_especeController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }
//sous menu
    @FXML
    private void list_Categorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherCategorie_espece.fxml"));
        Parent root = loader.load();
        FXMLAfficherCategorie_especeController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void list_espece(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherEspece.fxml"));
        Parent root = loader.load();
       FXMLAfficherEspeceController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }
}