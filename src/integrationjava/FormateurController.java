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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FormateurController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

    @FXML
    private Button ajouterBtn;
    private Button afficherBtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private Button rechercherBtn;
    @FXML
    private ImageView nomImage;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private ImageView retour;
    @FXML
    private TextArea path;
    @FXML
    private Button modifierBtn;
    //pour le fonction parcourir (image)
    private FileChooser fileChooser;
    private File fill;
    private Image image;
    private FileInputStream fis;
    private Stage stage;
    @FXML
    private ImageView logout;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Label Lhello;
    @FXML
    private Button listeformations;
    @FXML
    private Button listeformateurs;
    @FXML
    private Button addformation;
    @FXML
    private Button stat;
   private String lien;

    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    // private Object parent;
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
    private void ajouter(ActionEvent event) throws FileNotFoundException, ProtocolException, IOException {
          if (verifChamps() && verifNom()&& verifPrenom()) {
            String Nom = nom.getText();
            String Prenom = prenom.getText();
            //String Img = img.getText();
           // String NomImage = lien;
            

            System.out.println(Nom);
            System.out.println(Prenom);
            //System.out.println(Img);
          //  System.out.println(NomImage);
         //      fis = new FileInputStream(fill);
         //   Formateur u = new Formateur(Nom, Prenom, (InputStream) fis);
            FileUploader fu =new FileUploader("localhost/integration/test1.1/web/imageFormateurs");
            Formateur u = new Formateur(Nom, Prenom, fu.upload(lien));
            int status = ServiceFormateur.ajouter(u); //ligne hedhi tekhou valeur retournée par insert (eli heya st sammineha status o besh naamlou beha comparaison)

            if (status > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajouter formateur");
                alert.setHeaderText("Dialogue information");
                alert.setContentText("Ajout avec succés");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ajouter formateur");
                alert.setHeaderText("Dialogue ERREUR");
                alert.setContentText("Un probléme est survenu");

                alert.showAndWait();
            }

//s        }
    }
    }
    @FXML
    private void modifier(ActionEvent event) throws ProtocolException, IOException {
         String Nom = nom.getText();
        String Prenom = prenom.getText();
      //  String Img = img.getText();
          FileUploader fu = new FileUploader("localhost/integration/test1.1/web/imageFormateurs");
          
         //Formateur f = new Formateur(Nom, Prenom, (InputStream) fis);
         Formateur f = new Formateur(Nom, Prenom, fu.upload(lien));
        int status = ServiceFormateur.update(f, Nom);
        System.out.println(status);
        if (status == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Modifier Formateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Modification avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Modifier Formateur");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
    }

    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormateur.fxml"));
        Parent root = loader.load();
        FXMLAfficheFormateurController acc = loader.getController();
        afficherBtn.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        String Nom = nom.getText(); //yekhou nom eli aatithoulou besh yfasakh bih

        int status = ServiceFormateur.delete(Nom);
        if (status > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Supprimer Formateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Supprimer Formateur");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();

        }
        nom.clear();
        prenom.clear();
        //img.clear();
        // image.clear();
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
        String name = nom.getText();

        Formateur f = ServiceFormateur.getFormateur(name);

        nom.setText((f.getNom()));
        prenom.setText((f.getPrenom()));

        //img.setText(f.getImg());
        //nomImage.setText(f.getNomImage());
    }

    @FXML
    private void AddImage(MouseEvent event) throws MalformedURLException {
        
        
         fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
        //Stage stagec = (Stage) parent.getScene().getWindow();
        //fill = fileChooser.showOpenDialog(stagec);
        Window stage = null;
        fill = fileChooser.showOpenDialog(stage);
        if (fill != null) {
           this.lien=fill.getPath();

        }
        
    
    }

    @FXML
    private void Retour(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));
        Parent root = loader.load();
        FXMLMenuController acc = loader.getController();
        retour.getScene().setRoot(root);
    }

    private boolean verifChamps() {
        if (nom.getText().isEmpty() | prenom.getText().isEmpty() | path.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean verifNom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nom.getText());
        if (m.find() && m.group().equals(nom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom du formateur");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifPrenom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(prenom.getText());
        if (m.find() && m.group().equals(prenom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le prenom du formateur");
            alert.showAndWait();
            return false;
        }
    }

    private void redirection(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
         Parent root = loader.load();
         FXMLFormationController acc = loader.getController();
         FormationsBtn.getScene().setRoot(root);

    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionformation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
         Parent root = loader.load();
         FXMLFormationController acc = loader.getController();
         FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void listeformations(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormation.fxml"));
         Parent root = loader.load();
         FXMLAfficherFormationController acc = loader.getController();
         listeformations.getScene().setRoot(root);
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
    private void listeformateurs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormateur.fxml"));
        Parent root = loader.load();
        FXMLAfficheFormateurController acc = loader.getController();
        listeformateurs.getScene().setRoot(root);

    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

}
