/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import org.apache.commons.io.FileUtils;
import Entity.Articles_especes;
import Entity.Commentaires;
import Services.ArticleService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
//import com.mysfamql.fabric.xmlrpc.base.Data;
import com.teknikindustries.bulksms.SMS;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.sum;
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
import javafx.animation.FadeTransition;
import javafx.application.HostServices;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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


//hou hetha
   
public class FXMLArticles_especesController implements Initializable { 
    
    
    @FXML
    private TableView<Articles_especes> cc_article;
    @FXML
    private TableColumn<Articles_especes,String> cc_Type;
    @FXML
    private TableColumn<Articles_especes,String> cc_titre;
    @FXML
    private TableColumn<Articles_especes,String> cc_contenu;
    private TableColumn<Articles_especes,String> cc_like;
    @FXML
    private TableColumn<Articles_especes,String> cc_accept;
    @FXML
    private TableColumn<Articles_especes,String> cc_image;
    @FXML
    private TableColumn<Articles_especes,Date> DATE_cc;
     
    @FXML
    private TableColumn<Articles_especes,String>user;
    private PreparedStatement prt=null;
    private FileInputStream A;
    ObservableList<Articles_especes> oo,oblist=FXCollections.observableArrayList();
   
      FilteredList<Articles_especes> filteredData = new FilteredList<>(oblist, b -> true);
    String img="";
   Collection<Commentaires> com=null; //lahdha 
    String Titre = "";
    List<String> type;
   
	  
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    @FXML
    private TextField text_title;

    @FXML
    private TextField contenu_text;

ResultSet rs;
    @FXML
    private JFXTextField filter;

    @FXML
    private JFXCheckBox type1;

    @FXML
    private JFXCheckBox type2;

    @FXML
    private ImageView imageview;

    @FXML
    private Label labck;

    @FXML
    private Label labck1;


    @FXML
    private Label labeltitre;

    @FXML
    private Label labelcontenu;

    @FXML
    private Label labelimage;

    @FXML
    private Label labeltype;


    @FXML
    private Pane pane11;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;
    private JFXTextField front_art;

    @FXML
    private Label labtit;

    private Circle btnClose;


    @FXML
    private AnchorPane listview;
    @FXML
    private Label Lhello;
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
    private JFXButton load;
    @FXML
    private JFXButton print;
    @FXML
    private Button mesres;
    @FXML
    private JFXButton delete1;
    @FXML
    private JFXButton Eimage;
    @FXML
    private JFXButton AddArticle;
    @FXML
    private JFXButton deletee;
    @FXML
    private JFXButton update;
    @FXML
    private Label count;
    @FXML
    private Label fileselected;
    @FXML
    private JFXButton load1;
    @FXML
    private JFXButton selected;

  
  

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        backgroundAnimation();
         type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
         
      /*  try {
          
        Connection  cnx = ConnexionBase.getInstance().getCnx();
         String req = "SELECT * FROM articles_especes";
            Statement pst = cnx.createStatement();
          rs = pst.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLArticles_especesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficherListe();
            
            
            
            
            LoaddetailsAction();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLArticles_especesController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    @FXML
     void print(ActionEvent event) throws FileNotFoundException, DocumentException {

        
         //  String file_name="C:\\wamp64\\www\\pdfd\\Articles.pdf";  
           String file_name= "C:\\Users\\asus\\Desktop\\IntegrationJava\\Articles.pdf";
     Document document=new Document();
      PdfWriter.getInstance(document,new FileOutputStream(file_name));
        
     
    try{
     
       document.open();
    
       //document.add(Image.getInstance("C:\\wamp64\\www\\hunt.JPG"));g
  
      
        Connection  cnx = ConnexionBase.getInstance().getCnx();
         String req = "SELECT * FROM articles_especes";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
      while(rs.next()){
     Paragraph para=new  Paragraph(rs.getString("Titre")+" "+rs.getString("Contenu")+rs.getString("Type"));
     Paragraph p = new Paragraph();
            p.add("Hunkingdom");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
//            document.add(Image.getInstance("C:\\wamp64\\www\\hunt.JPG"));
            Paragraph p2 = new Paragraph();
            p2.add("ARTICLES_ESPECES"); //no alignment
           //no alignment
            document.add(p2);
      //       document.add(Image.getInstance("C:\\Users\\asus\\Desktop\\IntegrationJava\\src\\images\\ping.png"));
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
            document.add(new Paragraph(rs.getString("datepub"), f));
document.add(para);
       document.close();
      }
   } catch (DocumentException | SQLException e){
       System.out.println(e);
}
}
    @FXML
   public  void Calculate(ActionEvent event) throws SQLException {
    
        int a=cc_article.getItems().size();
        JOptionPane.showMessageDialog(null, "nombre rows" +a);
}

    @FXML
        void Ajoutercommentaire(ActionEvent event) throws IOException {

      Parent send = FXMLLoader.load(getClass().getResource("Commentaireinterf.fxml"));
      Scene article_scene=new Scene(send);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
      }  
     /* @FXML
 void smsload(ActionEvent event) throws IOException {

      Parent send = FXMLLoader.load(getClass().getResource("SMSSender.fxml"));
      Scene article_scene=new Scene(send);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
      
    }*/
    @FXML
       void LoaddetailsAction(ActionEvent event) {//hethi l affich
        ArticleService sp = new ArticleService();
        List articles=sp.ArticleLoad();
        ObservableList et=FXCollections.observableArrayList(articles);
      
        cc_article.setItems(et);
        cc_article.setEditable(true);
        // cc_commentaires.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
          cc_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
          cc_titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
          cc_contenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
            //cc_like.setCellValueFactory(new PropertyValueFactory<>("numlike"));
            cc_accept.setCellValueFactory(new PropertyValueFactory<>("accept"));
      
             cc_image.setCellValueFactory(new PropertyValueFactory<>("photo"));
             DATE_cc.setCellValueFactory(new PropertyValueFactory<>("datepub"));
             //user.setCellValueFactory(new PropertyValueFactory<>("user"));
             cc_titre.setCellFactory(TextFieldTableCell.forTableColumn());
             cc_contenu.setCellFactory(TextFieldTableCell.forTableColumn());
         
    }  
    @FXML
    public void checkType(ActionEvent event){
          
        int co=0;
        String msg="";
        if(type1.isSelected()){
            co ++;
            msg+=type1.getText()+"\n";
        }
        if(type2.isSelected()){
            co ++;
            msg+=type2.getText()+"\n";
        }
        labck.setText("selected :" +co);
        labck1.setText(msg);
       
  }
         void backgroundAnimation() {//oui en plus les image !!

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

    

        

       
      
    /*public void edittable(){
     
    cc_titre.setCellFactory(TextFieldTableCell.forTableColumn());   
 cc_titre.setOnEditCommit((TableColumn.CellEditEvent<Articles_especes, String> e)->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitre(e.getNewValue());
     });
    cc_contenu.setCellFactory(TextFieldTableCell.forTableColumn());   
 cc_contenu.setOnEditCommit((TableColumn.CellEditEvent<Articles_especes, String> e)->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setContenu(e.getNewValue());
     });
  
    
cc_article.setEditable(true);
}*/ 
    @FXML
         public void AddArticle(ActionEvent event) throws FileNotFoundException, IOException{
      /* labtit.setText("");
       labelcontenu.setText("");
       labelimage.setText("");
       labeltype.setText("");
        if(text_title.getText().isEmpty()||contenu_text.getText().isEmpty()||labck1.getText().isEmpty()){
         
         if (text_title.getText().isEmpty()) {
          labeltitre.setText("Champ titre vide");
            new Alert(Alert.AlertType.ERROR, "Champ titre vide").show();
        }
         if (contenu_text.getText().isEmpty()) {
           labelcontenu.setText("Champ contenu vide"); 
          new Alert(Alert.AlertType.ERROR, " Champ contenu vide").show();
        }
         
         if (labck1.getText().isEmpty()) {
          labeltype.setText("Vous devez entrer un type"); // hedhi habtch tatl fhmtch pk 
            new Alert(Alert.AlertType.ERROR, " Vous devez choisir un type de hunt").show();
        }
        
        else{
         /*if ("".equals(img)) {
           labelimage.setText("aucune image");
          //  new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } */
      labtit.setText("");
       labelcontenu.setText("");
       labelimage.setText("");
       labeltype.setText(""); 
      if (text_title.getText().isEmpty()) {
          labeltitre.setText("Champ titre vide");
            new Alert(Alert.AlertType.ERROR, "Champ titre vide").show(); 
        }
      if (contenu_text.getText().isEmpty()) {
           labelcontenu.setText("Champ contenu vide"); 
          new Alert(Alert.AlertType.ERROR, " Champ contenu vide").show();
        }
      
         
         if (labck1.getText().isEmpty()) {
          labeltype.setText("Vous devez entrer un type"); 
            new Alert(Alert.AlertType.ERROR, " Vous devez choisir un type de hunt").show();
        }
        
      
      else{
             String TitreE=text_title.getText();
        
        String Type=labck1.getText();
        String Contenu=contenu_text.getText();
        ArticleService sp = new ArticleService();
        System.out.println("IMAGE:"+img);//hiya mawosletch lena aslnn yaani wehlet f les cnd fo9
         Articles_especes e = new Articles_especes(Type,TitreE,Contenu,img);
           sp.ajouuterArticle(e);
         JOptionPane.showMessageDialog(null, "ajout avec succes");
     
        
         }}
  
    @FXML
    public void changeTitreCellEvent(CellEditEvent edittedCell)
    {
        Articles_especes art =  cc_article.getSelectionModel().getSelectedItem();
       art.setTitre(edittedCell.getNewValue().toString());
    }
    @FXML
   public void changeContenuCellEvent(CellEditEvent edittedCell)
    {
        Articles_especes art =  cc_article.getSelectionModel().getSelectedItem();
       art.setContenu(edittedCell.getNewValue().toString());
    }
     /* void changeCommentaireCellEvent(CellEditEvent edittedCell)
    {
        Articles_especes art =  cc_article.getSelectionModel().getSelectedItem();
       art.setCom((Set<Commentaires>) (Collection<Commentaires>) edittedCell.getNewValue());
    }*/

   
     /*
        try {
            
            prt =cnx.prepareStatement(sql);
                prt.setString(1,Titre);
                 prt.setString(2,Contenu);
        
          //   prt.setBlob(3, new SerialBlob((Blob) cc_image));
                 
               int i=prt.executeUpdate();
               if (i==1)
             id_console.setText("article inserted suceffuly..");
        } catch (SQLException ex) {
              System.out.println("error"+ex);
        } 
        
        Notifications ntf= Notifications.create()
                .title("Article added succeffuly !!!")
                .text("inserted in data-base 'pidev'")
                .graphic(new ImageView(easy)).hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT).onAction(new EventHandler <ActionEvent>(){
           @Override
           public void handle(ActionEvent event) {
           
               
               System.out.println("Clicked on notifications");
           }
                      
   });
        ntf.showConfirm();*/
    private void handleMouseEvenet(MouseEvent event){
        if (event.getSource() == btnClose){
            System.exit(0);
        }
    }
   /* 
  public void afficherListe() throws SQLException{
        rs.first();
        title.setText(rs.getString("title"));
         text.setText(rs.getString("content"));
           rs.next();
title1.setText(rs.getString("title"));
         text1.setText(rs.getString("content"));
        
    }
    
    @FXML
     public void afficherListe1() throws SQLException{
        rs.next();
        title.setText(rs.getString("title"));
         text.setText(rs.getString("content"));
       rs.next();
title1.setText(rs.getString("title"));
         text1.setText(rs.getString("content"));
        
     }*/


    @FXML
  public  void handleDeletePerson(ActionEvent event) {
    int selectedIndex = cc_article.getSelectionModel().getSelectedIndex();
    if (selectedIndex >2) {
        cc_article.getItems().remove(selectedIndex);
    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
      
        alert.setTitle("No Selection");
        alert.setHeaderText("No Article Selected");
        alert.setContentText("Please select a Article in the table.");

        alert.showAndWait();
    }
} 
    @FXML
  public  void DeleteRowfromtable(ActionEvent event) {
 cc_article.getItems().removeAll(cc_article.getSelectionModel().getSelectedItem());
    }
    @FXML
  public  void UpdateArticle(ActionEvent event) throws SQLException {
        
        try { 
Articles_especes.updateArticle(text_title.getText(),contenu_text.getText());
   JOptionPane.showMessageDialog(null, "Update avec succes");
 }catch(SQLException e){
     System.out.println("error occured while updating article"+e);
     throw e;
 }} 
    @FXML
    public void searchArticle(ActionEvent event) {
           ObservableList data =  cc_article.getItems();
            filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                cc_article.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Articles_especes> subentries = FXCollections.observableArrayList();

            long count = cc_article.getColumns().stream().count();
            for (int i = 0; i < cc_article.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + cc_article.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(cc_article.getItems().get(i));
                        break;
                    }
                }
            }
            cc_article.setItems(subentries);
        });}
     
    @FXML
    public void DeleteArticle(ActionEvent Event) throws SQLException{
      try { 
Articles_especes.DeleteArticleBytitre(String.valueOf(text_title.getText()));
     JOptionPane.showMessageDialog(null, "Delete avec succes");
 }catch(SQLException e){
     System.out.println("error occured while deleting article"+e);
     throw e;
 }}
         
    @FXML
   public void Uploadimage(ActionEvent event) throws IOException {
     
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png",type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
           imageview.setImage(i);
        }
     }
         
   /*@FXML
     void viewchart(ActionEvent event) throws IOException {
      Parent sende = FXMLLoader.load(getClass().getResource("chartespeces.fxml"));
      Scene article_scene=new Scene(sende);
      Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(article_scene);
      app_stage.show();
    }

 
 /*@FXML
public void choisirImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif","*.PNG"));
        File selectedFile =fc.showOpenDialog(new Stage());
        System.out.println("");
        if (selectedFile != null){
            System.out.println(selectedFile.getName());
           System.out.println(selectedFile.getAbsolutePath());
           btnup.setText(selectedFile.getAbsolutePath());

           imagepath = selectedFile.toURI().toString();
           System.out.println("file==>agepath)  "+imagepath);
           Image image = new Image(imagepath);
          imageview.setImage(image);
           
           File source = new File(selectedFile.getAbsolutePath());
           File dest = new File("C:\\wamp64\\www\\images");
           try {
           FileUtils.copyFileToDirectory(source, dest);
//               System.out.println("file uploaded ^^");
               Path p = Paths.get(selectedFile.getAbsolutePath());
               String file = p.getFileName().toString();
//               System.out.println("your link :))))))))");
//               System.out.println("http://localhost/projetpidev/images/"+file);
               ImgToDb = "http://127.0.0.1:88/images/"+file;

           } catch (IOException e) {
            e.printStackTrace();
         }
 
            } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogue D'information");
            alert.setHeaderText("Veuillez sélectionner un fichier");
            alert.setContentText("You didn't select a file!");
            alert.showAndWait();

            //System.out.println("not a valid File ");
        }}
     
    */     /*private void chart(javafx.scene.input.MouseEvent event) {
            
       Stage app_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
      app_stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis(0, 12, 1);
        final NumberAxis yAxis = new NumberAxis(0, 50, 5);

        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("les Articles ecrites, 2020");

        lineChart.setAnimated(true);

        //defining a series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Articles_especes existants");
        //populating the series with data
        series.getData().add(new XYChart.Data<>(1, 23));
        series.getData().add(new XYChart.Data<>(2, 14));
        series.getData().add(new XYChart.Data<>(3, 15));
        series.getData().add(new XYChart.Data<>(4, 24));
        series.getData().add(new XYChart.Data<>(5, 34));
        series.getData().add(new XYChart.Data<>(6, 36));
        series.getData().add(new XYChart.Data<>(7, 22));
        series.getData().add(new XYChart.Data<>(8, 45));
        series.getData().add(new XYChart.Data<>(9, 43));
        series.getData().add(new XYChart.Data<>(10, 17));
        series.getData().add(new XYChart.Data<>(11, 29));
        series.getData().add(new XYChart.Data<>(12, 25));        

        Scene scene  = new Scene(new BorderPane(lineChart),800,600);
        lineChart.getData().add(series);

        series.getData().forEach((data) -> {
            Node node = data.getNode() ;
            node.setCursor(Cursor.HAND);
            node.setOnMouseDragged(e -> {
                Point2D pointInScene = new Point2D(e.getSceneX(), e.getSceneY());
                double xAxisLoc = xAxis.sceneToLocal(pointInScene).getX();
                double yAxisLoc = yAxis.sceneToLocal(pointInScene).getY();
                Number x = xAxis.getValueForDisplay(xAxisLoc);
                Number y = yAxis.getValueForDisplay(yAxisLoc);
                data.setXValue(x);
                data.setYValue(y);
            });
            });


       app_stage.setScene(scene);
        app_stage.show();
    }
*/
    @FXML
    void logout(javafx.scene.input.MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    void redirectionFormation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void especes(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBackEspece.fxml"));
        Parent root = loader.load();
        FXMLBackEspeceController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void info(ActionEvent event) {
    }

    @FXML
    private void boutique(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduitCategorie.fxml"));
        Parent root = loader.load();
        FXMLProduitCategorieController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }


 

 





   
  
   
}