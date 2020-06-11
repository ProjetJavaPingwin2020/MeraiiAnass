/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Commande;
import Services.CommandeService;
import Services.UserService;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInRight;
import animatefx.animation.Tada;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class FXMLPanierController implements Initializable {

   @FXML
    private TableView<Commande> liste_commande;

    @FXML
    private TableColumn<?, ?> paye_commande;

    @FXML
    private TableColumn<?, ?> quantite_commande;

    @FXML
    private TableColumn<?, ?> prixtotal_commande;

    @FXML
    private Text nb_prod;

    @FXML
    private TableColumn<?, ?> etat_commande;

    @FXML
    private TableColumn<?, ?> imagecommande;

    @FXML
    private Text username;

    @FXML
    private TableColumn<?, ?> produit_commande;
    
    @FXML
    private Text new_quantite;
    
    @FXML
    private Button confirmer;
    @FXML
    private Button payer;
    
    @FXML
    private Button supprimer;
   
    @FXML
    private Pane paneprix;

    
  
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
 @FXML
    private Text yass;

    
    @FXML
    private Button edit;
    
    @FXML
    private TextField quantite_new;
    
    @FXML
    private Text id_commande;
     @FXML
    private Text total;
    
    public static String etat;
    public static Double prix;
    public static int idcommande1;
    
        @FXML
    private ImageView chariot;
    
         @FXML
    private ImageView com;
    CommandeService s3=new CommandeService();
    ObservableList<Commande> data3 = FXCollections.observableArrayList();
    UserService s2= new UserService();
    @FXML
    private Text username1;
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
        // TODO
             
        username.setText(s2.getloginusername());
    } catch (SQLException ex) {
        Logger.getLogger(FXMLFrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }
      
        List<Commande> d4 = s3.afficherpanier();
        data3 = FXCollections.observableArrayList(d4);
        liste_commande.setItems(data3);
        produit_commande.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        paye_commande.setCellValueFactory(new PropertyValueFactory<>("pay"));
        prixtotal_commande.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        quantite_commande.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        etat_commande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        imagecommande.setCellValueFactory(new PropertyValueFactory<>("photo2"));
       try {
           nb_prod.setText(String.valueOf(s3.NbrProduit()));
           // TODO
       } catch (SQLException ex) {
           Logger.getLogger(FXMLPanierController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           total.setText(String.valueOf(s3.TotalProduit()));
       } catch (SQLException ex) {
           Logger.getLogger(FXMLPanierController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }    
    
    
    @FXML
    public void edit()
    {
        new_quantite.setVisible(true);
        quantite_new.setVisible(true);
        confirmer.setVisible(true);
        edit.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             System.out.println(p.getQuantite());
            quantite_new.setText(String.valueOf(p.getQuantite()));
            id_commande.setText(String.valueOf(p.getId()));
            etat=p.getEtat();
           prix=p.getPrixtotal()/p.getQuantite();
             
        }) ;
    }
    
    @FXML
    public void confirmer(ActionEvent e) throws SQLException
    {   int idd=Integer.parseInt(id_commande.getText());
        int quantitee= Integer.parseInt(quantite_new.getText());
        Double new_prix=quantitee*prix;
        s3.editquantite(idd, quantitee,new_prix);
        if (etat.equals("Validé"))
        {
        JOptionPane.showMessageDialog(null, "Désolé! Votre Commande est déja validée");
    }
        else 
        {
            JOptionPane.showMessageDialog(null, "Modification avec succés ! En attente de validation ");
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
    }
    
    
    public void AfterSupprimer(ActionEvent e)
     {
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
    @FXML
     void Pro(MouseEvent e)
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
     
      @FXML
     void Messenger(MouseEvent e)
     {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLChat.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          
     }
    @FXML
     public void Payer(ActionEvent e)
     {
         
          payer.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             
             etat=p.getPay();
               idcommande1=p.getId();
               try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("DetailsCommandePay.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();
               
             
        }) ;
     }
     
    @FXML
    public void Supprimer(ActionEvent e)
     {
         
          supprimer.setOnMouseClicked(a -> {
             Commande p =liste_commande.getItems().get(liste_commande.getSelectionModel().getSelectedIndex());
             
             if (p.getEtat().equals("Validé"))
                  {
                      JOptionPane.showMessageDialog(null, "Erreur ! La commande est déja validée");
                  }
             else {
                 try {
                     
                    s3.Delete(p.getId());
                     JOptionPane.showMessageDialog(null, "Commande Supprimée");
                     AfterSupprimer(e);
                     
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(FXMLProduitCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }) ;
                 
     }
         
   
    
      @FXML
    void chariot(MouseEvent event) {
          new SlideInRight(chariot).play();

    }
    
    
      @FXML
    void com(MouseEvent event) {
          new SlideInDown(com).play();

    }
    
      @FXML
    void paneprix(MouseEvent event) {
         new Tada(paneprix).play();

    }

    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
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
    private void logout(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionBoutique(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontPrdouit.fxml"));
        Parent root = loader.load();
        FXMLFrontProduitController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
        
        
    }

    @FXML
    private void redirectionEspeces(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherCategorie_espece.fxml"));
        Parent root = loader.load();
        FXMLAfficherCategorie_especeController acc = loader.getController();
        EspecesBtn.getScene().setRoot(root);
    }
}
