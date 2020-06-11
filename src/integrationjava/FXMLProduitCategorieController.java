/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Categorie;
import Entity.Commande;
import Entity.Notif;
import Entity.Produit;
import Entity.Rating;
import Services.CommandeService;
import Services.ProduitCategorieService;
import Services.RatingService;
import Services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import utils.MailSend;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.ConnexionBase;
import utils.ControleSaisie;


/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLProduitCategorieController implements Initializable {
  ////////export/////////////
        private Connection cnx;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
        private XSSFWorkbook wb;
    private XSSFSheet sheet;
     private XSSFRow header;
    /**
     * Initializes the controller class.
     */
     @FXML
    private Button AjoutProduitBoutton;
    @FXML
    private TextField description_prod;

    @FXML
    private TableView<Produit> ListeProduit;

    @FXML
    private ChoiceBox<String> categorie_prod;

    @FXML
    private Button BouttonProduit;
    @FXML
    private Button detail;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
     
    @FXML
    private ImageView notif_empty;
    @FXML
    private ImageView notif_1;

    @FXML
    
    
    private Button BouttonCategorie;

    @FXML
    private TextField quantite_prod;

    @FXML
    private Button BouttonCommande;

    @FXML
    private Button BouttonRating;

    @FXML
    private TextField nom_categorie;

    @FXML
    private TextField nom_prod;

    @FXML
    private Pane Produit;

    @FXML
    private Pane Categorie;

    @FXML
    private TableView<Categorie> liste_categories;

    @FXML
    private TextField description_categorie;

    @FXML
    private TextField prix_prod;
    
    @FXML
    private TableColumn<Produit,String> categorie_prod2= new TableColumn<>("Categorie");
    @FXML
    private Text text_nom;
    
    @FXML
    private Text text_quantite;
     @FXML
    private Text produit_id;
    
    @FXML
    private Text text_prix;
            
    @FXML
    private Text text_description;
     @FXML
    private TableColumn<Produit,String> image2;
    @FXML
    private TableColumn<Produit,String> nom_prod1= new TableColumn<>("nom");
    @FXML
    private TableColumn<Produit,Double> prix_prod2= new TableColumn<>("prix");
    @FXML
    private TableColumn<Produit,String> quantite_prod2= new TableColumn<>("quantite");
//TableColumn mtaa l produit
    @FXML
    private TableColumn<Categorie,String> nom_categorie1= new TableColumn<>("Nom");
    @FXML
    private TableColumn<Categorie,String> description_categorie1= new TableColumn<>("Description");
    
      @FXML
    private ImageView imageView;
    private Image image;
    private FileInputStream fis;
    
    
     @FXML
    private TextField filechoose;
       @FXML
    private Button deletec;
       @FXML
    private Button valider;
       @FXML
    private Button refuser;
     
     public static String E_id_selection;
    public static String E_nom_selection;
    public static String E_description_selection;
   
    public static int test=0;
    @FXML
    private Button edit_categorie;
    
     @FXML
    private TableView<Commande> liste_commande;
  @FXML
    private TableColumn<?, ?> produit_commande;
  @FXML
    private TableColumn<?, ?> paye_commande;

    @FXML
    private TableColumn<?, ?> prixtotal_commande;
     @FXML
    private TableColumn<?, ?> quantite_commande;
      @FXML
    private TableColumn<?, ?> client_commande;
        @FXML
    private Pane Commande;
        @FXML
    private TableColumn<?, ?> etat_commande;
@FXML
    private TableColumn<?, ?> imagecommande;
//TableColumn mtaa l catégorie
   
    ProduitCategorieService s1=new ProduitCategorieService();
    UserService s2=new UserService();
    CommandeService s3=new CommandeService();
   ObservableList<Produit> data = FXCollections.observableArrayList();
   ObservableList<Categorie> data2 = FXCollections.observableArrayList();
   FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    ObservableList<Commande> data3 = FXCollections.observableArrayList();
    ObservableList<Rating> data4 = FXCollections.observableArrayList();
    RatingService s4=new RatingService();
    ControleSaisie controle=new ControleSaisie();
    //declaration rating
     @FXML
    private TableColumn<?, ?> produit_rating;
      @FXML
    private TableColumn<?, ?> commentaire_rating;
      @FXML
    private TableColumn<?, ?> client_rating;
       @FXML
    private TableView<Rating> liste_rating;
        @FXML
    private TableColumn<?, ?> degre_rating;
          @FXML
    private TableColumn<?, ?> image_rating;
          @FXML
    private Pane pane_rating;
          
public static double revenu;
static public int day;

    @FXML
    private Label errornom;

    @FXML
    private Label errorprix;

    @FXML
    private Label errordescription;

    @FXML
    private Label errorquantite;

    @FXML
    private Label errorcategorie;
    @FXML
    private Button chooser;
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
    private ImageView logout;
    private Object evenementsBtn;
    



  @FXML
    void reset_revenu(MouseEvent event) throws SQLException
{
    LocalDateTime now = LocalDateTime.now();
   int day6=now.getDayOfMonth();
   
   if (day6-s1.jour()!=0)
   {
       s1.editrevenu();
   }
   
       JOptionPane.showMessageDialog(null, "Le revenu du jour "+now+" est de : " +s1.revenu()+" Dinars");
       
    
    
  
    
}
    
    
    private void notif_empty()
    {
        notif_empty.setVisible(true);
        notif_1.setVisible(false);
    }
    
     private void notif_1()
    {
        notif_1.setVisible(true);
        notif_empty.setVisible(false);
    }
     
     
      void notification() {
          
    
    List<Notif> notif = s3.afficherNotif();
    
     for(int i=0;i<notif.size();i++)
         
      {
         Notifications notificationBuilder;
    notificationBuilder = Notifications.create()
            .title("Paiement")
            
            .text("Notification " +(i+1)+": Un nouveau paiement de la part de "+notif.get(i).getNom_user()+" d'une somme de : " +notif.get(i).getPrix())
           
          
             .hideAfter(Duration.seconds(6))
            .position(Pos.BOTTOM_RIGHT)
           
            ;
            
    notificationBuilder.darkStyle();
        notificationBuilder.show();
      
      
      }
    }
    
    
    @FXML
     public void addProduit(ActionEvent event) throws SQLException {
         
         String r = categorie_prod.getSelectionModel().getSelectedItem().toString();
         
         int id_cat=s1.findbynomcategorie(r);
        
         
         
         
         
         
         double price= Double.parseDouble(prix_prod.getText());
         int quantite=Integer.parseInt(quantite_prod.getText());
         controle.effacerControleSaisie(errornom);
            controle.effacerControleSaisie(errordescription);
            controle.effacerControleSaisie(errorprix);
        controle.effacerControleSaisie(errorquantite);
         //controle.effacerControleSaisie(errot);
     
    
     
     if(nom_prod.getText().isEmpty() || description_prod.getText().isEmpty() || prix_prod.getText().isEmpty() || quantite_prod.getText().isEmpty() )
     
     {
         
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("veuiller remplir tous les champs");
         alert.showAndWait();
         return;
     }
         
        Produit p = new Produit(id_cat,nom_prod.getText(),description_prod.getText(),price,filechoose.getText(),quantite,0.0,0,0);
        
        s1.ajouterProduit(p);
        test=2;
        JOptionPane.showMessageDialog(null, "Produit ajouté ");
        try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLProduitCategorie.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
        
       
    }
    
    @FXML
      public void addCategorie(ActionEvent event) {
        Categorie c = new Categorie(nom_categorie.getText(),description_categorie.getText());
        s1.ajouterCategorie(c);
        test=1;
        JOptionPane.showMessageDialog(null, "Catégorie ajoutée ");
        try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLProduitCategorie.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
        
       
    }
      
    @FXML
    public void products() {
        Produit.setVisible(true);
        Categorie.setVisible(false);
        Commande.setVisible(false);
        pane_rating.setVisible(false);
        
       }
    
    @FXML
    public void Categorie() {
        Produit.setVisible(false);
        Categorie.setVisible(true);
         Commande.setVisible(false);
         pane_rating.setVisible(false);
       }
    @FXML
    public void Commande() {
        Produit.setVisible(false);
        Categorie.setVisible(false);
         Commande.setVisible(true);
         pane_rating.setVisible(false);
       }
    
    @FXML
    public void rating() {
        Produit.setVisible(false);
        Categorie.setVisible(false);
         Commande.setVisible(false);
         pane_rating.setVisible(true);
       }
    
    
    @FXML
     public void image(ActionEvent event) {
      fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files","*.pdf", "*.tkt", "*.docx","*.png","*.jpg"));
        fc.setInitialDirectory(new File("C:"));
        selectedFile = fc.showOpenDialog(null);

        //UploadFile.upload(selectedFile,"", "");
        File file = new File("" + selectedFile.getName());
        filechoose.setText(selectedFile.getName());

        //images.setImage(imagee);   
    }
    
     public void afficher_champs(Produit p)
     {
       text_nom.setText(p.getNom());
       text_quantite.setText(String.valueOf(p.getQuantite()));
       text_prix.setText(String.valueOf(p.getPrix()));
       text_description.setText(p.getDescription());
       image = new Image("file:src/images/"+p.getNomimage()+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
                
       
       
     }
 
    @FXML
 public void edit(ActionEvent e) throws SQLException, IOException {
        
        
        String r = categorie_prod.getSelectionModel().getSelectedItem().toString();
         
         int id_cat=s1.findbynomcategorie(r);
        
         
         
         
         
         
         double price= Double.parseDouble(prix_prod.getText());
         int quantite=Integer.parseInt(quantite_prod.getText());
        
         
        Produit p = new Produit(Integer.parseInt(produit_id.getText()),id_cat,nom_prod.getText(),description_prod.getText(),price,filechoose.getText(),quantite,0.0,0,0);
        
        s1.edit(p);
        JOptionPane.showMessageDialog(null, "Produit Modifié");
        try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLProduitCategorie.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
        
        
       
       
     }
        
    @FXML
 public void delete(ActionEvent e) throws SQLException  {
        
        
  s1.Delete(Integer.parseInt(produit_id.getText()));
  JOptionPane.showMessageDialog(null, "Produit supprimé");
       try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLProduitCategorie.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
        
        
       
       
       
     }
        
      
    @FXML
         public void Edit_cat(ActionEvent e)
         {      
             
             E_id_selection=String.valueOf(liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getId());
             E_nom_selection=liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getNom();
             E_description_selection=liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getDescription();
             System.out.println(E_id_selection);
             JOptionPane.showMessageDialog(null, "Catégorie Modifiée");
             
             
             
             try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLEditC.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
         }

         
       
    
     
     public void afficher_detail()
     {
         
        
         {
        ListeProduit.setOnMouseMoved(e -> {
             Produit p =ListeProduit.getItems().get(ListeProduit.getSelectionModel().getSelectedIndex());
             
             produit_id.setText(String.valueOf(p.getId()));
            text_nom.setText(p.getNom());
       text_quantite.setText(String.valueOf(p.getQuantite()));
       text_prix.setText(String.valueOf(p.getPrix()));
       text_description.setText(p.getDescription());
       image = new Image("file:src/images/"+p.getNomimage()+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                imageView.setImage(image);
                prix_prod.setText(String.valueOf(p.getPrix()));
                filechoose.setText(p.getNomimage());
            quantite_prod.setText(String.valueOf(p.getQuantite()));
           try {
               categorie_prod.setValue(s1.findbyidcategorie2(p.getCategorie()).getNom());
           } catch (SQLException ex) {
               Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
           }
           nom_prod.setText(p.getNom());
           description_prod.setText(p.getDescription());
           
                
                  
                
                
                   });
     }
        
     }
     
    @FXML
     public void SupprimerCat(ActionEvent e) throws SQLException
     {
        
         System.out.println(liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getId());
         s1.Deletec(liste_categories.getItems().get(liste_categories.getSelectionModel().getSelectedIndex()).getId());
         test=1;
         JOptionPane.showMessageDialog(null, "supprimé");
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLProduitCategorie.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
                          
     }
   
     
     
     
     
     public void AfterValider(ActionEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLProduitCategorie.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
     
     
     
      
     
     
     
    @FXML
     public void Valider(ActionEvent e)
     {
         
          valider.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             Produit p2 =s1.Produitfindbyid(p.getProduit());
             int new_quantite= p2.getQuantite()-p.getQuantite();
             
            
             
             if (p.getEtat().equals("Validé"))
            {
                JOptionPane.showMessageDialog(null, "Commande déja Validée");
            }
             else if (new_quantite<0) 
             {
                 JOptionPane.showMessageDialog(null, "Erreur! Quantite de la commande dépasse le stock");
             }
            else 
            {
                 try {
                     s3.edit(p);
                     s1.editproduit(new_quantite,p2.getId());
                     test=3;
                     AfterValider(e);
                 } catch (SQLException ex) {
                     Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  int idd=p.getUser();
         
          CommandeService as = new CommandeService();
     
      String mailuser = null;
     
      String usernom =null;
     
      try {
          mailuser = as.getemail(idd);
         
        usernom=s2.getusername(idd);
       
      MailSend m = new MailSend();
                String subject = "Commande validée";
               
               
                String message ="Bonjour Mr/Mme " +usernom+ " Votre commande de " +p.getQuantite()+ " de " +p.getNom_prod()+ " est validée! "
                        + " Merci pour votre confiance";
                       
               System.out.println(message);
               
                m.sendMail("mohamedyassine.bennacef@esprit.tn", mailuser, subject, message);
       
               
           } catch (SQLException ex) {
                 Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
        }) ;
         
          
         
     }
         
    @FXML
     public void Refuser(ActionEvent e)
     {
         
          refuser.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             

                 try {
                     
                    s3.Delete(p.getId());
                     JOptionPane.showMessageDialog(null, "Commande Refusée et Supprimée");
                     test=3;
                     AfterValider(e);
                 } catch (SQLException ex) {
                     Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
        }) ;
         
          
         
     }
         
     

     
     
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         cnx = ConnexionBase.getInstance().getCnx();
         List<Notif> notif = s3.afficherNotif();
        if (notif.size() >0)
        {
            notif_1();
        }
        else
        {
            notif_empty();
        }
        
        if (FXMLEditCController.test==1 || test==1)
        {
             Categorie();
            }
        else if (test==2 || test==0) { products();
        }
        else 
        {
            Commande();
        }
            
        
        List<Produit> d1 = s1.afficherProd();
          List<Categorie> d2 = s1.afficherCat();
          List<Commande> d4 = s3.afficherCommande();
          List<Rating> d5 = s4.afficherRating();          
            
       data = FXCollections.observableArrayList(d1);
       data2 = FXCollections.observableArrayList(d2);
       data3 = FXCollections.observableArrayList(d4);
       data4 = FXCollections.observableArrayList(d5);
       
       
       afficher_champs(data.get(0));
       afficher_detail();
        
       liste_commande.setItems(data3);
        
        produit_commande.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        paye_commande.setCellValueFactory(new PropertyValueFactory<>("pay"));
        prixtotal_commande.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        quantite_commande.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        client_commande.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        etat_commande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        imagecommande.setCellValueFactory(new PropertyValueFactory<>("photo2"));
        liste_categories.setItems(data2);
        
        

        nom_prod1.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        categorie_prod2.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        quantite_prod2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prix_prod2.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
        
        
        
        ListeProduit.setItems(data);
        
        ObservableList<String> categories_id = FXCollections.observableArrayList();
        
   List<Categorie> d3 = s1.afficherCat();
   
   ObservableList<String> list_cat1 = FXCollections.observableArrayList();
   
   for(int i=0;i<d3.size();i++)
   
   {
       String val=(d3.get(i).getNom());
       
       categories_id.add(val);
       
   }
   
 
        
          categorie_prod.setItems(categories_id);
          nom_categorie1.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        description_categorie1.setCellValueFactory(new PropertyValueFactory<>("Description"));
       
        
        
     
  liste_rating.setItems(data4);
        
        produit_rating.setCellValueFactory(new PropertyValueFactory<>("produit"));
         commentaire_rating.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
          client_rating.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
           
            degre_rating.setCellValueFactory(new PropertyValueFactory<>("degre"));
             image_rating.setCellValueFactory(new PropertyValueFactory<>("photo"));
      
             System.out.print(day);
                
    
    
      notif_1.setOnMouseClicked(e -> {
             
                  notification();
                  notif_empty();
                  s3.deletenotif();
                
                
     
     });
     notif_empty.setOnMouseClicked(e -> {
             
                 
                Notifications notificationBuilder;
    notificationBuilder = Notifications.create()
            .title("Pas de Notifications")
            
            .text("Vous n'avez pas de notifications pour le moment ! ")
           
          
             .hideAfter(Duration.seconds(6))
            .position(Pos.BOTTOM_RIGHT)
           
            ;
            
    notificationBuilder.darkStyle();
        notificationBuilder.show();
      
                
     
     });
    
    }
    
    @FXML
    void exporter(ActionEvent event)  {
//

    try {
         String query ="select * from produit";
        
            pst=cnx.prepareStatement(query);
        
        rs= pst.executeQuery();
        
        
         wb= new XSSFWorkbook();
        sheet= wb.createSheet("Product Details");
        header =sheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("categorie");
        header.createCell(2).setCellValue("nom");
        header.createCell(3).setCellValue("prix");
        header.createCell(4).setCellValue("quantite");
        
        int index=1;
         
        while(rs.next()){
          XSSFRow row=sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString(1));
            row.createCell(1).setCellValue((rs.getString(2)));
            row.createCell(2).setCellValue(rs.getString(3));
            row.createCell(3).setCellValue(rs.getString(4));
            row.createCell(4).setCellValue(rs.getString(7));
            index++;
        }
        
       FileOutputStream fileOut= new FileOutputStream("Product.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Product Details Exported in Excel Sheet.");
        alert.showAndWait();
        
        pst.close();
        rs.close();
        
    }
    catch (SQLException ex) {
            Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    catch (IOException ex) {
        Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    }
    
      @FXML
    void statistique(MouseEvent event) {
        
          try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          
        
        

    }
    
      @FXML
    void chat(MouseEvent event) {
        
          try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLChatAdmin.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          
        
        

    }
    
    
     @FXML
 void login(MouseEvent event) {
         try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLyassine.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
//controle saisie
  @FXML
    void verifnom(KeyEvent event) {
        controle.controleTextFieldVide(nom_prod, "Veuillez saisir un nom du produit", errornom);
         
           controle.controleTextFieldOnlyLetters(nom_prod, "nom non  numérique", errornom);
           
        
          
          
          

    }

    @FXML
    void verifprix(KeyEvent event) {
        
        
      
         
          controle.controleTextFieldNumerique1(prix_prod, "Libelle non numérique", errorprix);
          controle.controleTextFieldVide(prix_prod,"veuillez saisir un prix", errorprix);
        

    }

    @FXML
    void verifquantite(KeyEvent event) {
        
        controle.controleTextFieldVide(quantite_prod, "Veuillez saisir la quantite", errorquantite);
          
        controle.controleTextFieldChiffres(quantite_prod, "la quantite doir etre une valeur", errorquantite);
        

    
        
  
        
    
    
}
    
  
       @FXML
    void verifdescription(KeyEvent event) {
        
        controle.controleTextFieldVide(description_prod, "Veuillez saisir la description", errordescription);
           controle.controleTextFieldOnlyLetters(description_prod, "description non numérique", errordescription);
           
        
          
          
        

    }

    @FXML
    private void evenements(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementback.fxml"));
        Parent root = loader.load();
        EvenementbackController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void formations(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectespece(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBackEspece.fxml"));
        Parent root = loader.load();
        FXMLBackEspeceController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }

    @FXML
    private void informations(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLArticles_especes.fxml"));
        Parent root = loader.load();
        FXMLArticles_especesController acc = loader.getController();
        InformationsBtn.getScene().setRoot(root);
    }
}



  


     
       
