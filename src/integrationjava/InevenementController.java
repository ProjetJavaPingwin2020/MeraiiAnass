/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Login;
import Services.EvenementService;
import Services.UserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author milim
 */
public class InevenementController implements Initializable ,MapComponentInitializedListener {
     @FXML
    private BorderPane borderpane;

    @FXML
    private AnchorPane parent;

    ListView<String> LVadherent;

    private Label Lhello;
    @FXML
    protected GoogleMapView mapView = new GoogleMapView("en-US", "AIzaSyDYjCwpMj7OBdHHkTty6XSzc11dxfd4gLI");
    
     @FXML
    private AnchorPane APmail;

    @FXML
    private JFXTextField TFsender;

    @FXML
    private JFXPasswordField TFpasswd;

    @FXML
    private JFXTextField TFreceiver;

    @FXML
    private JFXTextField TFsubjec;

    @FXML
    private JFXTextArea TAtext;
    
    UserService us = new UserService();
    @FXML
    private Button button3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane11;
    @FXML
    private ImageView panier;
    @FXML
    private ImageView logout;
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

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
     public void initialize(URL url, ResourceBundle resources) {
         try {
             pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
             pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
             pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
             pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
             backgroundAnimation();
             
           //  Lhello.setText("Bienvenue " + Login.getUsername());
             EvenementService es = new EvenementService();
             int id_event = es.getidEventByName(Login.getNom_event());
             System.out.println("////////////////////////");
             System.out.println(id_event);
             System.out.println("////////////////////////");
//             LVadherent.setItems(es.getAllEvenementusers(id_event));

             
             
             mapView.addMapInializedListener(this);
//        to.bindBidirectional(toTextField.textProperty());
//        from.bindBidirectional(fromTextField.textProperty());
         } catch (SQLException ex) {
             Logger.getLogger(InevenementController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void button1(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/ajoutevenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void button2(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/user_evenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void button3(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/get_all_evenement.fxml")));
        stage.setScene(scene);
    }


    void backToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/integrartion/Accueil.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        Stage stagec = (Stage) parent.getScene().getWindow();
        stagec.close();

    }

    @FXML
    private void showEmailOptions() throws SQLException{
        APmail.setVisible(true);
        mapView.setVisible(false);
        String s = "";
      //  s += LVadherent.getSelectionModel().getSelectedItem();
        String email = s.substring(s.lastIndexOf(":")+1);
        TFreceiver.setText(email);
        TFsender.setText(us.getEmailUSERByusername());    
        
        System.out.println("////////////////////////");
        
        
        EvenementService es = new EvenementService();
            int id_event = es.getidEventByName(Login.getNom_event());
            System.out.println("////////////////////////");
            System.out.println(id_event);
            System.out.println("////////////////////////");
       //     LVadherent.setItems(es.getAllEvenementusers(id_event));
       
    }

    @FXML
    private void getmeout(ActionEvent event) throws IOException, SQLException {
        EvenementService es = new EvenementService();
        UserService us = new UserService();
        int id_event = es.getidEventByName(Login.getNom_event());
        es.deleteparticipant(id_event, us.getidUSERByusername());
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/integrationjava/get_all_evenement.fxml")));
        stage.setScene(scene);
    }

    @FXML
     void Envoyer(ActionEvent event) {
        try {
            String host = "smtp.gmail.com";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider()); //security ta java
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(TFsender.getText()));
            InternetAddress[] address = {new InternetAddress("huntkingdompingwin@gmail.com")}; //li yab3eth
            msg.setRecipients(Message.RecipientType.TO, address); //receiver
            msg.setSubject(TFsubjec.getText());
            msg.setSentDate(new Date());
            msg.setText(TAtext.getText());

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, TFsender.getText(), TFpasswd.getText());
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

            Image image = new Image("/icons/mail.png");
            Notifications notification = Notifications.create()
                    .title("email envoyer !")
                    .text("votre email a été envoyé")
                    .graphic(new ImageView(image))
                    .hideAfter(Duration.seconds(7))
                    .position(Pos.TOP_RIGHT);
                    
            notification.show();
            TFpasswd.setText("");TFreceiver.setText("");TFsender.setText("");TAtext.setText("");TFsubjec.setText("");
        } catch (Exception ex) {
            System.out.println(ex);
            
            Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("failed!");
            alertu.setHeaderText("votre email n'est pas envoyer verifier les champs !");
            Optional<ButtonType> result = alertu.showAndWait();
        }
    }

    @FXML
    void annuler(ActionEvent event) {
        APmail.setVisible(false);
        mapView.setVisible(true);
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(36.8065, 10.1815))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        //GoogleMapView mapView = new GoogleMapView("en-US", "My-Google-Map-API-Key");
    // directionsService = new DirectionsService();
//        directionsPane = mapView.getDirec();
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
    private void redirectionevent(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        Parent root = loader.load();
        EvenementController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }
    
}
