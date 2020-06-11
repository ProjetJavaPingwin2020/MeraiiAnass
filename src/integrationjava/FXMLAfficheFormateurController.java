/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Formateur;
import Services.ServiceFormateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import utils.ConnexionBase;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLAfficheFormateurController implements Initializable {

    @FXML
    TableView<Formateur> table;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

    /* @FXML
     private TableColumn<Formateur, String> nom;
     @FXML
     private TableColumn<Formateur, String> prenom;*/
    public ObservableList<Formateur> cls = FXCollections.observableArrayList(); //on utilise ObservableList pcq on a un tableView
    @FXML
    private ImageView retour;

    @FXML
    private ImageView image;
    private FileInputStream fis;
    public static Formateur fo;
    private FileChooser fileChooser;
  //  private File file;

  
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
    private Button listeformations;
    @FXML
    private Button listeformateurs;
    @FXML
    private Button addformation;
    @FXML
    private Button stat;
   File pDir;
    File pfile;
    int file = 0;
    String lien;
    int c;

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
       
        TableColumn nom = new TableColumn("Nom");
        TableColumn prenom = new TableColumn("Prenom");
      
      

        table.getColumns().addAll(nom, prenom);

        ServiceFormateur sf = new ServiceFormateur();
        nom.setCellValueFactory(new PropertyValueFactory<Formateur, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Formateur, String>("prenom"));
     
       

        try {
            table.setItems(sf.getAllFormateurs());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficheFormateurController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Formateur.fxml"));
        Parent root = loader.load();
        FormateurController acc = loader.getController();
        retour.getScene().setRoot(root);
    }

    @FXML
    private void display(MouseEvent event) throws SQLException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        Formateur f = table.getSelectionModel().getSelectedItem();

        ServiceFormateur sf = new ServiceFormateur();
        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());
  Image img = new Image(new FileInputStream("C:\\wamp64\\www\\integration\\test1.1\\web\\imageFormateurs\\"+sf.getFormateurImageByID(f.getId())));
            //   ImageView imgEvt = new ImageView(image);
        image.setImage(img);
        System.out.println(f.getNom());

    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    private void redirectionformation(ActionEvent event) throws IOException {
        /*    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
         Parent root = loader.load();
         FXMLFormationController acc = loader.getController();
         FormationsBtn.getScene().setRoot(root);*/
    }

    @FXML
    private void listeformations(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormation.fxml"));
         Parent root = loader.load();
         FXMLAfficherFormationController acc = loader.getController();
         listeformations.getScene().setRoot(root);
    }

    @FXML
    private void listeformateurs(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormateur.fxml"));
         Parent root = loader.load();
         FXMLAfficheFormateurController acc = loader.getController();
         listeformateurs.getScene().setRoot(root);
    }

    @FXML
    private void addformation(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
         Parent root = loader.load();
         FXMLFormationController acc = loader.getController();
         addformation.getScene().setRoot(root);

    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLstatreservation.fxml"));
         Parent root = loader.load();
         FXMLstatreservationController acc = loader.getController();
         stat.getScene().setRoot(root);

    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

}
