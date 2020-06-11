/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Demande;
import Entity.Evenement;
import Entity.FosUser;
import Entity.Login;
import Services.DemandeService;
import Services.EvenementService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class DemandeevenementController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label Lhello;
    @FXML
    private AnchorPane parent;
    @FXML
    TableView<Demande> TVtable;
    @FXML
    private Label Ltest;
    @FXML
    private Button BTref;
    @FXML
    private Button BTacc;

    /**
     * Initializes the controller class.
     */
    DemandeService ds = new DemandeService();
    @FXML
    private ImageView logout;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane11;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button BoutiqueBtn;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        backgroundAnimation();
        try {
            Lhello.setText("Bienvenue " + Login.getUsername());
        } catch (Exception e) {

        }

       
        TableColumn iduser = new TableColumn("Nom du participant");
        TableColumn idevent = new TableColumn("Nom de l'événement");
        TableColumn etat = new TableColumn("Etat");
        TVtable.getColumns().addAll(etat, iduser, idevent);

        Demande d = new Demande();

       
        etat.setCellValueFactory(new PropertyValueFactory<Demande, String>("etat"));
        iduser.setCellValueFactory(new PropertyValueFactory<Demande, String>("username"));
        idevent.setCellValueFactory(new PropertyValueFactory<Demande, String>("nomevent"));

        UserService us = new UserService();
        try {
            ObservableList<Demande> cls = FXCollections.observableArrayList();
            List<Demande> Liste = ds.Affichage();
            for (Demande aux : Liste) {
                cls.add(new Demande(aux.getId(), aux.getEtat(), aux.getUsername(), aux.getNomevent()));
                TVtable.setItems(cls);
            }
            // TVtable.setItems(ds.Affichage());
            System.out.println(":')");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeevenementController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void button2(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/admin_evenementStats.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void button3(ActionEvent event) {
    }

    @FXML
    private void displayselected() throws Exception {
        Demande d = TVtable.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void RefuserDemande(MouseEvent event) {
    }

    @FXML
    private void refdemande(ActionEvent event) throws SQLException {
        Demande d = TVtable.getSelectionModel().getSelectedItem();
        System.out.println(d.getId());
        System.out.println(d.getEtat());
        d.setEtat("Refuse");
        ds.updateapp(d);

        UserService us = new UserService();
        ObservableList<Demande> cls = FXCollections.observableArrayList();
        List<Demande> Liste = ds.Affichage();
        for (Demande aux : Liste) {
            cls.add(new Demande(aux.getId(), aux.getEtat(), aux.getUsername(), aux.getNomevent()));
            TVtable.setItems(cls);
        }
    }

    @FXML
    private void ApprouverDemande(MouseEvent event) throws SQLException {
        Demande d = TVtable.getSelectionModel().getSelectedItem();
        System.out.println(d.getId());
        System.out.println(d.getEtat());
        d.setEtat("Validé");
        ds.updateapp(d);

        UserService us = new UserService();
        ObservableList<Demande> cls = FXCollections.observableArrayList();
        List<Demande> Liste = ds.Affichage();
        for (Demande aux : Liste) {

            cls.add(new Demande(aux.getId(), aux.getEtat(), aux.getUsername(), aux.getNomevent()));
            TVtable.setItems(cls);
        }
    }

    @FXML
    private void accdemande(ActionEvent event) {
        
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionevent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectioninfo(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLArticles_especes.fxml"));
        Parent root = loader.load();
        FXMLArticles_especesController acc = loader.getController();
        InformationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionespece(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBackEspece.fxml"));
        Parent root = loader.load();
        FXMLBackEspeceController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionboutique(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduitCategorie.fxml"));
        Parent root = loader.load();
        FXMLProduitCategorieController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }

}
