/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Commande;
import Entity.Produit;
import Entity.Rating;
import Services.CommandeService;
import Services.ProduitCategorieService;
import Services.RatingService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static integrationjava.FXMLFrontProduitController.pE_id_selection;


/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLDetailProduitController implements Initializable {
 @FXML
    private Text text_quantite;

    @FXML
    private Text prix;

    @FXML
    private Text description;

    @FXML
    private ImageView imageView;

    @FXML
    private Text id;

    @FXML
    private Button commande;

    @FXML
    private Text nom;

    @FXML
    private TextField quantite;
    
    @FXML
    private Button rate;

    @FXML
    private ChoiceBox<String> Rating;
    /**
     * Initializes the controller class.
     */
     private Image image;
     @FXML
    private TextField commentaire;
     
     @FXML
    private Button pro;
     
    
   UserService s1= new UserService(); 
   CommandeService s2= new CommandeService(); 
   ProduitCategorieService s3= new ProduitCategorieService();
   RatingService s4= new RatingService(); 
   
    public void afficher_champs()
     {
       nom.setText(FXMLFrontProduitController.pE_nom_selection);
       text_quantite.setText(FXMLFrontProduitController.pE_quantite_selection);
       prix.setText(FXMLFrontProduitController.pE_prix_selection);
       description.setText(FXMLFrontProduitController.pE_description_selection);
       id.setText(FXMLFrontProduitController.pE_id_selection);
       
       image = new Image("file:src/images/"+FXMLFrontProduitController.pE_nomimage_selection+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
               }
    
   
    
     public void panier (ActionEvent e) throws SQLException, IOException {
        
        
       System.out.println(pE_id_selection);
        try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLPanier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
        ((Node) e.getSource()).getScene().getWindow().hide();
                   
 }
     
      public void ajouter_commande(ActionEvent e) throws SQLException, IOException
    {
        Double prixtotal= Integer.parseInt(quantite.getText())*Double.parseDouble(prix.getText());
      
        Commande c =  new Commande(Integer.parseInt(id.getText()),Integer.parseInt(s1.getlogin()),Integer.parseInt(quantite.getText()),prixtotal,"Non Validé","Non Payée");
        
        if (Integer.parseInt(text_quantite.getText())==0)
        {
             JOptionPane.showMessageDialog(null, "Erreur! Le stock est épuisé !");
        }
        else if (Integer.parseInt(text_quantite.getText())-Integer.parseInt(quantite.getText())<=0)
        {
            JOptionPane.showMessageDialog(null, "Erreur! La quantité de votre commande dépasse le stock !");
        }
        else { 
        s2.ajouterCommande(c);
        panier(e);
        
    }
    }
      
      public void ajouter_rating (ActionEvent e) throws SQLException, IOException
    {
        int id2= Integer.parseInt(id.getText());
        int user=Integer.parseInt(s1.getlogin());
        String degre=Rating.getSelectionModel().getSelectedItem();
        String Commentaire= commentaire.getText();
        Rating r=new Rating(id2,user,degre,Commentaire);
        if( !s4.TestUser(id2) )
        {
        s4.ajouterRating(r);
        
        Produit p= s3.Produitfindbyid(id2);
        int somme=p.getSommerating();
        int nb=p.getNbrating()+1;
        double moyenne=0.0;
        if (degre.equals("Très Satisfait"))
        {
            somme+=100;
        }
        else if (degre.equals("Satisfait"))
        {
            somme+=75;
        }
        else if (degre.equals("Passable"))
        {
           somme+=50;

        }
        
        else if (degre.equals("InSatisfait"))
        {
            somme+=25;
        }
        else 
        {
            somme+=0; //besh net2aked w bara xD
        }
        
        
         moyenne=(float)somme/(nb*100);
         
        
        s3.ratingproduit(nb, somme, moyenne, id2);
         try {
               
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLFrontProduit.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
        ((Node) e.getSource()).getScene().getWindow().hide();
        }
        else {
            JOptionPane.showMessageDialog(null, "Erreur! Vous avez déjà évalué ce produit");
        }
        
        
    }
       @FXML
        public void Pro(MouseEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLFrontProduit.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
     }
       
    @Override
   
    
    public void initialize(URL url, ResourceBundle rb) {
        afficher_champs();
         ObservableList<String> degre = FXCollections.observableArrayList("Très Satisfait","Satisfait","Passable","Insatisfait","Très InSatisfait");
     
        Rating.setItems(degre);
    }    
    
}
