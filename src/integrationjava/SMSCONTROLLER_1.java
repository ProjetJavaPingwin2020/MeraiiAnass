/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;


import Entity.Articles_especes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.teknikindustries.bulksms.SMS;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import utils.ConnexionBase;
import com.teknikindustries.bulksms.SMS;
import java.awt.event.MouseEvent;
import javafx.scene.layout.Pane;
  
 
  

   
public class SMSCONTROLLER_1 implements Initializable {
      @FXML
    private AnchorPane listview;

    @FXML
    private TextField text_obj;

    @FXML
    private TextField text_msg;

    @FXML
    private JFXButton sms;

    @FXML
    private Label fileselected;

    @FXML
    private Label labeltitre;

    @FXML
    private Label labelcontenu;

    @FXML
    private Label labelimage;

    @FXML
    private Label object;

    @FXML
    private Label messsage;

    @FXML
    private Label Lhello;

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
    private ImageView panier;

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
    private JFXButton print1;

    
    @FXML
       public void sendsms(ActionEvent event) {

 
    SMS Tut=new SMS();
    Tut.SendSMS("alma_09","alma123ahmed",text_msg.getText(),text_obj.getText(),"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    JOptionPane.showMessageDialog(null, "sms sent");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
  

    @FXML
    void redirectionFormation(ActionEvent event) {

    }

    @FXML
    private void logout(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
      Parent send = FXMLLoader.load(getClass().getResource("Commentaireinterf.fxml"));
      Scene article_scene=new Scene(send);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
    }
}