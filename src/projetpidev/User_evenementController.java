/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Adresse;
import Entity.Evenement;
import Entity.Login;
import Services.EvenementService;
import Services.UserSevice;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class User_evenementController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private TextField TFrecherche;
    @FXML
    private Label Lhello;
    @FXML
    private AnchorPane parent;
    @FXML
    TableView<Evenement> TVtable;
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

    //adresse
    Adresse a = new Adresse();
    @FXML
    private TextField TFdesc;

    UserSevice us = new UserSevice();
    private FileChooser fileChooser;
    private File file;
    private Image image;
    private FileInputStream fis;
    @FXML
    private DatePicker datepicker;
    @FXML
    private JFXTimePicker heure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Lhello.setText("Bonjour " + Login.getUsername());
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

        TableColumn nom = new TableColumn("Nom");
        TableColumn date = new TableColumn("DateCreation");
        TableColumn heure = new TableColumn("Heure");
        TableColumn adresse = new TableColumn("Adresse");
        TableColumn d = new TableColumn("Description");
        TVtable.getColumns().addAll(nom, date, heure, adresse, d);

        EvenementService es = new EvenementService();

//        Image image = as.get
        nom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_evenement"));
        date.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<Evenement, String>("heure"));
        adresse.setCellValueFactory(new PropertyValueFactory<Evenement, String>("adresse"));
        d.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));

        try {
            TVtable.setItems(es.getAllUserOwnedEvenements(us.getidUSERByusername()));
        } catch (SQLException ex) {
            Logger.getLogger(User_evenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TFrecherche.textProperty().addListener((observable, oldvalue, newvalue) -> {
            try {
                TVtable.setItems(es.SearshUserEvenements(TFrecherche.getText(), us.getidUSERByusername()));
            } catch (SQLException ex) {
                Logger.getLogger(User_evenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    void button1(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/projetpidev/add_evenement.fxml")));
        stage.setScene(scene);

    }

    @FXML
    private void button2(ActionEvent event) {
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
    private void displayselected() throws Exception {
        Evenement event = TVtable.getSelectionModel().getSelectedItem();
        EvenementService es = new EvenementService();
        TFnom.setText(event.getNom_evenement());
        TFdesc.setText(String.valueOf(event.getDescription()));
        datepicker.setValue(convertToLocalDateViaMilisecond(event.getDate()));
        //https://howtodoinjava.com/java/date-time/java-localtime/
        LocalTime localTimeObj = LocalTime.parse(event.getHeure());
        heure.setValue(localTimeObj);

        System.out.println(event.getId_event());
        IVimage.setImage(es.getEvenementImageByID(event.getId_event()));

    }

    @FXML
    private void onAdressConfirmed(ActionEvent event) {
        int id = CBgovernorat.getSelectionModel().getSelectedIndex() + 1;
        System.out.println(id);
        CBvile.setItems(a.getVilleFroGov(id));
    }

    @FXML
    private void modifySelected() throws Exception {
        fis = new FileInputStream(file);
        Evenement evnt = TVtable.getSelectionModel().getSelectedItem();

        a.setGov(CBgovernorat.getSelectionModel().getSelectedItem());
        a.setRue(TFrue.getText());
        a.setVille(CBvile.getSelectionModel().getSelectedItem());
        System.out.println(a.toString());
        String s = a.toString();

        evnt.setNom_evenement(TFnom.getText());
        evnt.setDescription(TFdesc.getText());
        evnt.setHeure(heure.getValue().toString());
        LocalDate date = datepicker.getValue();
        java.sql.Date dd = convertToDateViaSqlDate(date);
        evnt.setDate(dd);
        evnt.setAdresse(s);
        evnt.setImage((InputStream) fis);

        System.out.println(evnt.getId_event());
        EvenementService es = new EvenementService();
        es.updateEvenement(evnt); // na9es l adresse
        TVtable.setItems(es.getAllUserOwnedEvenements(us.getidUSERByusername())); //refresh
        TFnom.setText("");
        TFdesc.setText("");

    }

    @FXML
    private void deleteselected() throws Exception {
        Evenement event = TVtable.getSelectionModel().getSelectedItem();
        // System.out.println(asso.getId()); 
        EvenementService es = new EvenementService();
        es.deleteEvenementById(event.getId_event());
        TVtable.setItems(es.getAllUserOwnedEvenements(us.getidUSERByusername()));

    }

    @FXML
    private void browse(ActionEvent event) throws Exception {

        fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
        Stage stagec = (Stage) parent.getScene().getWindow();
        file = fileChooser.showOpenDialog(stagec);
        if (file != null) {
            //desktop.open(file);
            image = new Image(file.toURI().toString());
            IVimage.setImage(image);
            //TApath.setText(file.getAbsolutePath());

        }
    }

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    //https://www.baeldung.com/java-date-to-localdate-and-localdatetime <3<3
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
