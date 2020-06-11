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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author milim
 */
public class UserevenementController implements Initializable {
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
    private TextField TFnom;
    @FXML
    private JFXComboBox<String> CBgovernorat;
    @FXML
    private JFXComboBox<String> CBvile;
    @FXML
    private TextField TFrue;
    @FXML
    private TextField TFdesc;
    @FXML
    private DatePicker datepicker;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private ImageView IVimage;
     Adresse a = new Adresse();
    //pour le fonction parcourir (image)
    private FileChooser fileChooser;
    public String lien;
    private File file;
    private Image image;
    private FileInputStream fis;
    // pour l'ajout
    int idlastevent = 0;
    UserService ms = new UserService();
    private Stage stage;
    @FXML
    private TableView<Evenement> TVtable;
    @FXML
    private TextField TFrecherche;
     UserService us = new UserService();
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
            Logger.getLogger(UserevenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TFrecherche.textProperty().addListener((observable, oldvalue, newvalue) -> {
            try {
                TVtable.setItems(es.SearshUserEvenements(TFrecherche.getText(), us.getidUSERByusername()));
            } catch (SQLException ex) {
                Logger.getLogger(UserevenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
    private void button1(ActionEvent event) throws IOException {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/ajoutevenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void onAdressConfirmed(ActionEvent event) {
          int id = CBgovernorat.getSelectionModel().getSelectedIndex() + 1;
        System.out.println(id);
        CBvile.setItems(a.getVilleFroGov(id));
    }

    @FXML
    private void Parcourir(ActionEvent event) {
          fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
        Stage stagec = (Stage) parent.getScene().getWindow();
        file = fileChooser.showOpenDialog(stagec);
        if (file != null) {
            lien=file.getPath();
            //desktop.open(file);
            image = new Image(file.toURI().toString());
            IVimage.setImage(image);
            //TApath.setText(file.getAbsolutePath());

        }
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
        FileUploader fu = new FileUploader("localhost/integration/test1.1/web/images"); 
        
        evnt.setImage(fu.upload(lien));

        System.out.println(evnt.getId_event());
        EvenementService es = new EvenementService();
        es.updateEvenement(evnt); // na9es l adresse
        TVtable.setItems(es.getAllUserOwnedEvenements(us.getidUSERByusername())); //refresh
        TFnom.setText("");
        TFdesc.setText("");

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
  Image image = new Image(new FileInputStream("D:\\wamp64\\www\\integration\\test1.1\\web\\images\\"+es.getEvenementImageByID(event.getId_event())));
               // ImageView imgEvt = new ImageView(image);
        System.out.println(event.getId_event());
        IVimage.setImage(image);

    }
      @FXML
    private void deleteselected(ActionEvent event) throws SQLException {
        Evenement e = TVtable.getSelectionModel().getSelectedItem();
        // System.out.println(asso.getId()); 
        EvenementService es = new EvenementService();
        es.deleteEvenementById(e.getId_event());
        TVtable.setItems(es.getAllUserOwnedEvenements(us.getidUSERByusername()));
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
