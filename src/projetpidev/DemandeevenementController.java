/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Demande;
import Entity.Evenement;
import Entity.FosUser;
import Entity.Login;
import Services.DemandeService;
import Services.EvenementService;
import Services.UserSevice;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class DemandeevenementController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private TextField TFrecherche;
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

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        try {
            Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {

        }

        TableColumn id = new TableColumn("id");
        TableColumn iduser = new TableColumn("iduser");
        TableColumn idevent = new TableColumn("idevent");
        TableColumn etat = new TableColumn("etat");
        TVtable.getColumns().addAll(id, etat, iduser, idevent);

        Demande d = new Demande();

        id.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("id"));
        etat.setCellValueFactory(new PropertyValueFactory<Demande, String>("etat"));
        iduser.setCellValueFactory(new PropertyValueFactory<Demande, String>("username"));
        idevent.setCellValueFactory(new PropertyValueFactory<Demande, String>("nomevent"));

        UserSevice us = new UserSevice();
        try {
            ObservableList<Demande> cls = FXCollections.observableArrayList();
            List<Demande> Liste = ds.Affichage();
            for (Demande aux : Liste) {
                cls.add(new Demande(aux.getId(), aux.getEtat(), aux.getUsername(), aux.getNomevent()));
                TVtable.setItems(cls);
            }
            // TVtable.setItems(ds.Affichage());
            System.out.println("aaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeevenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void button2(ActionEvent event) {
    }

    @FXML
    private void button3(ActionEvent event) {
    }

    @FXML
    private void backToMenu(ActionEvent event) {

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

        UserSevice us = new UserSevice();
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

        UserSevice us = new UserSevice();
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

}
