package integrationjava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Login;
import Services.EvenementService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.ConnexionBase;
import static utils.ConnexionBase.getInstance;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class Admin_evenementStatsController implements Initializable {
    @FXML
    private BorderPane borderpane;
  
    @FXML
    private AnchorPane parent;
    @FXML
    private Pane Pchart;
    @FXML
    private Label Lna;
    @FXML
    private Label La;
    @FXML
    private BarChart<?, ?> Bchart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    
    UserService us = new UserService();
    EvenementService es = new EvenementService();
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane11;
    @FXML
    private ImageView logout;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button BoutiqueBtn;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle resources) {
           pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        backgroundAnimation();
         
        try {

         
            int nbr_part = es.countparticipant();
            int nbr_mbr = us.countMembres();
            La.setText(String.valueOf((nbr_part * 100) / nbr_mbr) + "%");
            Lna.setText(String.valueOf(((nbr_mbr - nbr_part) * 100) / nbr_mbr) + "%");

            ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
            list.add(new PieChart.Data("% membre participant", nbr_part));
            list.add(new PieChart.Data("% membre non participant", nbr_mbr - nbr_part));
            //list.add(new PieChart.Data("test",8));
            PieChart pie = new PieChart(list);
            //pieRadius = Math.min(contentWidth,contentHeight) / 2;

            Pchart.getChildren().add(pie);
        } catch (Exception e) {
        }
        try {
            ObservableList<Integer> list1 = FXCollections.observableArrayList();
            ObservableList<String> list2 = FXCollections.observableArrayList();
            Connection connection;
            ConnexionBase db = getInstance();
            connection = db.getCnx();
            

            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(d.idU) ,e.nom_evenement FROM demande d INNER JOIN evenement e ON e.id_event=d.idE GROUP by e.id_event");
            ResultSet rs = ps.executeQuery();
            System.out.println("iiiiiii");
            while (rs.next()) {
                EvenementService k = new EvenementService();
                list1.add(rs.getInt(1));
                list2.add(rs.getString(2));
            }
            System.out.println("iiiiiii");
            System.out.println(list1);
            System.out.println(list2);
            XYChart.Series set1 = new XYChart.Series<>();

            for (int k = 0, p = 0; k < list2.size() && p < list1.size(); k++, p++) {

                System.out.println(k);
                set1.getData().add(new XYChart.Data(list2.get(k), list1.get(p)));
            }
            Bchart.getData().addAll(set1);

        } catch (SQLException ex) {
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
    private void button2(ActionEvent event) {
    }


    @FXML
    private void showpie() {
        Pchart.setVisible(true);
        Bchart.setVisible(false);

    }

    @FXML
    private void showdiag() {
        Pchart.setVisible(false);
        Bchart.setVisible(true);

    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionevent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void button3(ActionEvent event) throws IOException {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/demandeevenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void redirectinfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLArticles_especes.fxml"));
        Parent root = loader.load();
        FXMLArticles_especesController acc = loader.getController();
        InformationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectespece(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBackEspece.fxml"));
        Parent root = loader.load();
        FXMLBackEspeceController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectboutique(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduitCategorie.fxml"));
        Parent root = loader.load();
        FXMLProduitCategorieController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }
    
}
