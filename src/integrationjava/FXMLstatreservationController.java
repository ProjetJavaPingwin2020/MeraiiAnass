/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Services.ServiceFormation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import utils.ConnexionBase;
import static utils.ConnexionBase.getInstance;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLstatreservationController implements Initializable {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private ResultSet res ;
    @FXML
    private BarChart<?, ?> stat;
    @FXML
    private CategoryAxis abs;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button EspecesBtn;
    @FXML
    private ImageView logout;
    @FXML
    private Pane pane4;
    @FXML
    private Label Lhello;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane11;
    @FXML
    private Button stat1;
    @FXML
    private Button addformation;
    @FXML
    private Button listeformateurs;
    @FXML
    private Button listeformations;
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
      

         try {
            ObservableList<Integer> list1 = FXCollections.observableArrayList();
            ObservableList<String> list2 = FXCollections.observableArrayList();
            Connection connection;
            ConnexionBase cnx=getInstance();
           
            connection = cnx.getCnx();

            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(r.idu) ,f.nom FROM reservation r INNER JOIN formation f ON f.id =r.idformation GROUP by f.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ServiceFormation sf = new ServiceFormation();
                list1.add(rs.getInt(1));
                list2.add(rs.getString(2));
            }
            
            System.out.println(list1);
            System.out.println(list2);
            XYChart.Series set1 = new XYChart.Series<>();

            for (int sf = 0, p = 0; sf < list2.size() && p < list1.size(); sf++, p++) {

                System.out.println(sf);
                set1.getData().add(new XYChart.Data(list2.get(sf), list1.get(p)));
            }
            stat.getData().addAll(set1);

        } catch (SQLException ex) {
        }

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
    private void redirectionformation(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }
    
    private void redirectionEvent(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
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
    private void redirectBoutique(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduitCategorie.fxml"));
        Parent root = loader.load();
        FXMLProduitCategorieController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectInformations(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLArticles_especes.fxml"));
        Parent root = loader.load();
        FXMLArticles_especesController acc = loader.getController();
        InformationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectEspece(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBackEspece.fxml"));
        Parent root = loader.load();
        FXMLBackEspeceController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }
    
}
