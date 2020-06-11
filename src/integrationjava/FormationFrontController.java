/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Commande;
import Entity.Formation;
import Entity.FosUser;
import Entity.Login;
import Entity.Reservation;
import Services.ServiceFormation;
import Services.UserService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.jfoenix.svg.SVGGlyphLoader.clear;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FormationFrontController implements Initializable {

    @FXML
    private Button reserver;
    UserService us = new UserService();
    @FXML
    private Label Lhello;

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
    private Pane pane4;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane11;

    @FXML
    private Label Ltest;
    @FXML
    private AnchorPane parent;
    @FXML
    private ListView<HBox> LVlist;
    @FXML
    private Label alerte;
    @FXML
    private Button acceder;
    @FXML
    private TextArea alertee;
    @FXML
    private ImageView panier;
    @FXML
    private ImageView logout;
      public static int idformation;
    
    @FXML
    private Button mesres;
    @FXML
    private Button mescadeaux;
    @FXML
    private Button listeformations;
    @FXML
    private Text username;
    UserService usrser=new UserService();
    @FXML
    private ImageView pdf;
    private Formation formation;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        String s="";
                FosUser fos= new FosUser();
                
        try {
             username.setText(usrser.getloginusername());
            s = us.getlogin();
                        fos = us.getUSERById(Integer.parseInt(s));

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Login.setUsername(fos.getUsername());
        Login.setId_user(fos.getId());
        backgroundAnimation();
        try {
         //   Lhello.setText("Bonjour " + Login.getUsername());
        } catch (Exception e) {
        }
        ServiceFormation sf = new ServiceFormation();
        try {
            ObservableList<ImageView> ivs = sf.getAllFormationsImages();

            ObservableList<Formation> als = sf.getAllFormations();
            ObservableList<Label> labels = FXCollections.observableArrayList();

            for (int i = 0; i < als.size(); i++) {
                 //String S = als.get(i).getNom() +"\n" + als.get(i).getDate()+"\n" +als.get(i).getHeure()+"\n" +als.get(i).getLieu()+"\n" +als.get(i).getDescription()+"\n" +als.get(i).getNbrplace();
               //   String S = als.get(i).getNbrplace()+"\n"+als.get(i).getNom() ;
                
              labels.add(new Label(als.get(i).getNom()));
           
            }
            final double MAX_FONT_SIZE = 15.0;

            ObservableList<HBox> hbs = FXCollections.observableArrayList();
            HBox hb = new HBox();
            HBox hb2 = new HBox();
            HBox hb3 = new HBox();

            for (int i = 0; i < ivs.size(); i++) {
                //hb.getChildren().addAll(ivs.get(0),labels.get(0));
                labels.get(i).setFont(new Font(MAX_FONT_SIZE));
                labels.get(i).setPadding(new Insets(70, 0, 0, 0));
                hbs.add(new HBox(ivs.get(i), labels.get(i)));
                

            }
            for (int i = 0; i < ivs.size(); i++) {
                hbs.get(i).setSpacing(80);
            }

            LVlist.setItems(hbs);

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
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
    /*private void select(MouseEvent event) throws SQLException {
     ServiceFormation sf = new ServiceFormation();
     HBox hb = new HBox();
     hb = list.getSelectionModel().getSelectedItem();
     Node nodeOut = hb.getChildren().get(1);
     if (nodeOut instanceof Label) {

     Ltest.setText(((Label) nodeOut).getText());
     int id = sf.getidFormationByName(Ltest.getText());

     if (sf.Reservationexiste(id, us.getidUSERByusername())) {
     alerte.setText("Vous avez déjà réservé dans cette formation !");
     reserver.setVisible(false);
     } else {
     reserver.setVisible(true);
                
     }
     }
     }*/
    /*   private void display(MouseEvent event) throws SQLException {
     ServiceFormation sf = new ServiceFormation();
     HBox hb = new HBox();
     hb = LVlist.getSelectionModel().getSelectedItem();
     Node nodeOut = hb.getChildren().get(1);
     if (nodeOut instanceof Label) {

     Ltest.setText(((Label) nodeOut).getText());
     int id = sf.getidFormationByName(Ltest.getText());

     if (sf.Reservationexiste(id, us.getidUSERByusername())) {
     reserver.setVisible(false);
     alerte.setText("Vous avez déjà réservé dans cette formation !");
     } else {
     reserver.setVisible(true);
     }
     }
     }
     */
    //  private void reserver(ActionEvent event) throws SQLException, IOException {
       /*    ServiceFormation sf = new ServiceFormation();
     if (Ltest.getText().equals("")) {
     Alert alertu = new Alert(Alert.AlertType.ERROR);
     alertu.setTitle("pas de choix!");
     alertu.setHeaderText("vous dever choisir une formation !");
     Optional<ButtonType> result = alertu.showAndWait();
     }
     System.out.println(Ltest.getText());
     int id = sf.getidFormationByName(Ltest.getText());
     //  System.out.println(Ltest.getText());
     Login.setNomformation(Ltest.getText());
     if (!sf.Reservationexiste(id, us.getidUSERByusername())) {   
     sf.addReservation(id, us.getidUSERByusername());
     }
     Node source = (Node) event.getSource();
     Stage stage = (Stage) source.getScene().getWindow();
     Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MesReservations.fxml")));
     stage.setScene(scene);
     */
    /*      ServiceFormation sf = new ServiceFormation();
     if (Ltest.getText().equals("")) {
     Alert alertu = new Alert(Alert.AlertType.ERROR);
     alertu.setTitle("pas de choix!");
     alertu.setHeaderText("vous dever choisir une formation !");
     Optional<ButtonType> result = alertu.showAndWait();
     }
     int id = sf.getidFormationByName(Ltest.getText());
     Login.setNomformation(Ltest.getText());
     if (!sf.Reservationexiste(id, us.getidUSERByusername())) {
     sf.addReservation(id, us.getidUSERByusername());
     }
     Node source = (Node) event.getSource();
     Stage stage = (Stage) source.getScene().getWindow();
     Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MesReservations.fxml")));
     stage.setScene(scene);
     }*/

   

    /* private void select(MouseEvent event) throws SQLException {
     ServiceFormation sf = new ServiceFormation();
     HBox hb = new HBox();
     hb = list.getSelectionModel().getSelectedItem();
     Node nodeOut = hb.getChildren().get(1);
     if (nodeOut instanceof Label) {

     Ltest.setText(((Label) nodeOut).getText());
     int id = sf.getidFormationByName(Ltest.getText());

     if (sf.Reservationexiste(id, us.getidUSERByusername())) {
     alerte.setText("Vous avez déjà réservé dans cette formation !");
     reserver.setVisible(false);
     } else {
     reserver.setVisible(true);
                
     }
     }
     }*/
    @FXML
    private void select(MouseEvent event) throws SQLException {
       /* ServiceFormation sf = new ServiceFormation();
        HBox hb = new HBox();
        hb = LVlist.getSelectionModel().getSelectedItem();
        Node nodeOut = hb.getChildren().get(1);
        if (nodeOut instanceof Label) {
            Ltest.setText(((Label) nodeOut).getText());                          
            System.out.println(sf.getidFormationByName(Ltest.getText()));
            int id = sf.getidFormationByName(Ltest.getText());
            System.out.println(us.getidUSERByusername());
           if (((sf.Reservationexiste(id, us.getidUSERByusername()) && (sf.getNbrPlace(id) == 0)) || (sf.Reservationexiste(id, us.getidUSERByusername()) && (sf.getNbrPlace(id) > 0))))  {
               
                Alert alertu = new Alert(Alert.AlertType.ERROR);
                alertu.setTitle("Choisissez une autre formation !");
                alertu.setHeaderText("Vous avez déjà réservé dans cette formation !");
                alerte.setText("Le nombre de places disponibles est" +sf.getNbrPlace(id)+ "places !");
                Optional<ButtonType> result = alertu.showAndWait();
                reserver.setVisible(false);
                acceder.setVisible(true);
            } else {
                alerte.setText("Le nombre de places disponibles est"  +sf.getNbrPlace(id)+ "places !");
                reserver.setVisible(true);
                acceder.setVisible(false);
mtei lkhra
            }
        }*/
        ServiceFormation sf = new ServiceFormation();
        HBox hb = new HBox();
        hb = LVlist.getSelectionModel().getSelectedItem();
        Node nodeOut = hb.getChildren().get(1);
        if (nodeOut instanceof Label) {
            Ltest.setText(((Label) nodeOut).getText());                          
            System.out.println(sf.getidFormationByName(Ltest.getText()));
            int id = sf.getidFormationByName(Ltest.getText());
            System.out.println(us.getidUSERByusername());
         if (((sf.Reservationexiste(id, us.getidUSERByusername()) && (sf.getNbrPlace(id) == 0)) || (sf.Reservationexiste(id, us.getidUSERByusername()) && (sf.getNbrPlace(id) > 0))))  {
                // alerte.setText("Vous avez déjà réservé dans cette formation !");
                Alert alertu = new Alert(Alert.AlertType.ERROR);
                alertu.setTitle("Choisissez une autre formation !");
                alertu.setHeaderText("Vous avez déjà réservé dans cette formation !");
                alerte.setText("Le nombre de places disponibles est" +sf.getNbrPlace(id)+ "places !");
                Optional<ButtonType> result = alertu.showAndWait();
                reserver.setVisible(false);
                acceder.setVisible(true);
            } else {
               alerte.setText("Le nombre de places disponibles est" +sf.getNbrPlace(id)+ "places !");
                reserver.setVisible(true);
                acceder.setVisible(false);

            }
        }
    }

  /*  @FXML
    private void reserver(ActionEvent event) throws IOException, SQLException {
        ServiceFormation sf = new ServiceFormation();
        
        if (Ltest.getText().equals("")) {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("pas de choix!");
            alertu.setHeaderText("vous dever choisir une formation !");
            Optional<ButtonType> result = alertu.showAndWait();
        }
        else
        {
            System.out.println(Ltest.getText());
        int id = sf.getidFormationByName(Ltest.getText());
        Login.setNomformation(Ltest.getText());
        
        if (((sf.getNbrPlace(id) == 0)  && (!sf.Reservationexiste(id, us.getidUSERByusername())))) {
            
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Pas de choix!");
            alertu.setHeaderText("Formation complète! Le nombre de places maximum est atteint!");
       //     alertee.setText("Le nombre de places disponibles est" + sf.getNbrPlace(id)+"places !");
            Optional<ButtonType> result = alertu.showAndWait();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FormationFront.fxml")));
            stage.setScene(scene);
        } 
        else if((((sf.getNbrPlace(id)==0)) && (sf.Reservationexiste(id, us.getidUSERByusername()))) || ((sf.getNbrPlace(id) > 0) && (sf.Reservationexiste(id, us.getidUSERByusername())))){
            String s="";
                   try {
            s = us.getlogin();
        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
          //  sf.addReservation(id, Integer.parseInt(s));
            // sf.decrementnbr(id);
                    acceder.setOnMouseClicked(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent a) {
                    HBox f =LVlist.getItems().get(LVlist.getSelectionModel().getSelectedIndex());
                    try {
                        int id = sf.getidFormationByName(Ltest.getText());
                    } catch (SQLException ex) {
                        Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Parent AnchorPane = FXMLLoader.load(getClass().getResource("DetailFormation.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(AnchorPane);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
                    }    }
            }) ;
          //  Node source = (Node) event.getSource();
            //Stage stage = (Stage) source.getScene().getWindow();
          // loula Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MesReservations.fxml")));
          //  Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DetailFormation.fxml")));
           // stage.setScene(scene);
        }else if ((sf.getNbrPlace(id) > 0) && (!sf.Reservationexiste(id, us.getidUSERByusername()))) {
            String s="";
                   try {
            s = us.getlogin();
        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
              //  int nbrres = 0;
            sf.addReservation(id, Integer.parseInt(s));
       //     Reservation r = null;
//            r.getNbrres();
  //          nbrres++;
    //        r.setNbrres(nbrres);
             sf.nbrres(id);
             sf.decrementnbr(id);
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MesReservations.fxml")));
            stage.setScene(scene);
        }
        }    
    }
*/
    
      @FXML
    private void reserver(ActionEvent event) throws IOException, SQLException {
        ServiceFormation sf = new ServiceFormation();
        
        if (Ltest.getText().equals("")) {
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("pas de choix!");
            alertu.setHeaderText("vous dever choisir une formation !");
            Optional<ButtonType> result = alertu.showAndWait();
        }
        else
        {
            System.out.println(Ltest.getText());
        int id = sf.getidFormationByName(Ltest.getText());
        Login.setNomformation(Ltest.getText());
       if (((sf.getNbrPlace(id) == 0)  && (!sf.Reservationexiste(id, us.getidUSERByusername())))){

            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Pas de choix!");
            alertu.setHeaderText("Formation complète! Le nombre de places maximum est atteint!");
            Optional<ButtonType> result = alertu.showAndWait();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FormationFront.fxml")));
            stage.setScene(scene);
        }  else if((((sf.getNbrPlace(id)==0)) && (sf.Reservationexiste(id, us.getidUSERByusername()))) || ((sf.getNbrPlace(id) > 0) && (sf.Reservationexiste(id, us.getidUSERByusername())))){
            String s="";
                   try {
            s = us.getlogin();
        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     acceder.setOnMouseClicked(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent a) {
                    HBox f =LVlist.getItems().get(LVlist.getSelectionModel().getSelectedIndex());
                     
                    try {
                        int id = sf.getidFormationByName(Ltest.getText());
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Parent AnchorPane = FXMLLoader.load(getClass().getResource("MesReservations.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(AnchorPane);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
                    }    }
            }) ;
          //  sf.addReservation(id, Integer.parseInt(s));
           //  sf.decrementnbr(id);
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MesReservations.fxml")));
            stage.setScene(scene);
        }else if ((sf.getNbrPlace(id) > 0) && (!sf.Reservationexiste(id, us.getidUSERByusername()))) {
            String s="";
                   try {
            s = us.getlogin();
        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            sf.addReservation(id, Integer.parseInt(s));
             sf.nbrres(id);
             sf.decrementnbr(id);
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MesReservations.fxml")));
            stage.setScene(scene);
        }
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
    private void redirectionFormation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
        
    }

   

    @FXML
    private void mesres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SMS.fxml"));
        Parent root = loader.load();
        SMSController acc = loader.getController();
        mesres.getScene().setRoot(root);
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
    private void save(MouseEvent event) throws DocumentException, FileNotFoundException, SQLException, BadElementException, IOException {
          if (LVlist.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Aucune formation n'est sélectionnée");
            alert.showAndWait();}
          else {
        Document d = new Document();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        QrCodeController q = new QrCodeController();
        ServiceFormation sf = new ServiceFormation();
        Date date = new Date();
           
        System.out.println(dateFormat.format(date));
        PdfWriter.getInstance(d, new FileOutputStream("QrCodeCadeau.pdf"));
        d.open();
        Paragraph p =new Paragraph();
        p.add("Bienvenue Mme/Mr " + Login.getUsername());
        p.setAlignment(Element.ALIGN_LEFT);
        d.add(p);
         Paragraph p2 =new Paragraph();
         p2.add(new Date().toString());
         p2.setAlignment(Element.ALIGN_RIGHT);
         d.add(p2);
     
    //  d.add(new Paragraph("Bienvenue Mme/Mr " + Login.getUsername(),FontFactory.getFont(FontFactory.TIMES_BOLD)));
     // d.add(new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.TIMES_BOLD)));
      Paragraph p3 =new Paragraph();
      p3.add("---------------------------------------------------------------------------------------------------------------------------");
      d.add(p3);
      //d.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------"));
          d.add(Image.getInstance("C:\\Users\\asus\\Desktop\\IntegrationJava\\src\\images\\ping.png"));
          
           sf.getmyReservations(us.getidUSERByusername());
           
         d.add(new Paragraph("L'équipe PingWin vous remercie pour votre fidélité , et pour votre participation aux formations" +sf.getMyRes(us.getidUSERByusername()) +"\n"));
        d.add(new Paragraph ("N'hésitez pas à réserver de plus."+"\n",FontFactory.getFont(FontFactory.TIMES_BOLD)));
         d.add(new Paragraph ("Vous etes toujours les bienvenus."+"\n"+"A bientot",FontFactory.getFont(FontFactory.TIMES_BOLD)));
    
    
    d.close();
    }


    }


}
