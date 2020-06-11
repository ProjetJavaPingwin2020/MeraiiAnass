/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import org.apache.commons.io.FileUtils;
import Entity.Articles_especes;
import Entity.CommentaireEvenement;
import Entity.Commentaires;
import Services.ArticleService;
import Services.CommentaireService;
import Services.ServiceCommentaireEvenement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.events.JFXDialogEvent;

import com.teknikindustries.bulksms.SMS;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.runtime.Debug.id;
//import org.controlsfx.control.Notifications;
import utils.ConnexionBase;

//hou hethati
   
public class CommentaireController implements Initializable {
    @FXML
    private TableView<CommentaireEvenement> cc_com;

    @FXML
    private TableColumn<CommentaireEvenement, String> cc_contenucom;

    @FXML
    private TableColumn<CommentaireEvenement, Integer> cc_articlecom;


  @FXML
    private Label labelimage;

    private JFXTextArea id_console;
      @FXML
    private JFXButton load;

    
    @FXML
    private Label count;
    
private TextField text_title;
private PreparedStatement prt=null;
private FileInputStream A;
    ObservableList<Articles_especes> oo,oblist=FXCollections.observableArrayList();
   
      FilteredList<Articles_especes> filteredData = new FilteredList<>(oblist, b -> true);
    @FXML
    private AnchorPane listview;
    private TextField text_obj;

    private TextField text_msg;
 private CommentaireEvenement ev=null;
    @FXML
    private JFXButton AddArticle;
    
    @FXML
    private ComboBox<String> check;

String imagepath;
    @FXML
    private ImageView imageview;
      String img="";
Collection<Commentaires> com=null;
String Titre = "";
    List<String> type;
        @FXML
    private Label labeltitre;

	  
   private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    @FXML
    private Label labelcontenu;
    @FXML
    private Label fileselected;


    /*@FXML
    void smsload(ActionEvent event) throws IOException {

      Parent send = FXMLLoader.load(getClass().getResource("SMSSender.fxml"));
      Scene article_scene=new Scene(send);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
      
    } */
    
  /*  void sendsms(ActionEvent event) {

 
    SMS Tut=new SMS();
    Tut.SendSMS("alma_09","almoucha123",text_msg.getText(),text_obj.getText(),"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    .showMessageDialog(null, "sms sent");
    }*/
  

    @FXML
    private Label labeltype;
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
    private JFXButton smssend;
    @FXML
    private JFXButton print1;
       @FXML
    private JFXTextField contenu_com;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }
          
/*     
try{

String sql="Select count(Titre) from Articles_especes";
 PreparedStatement pst = cnx.prepareStatement(sql);
 ResultSet rs = pst.executeQuery(sql);
if(rs.next()){
    String sum=rs.getString("count(Titre)");
    txtsum.setText(sum);
     pst.executeUpdate();
}}catch(SQLException e){JOptionPane.showMessageDialog(null, "nbre of articles");

         */

    
 @FXML
   public void SelectItemes(javafx.scene.input.MouseEvent event) {
         
        Connection cnx = ConnexionBase.getInstance().getCnx();//connexion mteek chesmha 
        ObservableList<CommentaireEvenement> oblist;
        oblist = (ObservableList<CommentaireEvenement>) cc_com.getSelectionModel().getSelectedItems();
        ev = (CommentaireEvenement)cc_com.getSelectionModel().getSelectedItem();
        ArticleService stocks = new ArticleService();//nn hedhy 
        Articles_especes A=new Articles_especes();//shymou hehi article mouch comm 
        if (oblist != null) {
           contenu_com.setText(oblist.get(0).getMessage());
         
            try {
                 check.setValue(stocks.getStockType(ev.getArticle()));
            } catch (SQLException ex) {
                   Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                }                        
            int max = oblist.get(0).getId();
//
        }
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner Article");
        Articles_especes s = new Articles_especes();
  
        String req = "SELECT * FROM Articles_especes";
        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
             //mouch 9a3ed ychouf fiih l setContenu  shymou ne9ess l entite bich yaarafha kima hnee chnou
                s.setContenu(rs.getString("contenu"));
                availableChoices.add(s.getContenu());
                check.setItems(availableChoices);
                check.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
  
     
      

      @FXML
      void Loadcom(ActionEvent event) {//hethi l affich
        ServiceCommentaireEvenement sp = new  ServiceCommentaireEvenement ();
       List coms=sp.CommentaireLoad();
       ObservableList et=FXCollections.observableArrayList(coms);
      
          cc_com.setItems(et);
          cc_com.setEditable(true);
          cc_contenucom.setCellValueFactory(new PropertyValueFactory<>("message"));
          cc_articlecom.setCellValueFactory(new PropertyValueFactory<>("article"));
         
          // cc_datepubcom.setCellValueFactory(new PropertyValueFactory<>("datepub"));
    }  
      

@FXML
   
void AddCom(ActionEvent event) throws  SQLException, IOException{
  /*String Contenu=contenu_com.getText();
  int Article= Integer.parseInt(check.getValue());
  CommentaireService sp = new  CommentaireService() ;
  Commentaires e = new Commentaires(Contenu,Article);
        sp.ajouuterCommentaire(e);
        JOptionPane.showMessageDialog(null, " commentaire ajoute avec succes");*/
  String a = contenu_com.getText();
      
       ServiceCommentaireEvenement sp = new   ServiceCommentaireEvenement();
        ArticleService stocks = new  ArticleService();
       Articles_especes s=new Articles_especes(); 
   
        s=stocks.getStock((String) check.getValue());
       ServiceCommentaireEvenement ac = new ServiceCommentaireEvenement();
        CommentaireEvenement dd = new CommentaireEvenement (s.getId(),a);
        ac.ajouuterCommentaire(dd); //mochekla fil add eli fil service el code hedha yemchy c'est deja ya9ra fil alert 
        Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Added  Don!");
            alert.showAndWait();//bb wini el fxml mte3 el add
       }
        @FXML
public void changeContenuCellEvent(CellEditEvent edittedCell)
    {
       CommentaireEvenement art =  (CommentaireEvenement) cc_com.getSelectionModel().getSelectedItem();
       art.setMeessage(edittedCell.getNewValue().toString());
    }


    @FXML
    private void logout(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    private void smsload(ActionEvent event) throws IOException {
        
 
      Parent article = FXMLLoader.load(getClass().getResource("SMSSender.fxml"));
      Scene article_scene=new Scene(article);
      
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
    } 
       @FXML
    void redirectionFormation(ActionEvent event) {

    }
       @FXML
    private void back(ActionEvent event) throws IOException {
        
      Parent send = FXMLLoader.load(getClass().getResource("FXMLArticles_especes.fxml"));
      Scene article_scene=new Scene(send);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
    }

  /*  @FXML
    public void ajouterCommentaireEvenement() throws SQLException {
        //String contenueCommentaireEvenement = commentaire_text_fx.getText();
        BoxBlur blur = new BoxBlur(2, 2, 2);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OKAY");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(DetailsEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
        });
        if ((commentaire_text_fx.getText()).isEmpty()) {
            dialogLayout.setHeading(new Label("Champ commentaire est vide"));
            dialogLayout.setBody(new Label("vous devez ecrir un commentaire"));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                GUI.setEffect(null);
            });
            GUI.setEffect(blur);
            return;
        }
       
        sc.ajouterCommentaireEvenement(commentaire_text_fx.getText(),id);
        setDataCommentaire();
   

        commentaire_text_fx.clear();
    }
*/

    void DeleteArticle(ActionEvent Event) throws SQLException{
      try { 
Articles_especes.DeleteArticleBytitre(String.valueOf(text_title.getText()));
  id_console.setText("Article deleted suceffuly..");
 }catch(SQLException e){
     System.out.println("error occured while deleting article"+e);
     throw e;
 }}

    @FXML
    private void chatAction(javafx.scene.input.MouseEvent event) throws IOException {
        
        
            Parent send = FXMLLoader.load(getClass().getResource("Comments.fxml"));
      Scene article_scene=new Scene(send);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
    
    }

    
    
  
}
 
 
 