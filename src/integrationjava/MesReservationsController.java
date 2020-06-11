/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Formation;
import Entity.FosUser;
import Entity.Login;
import Entity.Reservation;
import Services.ReservationService;
import Services.ServiceFormation;
import Services.UserService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.ConnexionBase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
//import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
//import com.itextpdf.text.pdf.qrcode.WriterException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
//import com.google.zxing.common.ByteMatrix;
//import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import static javax.swing.text.html.parser.DTDConstants.MS;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class MesReservationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    UserService us = new UserService();
   private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    ReservationService sr = new ReservationService();
    ServiceFormation sf = new ServiceFormation();
    private ObservableList<Reservation> data;
    @FXML
    private AnchorPane parent;
    @FXML
    private ListView<HBox> list;
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
    private Button FormationsBtn;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button EspecesBtn;
    private Button sms;
  
    @FXML
    private Label Ltest;
   
    private Formation form;
    @FXML
    private Button mesres;
    @FXML
    private Button listeformations;
    @FXML
    private Button mescadeaux;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView panier;
        UserService usrser=new UserService();
    @FXML
    private Text username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        try {
             username.setText(usrser.getloginusername());
           

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        backgroundAnimation();
        try {
         //   Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {
        }
     /*   String s="";
                FosUser fos= new FosUser();

        try {
            s = us.getlogin();
                        fos = us.getUSERById(Integer.parseInt(s));

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Login.setUsername(fos.getUsername()); yekhdmmmmmmmmmmmmmm*/ 
        
      /*    data = FXCollections.observableArrayList();
        cnx = ConnexionBase.getInstance().getCnx();
         TableColumn idformation = new TableColumn("Id de la Formation ");
          TableColumn etat = new TableColumn("etat ");
           TableColumn avis = new TableColumn("avis ");
          idformation.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idformation"));
           etat.setCellValueFactory(new PropertyValueFactory<Reservation,String>("etat"));
            avis.setCellValueFactory(new PropertyValueFactory<Reservation,String>("avis"));
           table.getColumns().addAll(idformation,etat,avis);
        try {
            table.setItems(sr.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(MesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
       // System.out.println(location.toString());
     //   System.out.println(this.getClass().getResource(MEDIA_URL).toExternalForm());
//        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));
//        mediaPlayer.setAutoPlay(true);
//        media.setMediaPlayer(mediaPlayer);
       // engine = webView.getEngine();
        // engine.load("https://web.facebook.com/tesnime.ammar");
        
        
           
   /*   pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
       pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");

        backgroundAnimation();
        try {
            Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {
        }
     
        ServiceFormation sf = new ServiceFormation();
        try {
            ObservableList<ImageView> ivs = sf.getAllFormationsImages();
            System.out.println(Login.getId_user());
            ObservableList<Formation> als = sf.getMyRes(Login.getId_user());
            ObservableList<Label> labels = FXCollections.observableArrayList();

            for (int i = 0; i < als.size(); i++) {
             String S = als.get(i).getNom() +"\n" + als.get(i).getType()+"\n" + als.get(i).getDate()+"\n" +als.get(i).getHeure()+"\n" +als.get(i).getLieu()+"\n" +als.get(i).getDescription()+"\n" +als.get(i).getNbrplace();
                System.out.println(S);
               labels.add(new Label(S));
              //  labels.add(new Label(als.get(i).getNom()));
              
                
            }
            final double MAX_FONT_SIZE = 15.0;

            ObservableList<HBox> hbs = FXCollections.observableArrayList();
            HBox hb = new HBox();
            HBox hb2 = new HBox();
            HBox hb3 = new HBox();

            for (int i = 0; i < ivs.size(); i++) {
                //hb.getChildren().addAll(ivs.get(0),labels.get(0));
               labels.get(i).setFont(new Font(MAX_FONT_SIZE));
                labels.get(i).setPadding(new Insets(50, 0, 0, 0));
                hbs.add(new HBox(ivs.get(i), labels.get(i)));
 //hbs.add(new HBox(ivs.get(i)));
            }
            for (int i = 0; i < ivs.size(); i++) {
                hbs.get(i).setSpacing(80);
            }

            list.setItems(hbs);

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        // TODO
        */
        
         ServiceFormation sf = new ServiceFormation();
        try {
            ObservableList<ImageView> ivs = sf.getAllFormationsImages();

            ObservableList<Formation> als = sf.getAllFormations();
            ObservableList<Label> labels = FXCollections.observableArrayList();

            for (int i = 0; i < als.size(); i++) {
             String S = als.get(i).getNom() +"\n" + als.get(i).getDate()+"\n" +als.get(i).getHeure()+"\n" +als.get(i).getLieu()+"\n" +als.get(i).getDescription()+"\n" +als.get(i).getNbrplace();
              
               labels.add(new Label(S));
              //  labels.add(new Label(als.get(i).getNom()));
              
                
            }
            final double MAX_FONT_SIZE = 15.0;

            ObservableList<HBox> hbs = FXCollections.observableArrayList();
            HBox hb = new HBox();
            HBox hb2 = new HBox();
            HBox hb3 = new HBox();

            for (int i = 0; i < ivs.size(); i++) {
                //hb.getChildren().addAll(ivs.get(0),labels.get(0));
                labels.get(i).setFont(new Font(MAX_FONT_SIZE));
                labels.get(i).setPadding(new Insets(50, 0, 0, 0));
                hbs.add(new HBox(ivs.get(i), labels.get(i)));

            }
            for (int i = 0; i < ivs.size(); i++) {
                hbs.get(i).setSpacing(80);
            }
//                Label l2 = new Label();
//                l2.setText(als.get(2).getNom_association());
//                hb2.getChildren().addAll(ivs.get(2),labels.get(2));
//                hbs.add(hb2);
//                
//                hb3.getChildren().addAll(ivs.get(3),l);
//                hbs.add(hb3);

//            LVlist.setItems(as.getAllAssociationImages());
            list.setItems(hbs);

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void select(MouseEvent event) throws SQLException {
    //    ServiceFormation sf = new ServiceFormation();
      //  HBox hb = new HBox();
        // les 3 lounenin hb = list.getSelectionModel().getSelectedItem();
      //  Node nodeOut = hb.getChildren().get(1);
     /*   if (nodeOut instanceof Label) {
                System.out.println(Ltest.getText());
            Ltest.setText(((Label) nodeOut).getText());
            int id = sf.getidFormationByName(Ltest.getText());

        }*/
         ServiceFormation sf = new ServiceFormation();
        HBox hb = new HBox();
        hb = list.getSelectionModel().getSelectedItem();
        Node nodeOut = hb.getChildren().get(1);
        if (nodeOut instanceof Label) {
                System.out.println(Ltest.getText());
            Ltest.setText(((Label) nodeOut).getText());
            int id = sf.getidFormationByName(Ltest.getText());
    }
    }

   @FXML
    private void sms(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SMS.fxml"));
        Parent root = loader.load();
        SMSController acc = loader.getController();
        mesres.getScene().setRoot(root);
    }

    

    private void mesres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SMS.fxml"));
        Parent root = loader.load();
        SMSController acc = loader.getController();
        sms.getScene().setRoot(root);
    }

    @FXML
    private void listeformations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        listeformations.getScene().setRoot(root);
    }

    @FXML
    private void mescadeaux(ActionEvent event) throws SQLException, DocumentException, FileNotFoundException, IOException {
      
  ServiceFormation sf = new ServiceFormation();
         int id = sf.getidFormationByName(Ltest.getText());
        if(sf.Reservationexiste(id, us.getidUSERByusername())){
          QrCodeController q = new QrCodeController();
        //  int id = sf.getidFormationByName(Ltest.getText());
        Formation f = new Formation();
        f=sf.findbyid(id);
        q.ini(f);
         /* Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("QrCode.fxml")));
            stage.setScene(scene);*/
        
    } else {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Pas de QrCodeCadeau!");
            alertu.setHeaderText("Vous n'etes pas inscrit à cette formation !");
            Optional<ButtonType> result = alertu.showAndWait();
        }
    
       
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
       FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        Parent root = loader.load();
        EvenementController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionBoutique(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontProduit.fxml"));
        Parent root = loader.load();
        FXMLFrontProduitController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }

    @FXML
    private void panier(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPanier.fxml"));
        Parent root = loader.load();
        FXMLPanierController acc = loader.getController();
        panier.getScene().setRoot(root);
    }

    @FXML
    private void redirectionInformation(ActionEvent event) {
    }

    @FXML
    private void redirectionEspece(ActionEvent event) {
    }
  
}

   

   
       
    

   


    
