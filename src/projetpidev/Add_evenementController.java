/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import com.jfoenix.controls.JFXComboBox;
import Entity.Adresse;
import Entity.Evenement;
import Entity.Login;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Services.*;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class Add_evenementController implements Initializable {

    @FXML
    private BorderPane borderpane;

    @FXML
    private Label Lhello;

    @FXML
    private AnchorPane parent;

    @FXML
    private Label Lasso;

    @FXML
    private TextField TFnom;

    @FXML
    private JFXComboBox<String> CBgovernorat;

    @FXML
    private JFXComboBox<String> CBvile;

    @FXML
    private TextField TFrue;
    @FXML
    private ImageView IVimage;

    @FXML
    private TextField TFcap;
    @FXML
    private TextArea TApath;

    @FXML
    private JFXTimePicker heure;

    @FXML
    private DatePicker datepicker;
    //pour la fonction initialize
    Adresse a = new Adresse();
    //pour le fonction parcourir (image)
    private FileChooser fileChooser;
    private File file;
    private Image image;
    private FileInputStream fis;
    // pour l'ajout
    int idlastevent = 0;
    UserSevice ms = new UserSevice();
    private Stage stage;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        try {
            Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {

        }
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        a.getGovBYid(1),
                        a.getGovBYid(2),
                        a.getGovBYid(3),
                        a.getGovBYid(4),
                        a.getGovBYid(5),
                        a.getGovBYid(6),
                        a.getGovBYid(7),
                        a.getGovBYid(8),
                        a.getGovBYid(9),
                        a.getGovBYid(10),
                        a.getGovBYid(11),
                        a.getGovBYid(12)
                );
        CBgovernorat.setItems(options);

    }

    @FXML
    private void button1(ActionEvent event) {
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
    private void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projetpidev/FXMLMenuFront.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        Stage stagec = (Stage) parent.getScene().getWindow();
        stagec.close();
    }

    @FXML
    void onAdressConfirmed(ActionEvent event) {
        int id = CBgovernorat.getSelectionModel().getSelectedIndex() + 1;
        System.out.println(id);
        CBvile.setItems(a.getVilleFroGov(id));

    }

    @FXML
    private void Parcourir(ActionEvent event) throws Exception {

        fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
        Stage stagec = (Stage) parent.getScene().getWindow();
        file = fileChooser.showOpenDialog(stagec);
        if (file != null) {
            //desktop.open(file);
            image = new Image(file.toURI().toString());
            IVimage.setImage(image);
            TApath.setText(file.getAbsolutePath());

        }
    }

    @FXML
    void onCreerClicked(ActionEvent event) throws SQLException, FileNotFoundException {

        //System.out.println(d);
        a.setGov(CBgovernorat.getSelectionModel().getSelectedItem());
        a.setRue(TFrue.getText());
        a.setVille(CBvile.getSelectionModel().getSelectedItem());
        System.out.println(a.toString());
        String s = a.toString();
        EvenementService es = new EvenementService();
        UserSevice u = new UserSevice();

        if (!TFnom.getText().isEmpty() && !TFrue.getText().isEmpty() && !TFrue.getText().isEmpty()
                && !CBgovernorat.getSelectionModel().isEmpty()
                && !TApath.getText().isEmpty()) {
            fis = new FileInputStream(file);
            LocalDate date = datepicker.getValue();
            java.sql.Date dd = convertToDateViaSqlDate(date);

            Evenement e = new Evenement(TFnom.getText(), s, TFcap.getText(), dd ,heure.getValue().toString(), u.getidUSERByusername(), (InputStream) fis);
            System.out.println(dd);
            System.out.println(heure.getValue().toString());
            idlastevent = es.createEvent(e);
            System.out.println("isdlast event");
            System.out.println(idlastevent);
           es.adddemande(idlastevent, u.getidUSERByusername());
           System.out.println(idlastevent);
        } else {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Champs manquant!");
            alertu.setHeaderText("Veuillez saisir les champs requis !");
            Optional<ButtonType> result = alertu.showAndWait();
        }

//        Alert alertu = new Alert(Alert.AlertType.ERROR);
//        alertu.setTitle("success !");
//        alertu.setHeaderText("félicitationt association créer avec succes !");
//        Optional<ButtonType> result = alertu.showAndWait();
    }

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

}
