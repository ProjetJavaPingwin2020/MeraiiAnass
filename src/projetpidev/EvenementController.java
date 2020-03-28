/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class EvenementController implements Initializable {

    @FXML
    private BorderPane borderpane;
    private Label Lhello;
    @FXML
    private AnchorPane parent;
    @FXML
    private Button button3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        try {
            Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {

        }

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
    void button3(ActionEvent event) throws IOException {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/projetpidev/get_all_evenement.fxml")));
        stage.setScene(scene);
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

    }

 