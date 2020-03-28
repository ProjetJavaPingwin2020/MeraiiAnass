/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Evenement;
import Entity.Login;
import Services.EvenementService;
import Services.UserSevice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class Get_all_evenementController implements Initializable {
    @FXML
    private BorderPane borderpane;
    @FXML
    private TextField TFrecherche;
    @FXML
    private Label Lhello;
    @FXML
    private AnchorPane parent;
    @FXML
    private ListView<HBox> LVlist;
    @FXML
    private Label Ltest;

    
    //display
    UserSevice us = new UserSevice();
    @FXML
    private Button BTaccess;
    @FXML
    private Button BTjoin;

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle resources) {
        try {
            Lhello.setText("Bonjour " + Login.getUsername());
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
                labels.get(i).setPadding(new Insets(70, 0, 0, 0));
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

    @FXML
    void button1(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/projetpidev/add_evenement.fxml")));
        stage.setScene(scene);

    }

    @FXML
    private void button2(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/projetpidev/user_evenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void button3(ActionEvent event) {
    }

    @FXML
    private void button4(ActionEvent event) {
    }

    @FXML
    void backToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projetpidev/FXMLMenuFront.fxml"));
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
        int id = es.getidEventByName(Ltest.getText());
        Login.setNom_event(Ltest.getText());
        if (!es.alreadyMembre(id, us.getidUSERByusername())) {
            es.adddemande(id, us.getidUSERByusername());
        }
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/projetpidev/inevenement.fxml")));
        stage.setScene(scene);
 
    }


       
}
