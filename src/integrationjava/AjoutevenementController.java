/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Adresse;
import Entity.Evenement;
import Services.EvenementService;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutevenementController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane parent;
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
    private Label Lasso;
    @FXML
    private TextField TFnom;
    @FXML
    private TextField TFrue;
    @FXML
    private TextField TFcap;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextArea TApath;
    @FXML
    private ImageView IVimage;

    Adresse a = new Adresse();
    //pour le fonction parcourir (image)
    public String lien;
    private FileChooser fileChooser;
    private File file;
    private Image image;
    private FileInputStream fis;
    // pour l'ajout
    int idlastevent = 0;
    UserService ms = new UserService();
    private Stage stage;
    @FXML
    private JFXComboBox<String> CBgovernorat;
    @FXML
    private JFXComboBox<String> CBvile;
    @FXML
    private JFXTimePicker heure;

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
        // TODO
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
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionevent(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/evenement.fxml")));
        stage.setScene(scene);

    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void button2(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/userevenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void button3(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/get_all_evenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void button1(ActionEvent event) {
    }

    @FXML
    private void Parcourir(ActionEvent event) throws Exception {

        fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
        Stage stagec = (Stage) parent.getScene().getWindow();
        file = fileChooser.showOpenDialog(stagec);
        if (file != null) {
           this.lien=file.getPath();
         
        }
    }

    @FXML
    private void onCreerClicked(ActionEvent event) throws SQLException, FileNotFoundException, ProtocolException, IOException {
        a.setGov(CBgovernorat.getSelectionModel().getSelectedItem());
        a.setRue(TFrue.getText());
        a.setVille(CBvile.getSelectionModel().getSelectedItem());
        TApath.setText(lien);
        System.out.println(a.toString());
        String s = a.toString();
        EvenementService es = new EvenementService();
        UserService u = new UserService();

        if (verifChamps()
                && verifNom()
                && verifDescription()
                && verifDate()) {
            fis = new FileInputStream(file);
            LocalDate date = datepicker.getValue();
            java.sql.Date dd = convertToDateViaSqlDate(date);
            FileUploader fu = new FileUploader("localhost/integration/test1.1/web/images"); 
            Evenement e = new Evenement(TFnom.getText(), s, TFcap.getText(), dd, heure.getValue().toString(), u.getidUSERByusername(),fu.upload(lien));
            System.out.println(dd);
            System.out.println(heure.getValue().toString());
            idlastevent = es.createEvent(e);
            System.out.println("isdlast event");
            System.out.println(idlastevent);
            es.adddemande(idlastevent, u.getidUSERByusername());
            System.out.println(idlastevent);
        }

    }

    private boolean verifChamps() {
        if (TFnom.getText().isEmpty() 
             | TFrue.getText().isEmpty() 
             | TFcap.getText().isEmpty()
             | CBgovernorat.getSelectionModel().isEmpty()
             | CBvile.getSelectionModel().isEmpty()
             | CBvile.getSelectionModel().isEmpty()
             | TApath.getText().isEmpty()) {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Champs manquant!");
            alertu.setHeaderText("Veuillez saisir les champs requis !");
            Optional<ButtonType> result = alertu.showAndWait();

            return false;
        }
        return true;
    }

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @FXML
    private void onAdressConfirmed(ActionEvent event) {
        int id = CBgovernorat.getSelectionModel().getSelectedIndex() + 1;
        System.out.println(id);
        CBvile.setItems(a.getVilleFroGov(id));
    }

    private boolean verifDescription() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(TFcap.getText());
        if (m.find() && m.group().equals(TFcap.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier la description de l'evénement");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifDate() {
        int day = datepicker.getValue().getDayOfMonth();
        int month = datepicker.getValue().getMonthValue();
        int year = datepicker.getValue().getYear() - 1900;
        Date d = new Date(year, month, day);
        Date s = new Date(LocalDate.now().getYear() - 1900, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        if (d.before(s)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("La date entrée est inférieure à la date actuelle");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    private boolean verifNom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(TFnom.getText());
        if (m.find() && m.group().equals(TFnom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom de l'evenement");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void redirectionBoutique(ActionEvent event) {
    }

    @FXML
    private void redirectionInformation(ActionEvent event) {
    }

    @FXML
    private void redirectionEspece(ActionEvent event) {
    }

}
