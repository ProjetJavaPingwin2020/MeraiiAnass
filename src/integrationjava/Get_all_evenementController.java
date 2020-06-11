/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Evenement;
import Entity.Login;
import Services.EvenementService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class Get_all_evenementController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label Lhello;
    @FXML
    private AnchorPane parent;
    @FXML
    private ListView<HBox> LVlist;
    @FXML
    private Label Ltest;

    //display
    UserService us = new UserService();
    @FXML
    private Button BTaccess;
    @FXML
    private Button BTjoin;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private ImageView panier;
    @FXML
    private ImageView logout;
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
    private Button button3;
    @FXML
    private Button button2;

    /**
     * Initializes the controller class.
     */
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
        EvenementService es = new EvenementService();
        try {
            ObservableList<ImageView> ivs = es.getAllEvenementImages();

            ObservableList<Evenement> els = es.getAllevenements();
            ObservableList<Label> labels = FXCollections.observableArrayList();

            for (int i = 0; i < els.size(); i++) {
                labels.add(new Label(els.get(i).getNom_evenement()));
            }
            final double MAX_FONT_SIZE = 30.0;

            ObservableList<HBox> hbs = FXCollections.observableArrayList();
            HBox hb = new HBox();
            HBox hb2 = new HBox();
            HBox hb3 = new HBox();

            for (int i = 0; i < ivs.size(); i++) {
                //hb.getChildren().addAll(ivs.get(0),labels.get(0));
                labels.get(i).setFont(new Font(MAX_FONT_SIZE));
                labels.get(i).setPadding(new Insets(10, 0, 0, 0));
                hbs.add(new HBox(ivs.get(i), labels.get(i)));

            }
            for (int i = 0; i < ivs.size(); i++) {
                hbs.get(i).setSpacing(80);
            }
//              
            LVlist.setItems(hbs);

        } catch (SQLException ex) {
            Logger.getLogger(Get_all_evenementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Get_all_evenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;

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
    void button1(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ajoutevenement.fxml")));
        stage.setScene(scene);

    }

    @FXML
    private void button2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userevenement.fxml"));
        Parent root = loader.load();
        UserevenementController acc = loader.getController();
        button2.getScene().setRoot(root);
    }

    void backToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        Stage stagec = (Stage) parent.getScene().getWindow();
        stagec.close();

    }

    @FXML
    private void displaySeleckted() throws SQLException {
        EvenementService es = new EvenementService();
        HBox hb = new HBox();
        hb = LVlist.getSelectionModel().getSelectedItem();
        Node nodeOut = hb.getChildren().get(1);
        if (nodeOut instanceof Label) {

            Ltest.setText(((Label) nodeOut).getText());
            int id = es.getidEventByName(Ltest.getText());

            if (es.alreadyMembre(id, us.getidUSERByusername())) {
                BTjoin.setVisible(false);
            } else {
                BTjoin.setVisible(true);
            }
        }
    }

    @FXML
    private void joinEvenement(ActionEvent event) throws IOException, SQLException {
        EvenementService es = new EvenementService();
        if (Ltest.getText().equals("")) {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("pas de choix!");
            alertu.setHeaderText("vous dever choisir un evénement !");
            Optional<ButtonType> result = alertu.showAndWait();
        }
        int id_event = es.getidEventByName(Ltest.getText());
        if (!es.alreadyMembre(id_event, us.getidUSERByusername())) {
            es.adddemande(id_event, us.getidUSERByusername());
        }
        Login.setNom_event(Ltest.getText());
        String k = es.getetatEventByName(id_event, us.getidUSERByusername());
        if (k.equals("En cours !")) {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("pas de choix!");
            alertu.setHeaderText("vous demande est en cours de traitement !");
            Optional<ButtonType> result = alertu.showAndWait();
        } else if (k.equals("Refusé")) {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("pas de choix!");
            alertu.setHeaderText("vous demande est refusée  !");
            Optional<ButtonType> result = alertu.showAndWait();
        } else {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/inevenement.fxml")));
            stage.setScene(scene);
        }
        
        
        
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionevent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        Parent root = loader.load();
        EvenementController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void button3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("get_all_evenement.fxml"));
        Parent root = loader.load();
        Get_all_evenementController acc = loader.getController();
        button3.getScene().setRoot(root);
    }

    @FXML
    private void redirectionBoutique(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontProduit.fxml"));
        Parent root = loader.load();
        FXMLFrontProduitController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionInformation(ActionEvent event) {
    }

    @FXML
    private void redirectionEspece(ActionEvent event) {
    }


}
