/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Categorie;
import Entity.Produit;
import Services.ProduitCategorieService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLFrontProduitController implements Initializable {
@FXML
    private TableColumn<?, ?> image;

    @FXML
    private TableColumn<?, ?> prix;

    @FXML
    private Pane pane_chasse;

    @FXML
    private TableColumn<?, ?> rating;

    @FXML
    private Pane pane_peche;

    @FXML
    private TableColumn<?, ?> description;


    @FXML
    private TableColumn<?, ?> image1;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    private TableColumn<?, ?> description1;

    @FXML
    private TableColumn<?, ?> rating1;

    @FXML
    private Button Chasse;

    @FXML
    private TableColumn<?, ?> nom1;

        @FXML
    private ImageView commande;

    @FXML
    private Button Peche;

    @FXML
    private TableColumn<?, ?> prix1;

    @FXML
    private Text username;
    
    
    
     @FXML
    private TableView<Produit> peche;
     
      @FXML
    private TableView<Produit> chasse;
      
  
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
    private ImageView logout;
    @FXML
    private ImageView panier;


//les déclarations
    
    ProduitCategorieService s1=new ProduitCategorieService();
    UserService s2=new UserService();
    ObservableList<Produit> data = FXCollections.observableArrayList();
    ObservableList<Produit> data2 = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    public static String pE_id_selection;
    public static String pE_nom_selection;
    public static String pE_description_selection;
    public static String pE_prix_selection;
    public static String pE_quantite_selection;
    public static String pE_nomimage_selection;
    @FXML
    private AnchorPane rootpane;
    
    
    
    //les fcts
    @FXML
    public void peche() {
        pane_peche.setVisible(true);
        pane_chasse.setVisible(false);
        
       }
    
    @FXML
     public void chasse() {
        pane_peche.setVisible(false);
        pane_chasse.setVisible(true);
        
       }
     
     public void selection()
     {
         
         { peche.setOnMouseClicked(e -> {
             Produit p =peche.getItems().get(peche.getSelectionModel().getSelectedIndex());
            
             
       pE_id_selection=(String.valueOf(p.getId()));
       pE_nom_selection=p.getNom();
       pE_description_selection=p.getDescription();
       pE_prix_selection=(String.valueOf(p.getPrix()));
       pE_quantite_selection=(String.valueOf(p.getQuantite()));
       pE_nomimage_selection=p.getNomimage();
       

       
               }) ;
         }
         
         {
             chasse.setOnMouseClicked(a -> {
             Produit p2 =chasse.getItems().get(chasse.getSelectionModel().getSelectedIndex());
             
             
       pE_id_selection=(String.valueOf(p2.getId()));
       pE_nom_selection=p2.getNom();
       pE_description_selection=p2.getDescription();
       pE_prix_selection=(String.valueOf(p2.getPrix()));
       pE_quantite_selection=(String.valueOf(p2.getQuantite()));
       pE_nomimage_selection=p2.getNomimage();
       

       
               }) ;
             
         }
         
         
     }
     
     public void test()
     {
         selection();
         System.out.print(1);
     }
     
  
      @FXML
    void commande(MouseEvent event) throws SQLException, IOException {
   try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLDetailProduit.fxml"));
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
        void panier(MouseEvent event) throws  IOException {
        
        
      
        try {
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLPanier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLFrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   ((Node) event.getSource()).getScene().getWindow().hide();
                   
 }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
     
     
        chasse();
       try {
        // TODO
        username.setText(s2.getloginusername());
    } catch (SQLException ex) {
        Logger.getLogger(FXMLFrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }
      
     List<Produit> d1 = s1.afficherProdPeche();
         data = FXCollections.observableArrayList(d1);
          System.out.println(data);
          peche.setItems(data);
          nom1.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description1.setCellValueFactory(new PropertyValueFactory<>("Description"));
        image1.setCellValueFactory(new PropertyValueFactory<>("photo"));
        rating1.setCellValueFactory(new PropertyValueFactory<>("photorating"));
        
        List<Produit> d2 = s1.afficherProdChasse();
         
        MouseEvent ev = null;
        data2 = FXCollections.observableArrayList(d2);
          
          chasse.setItems(data2);
          
          nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        rating.setCellValueFactory(new PropertyValueFactory<>("photorating"));
       selection();
       
    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionBoutique(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontProduit.fxml"));
        Parent root = loader.load();
        FXMLFrontProduitController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionEvent(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        Parent root = loader.load();
        EvenementController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionEspece(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherCategorie_espece.fxml"));
        Parent root = loader.load();
        FXMLAfficherCategorie_especeController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }
   
     
 
     
}


