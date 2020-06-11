/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Categorie;

import Services.ProduitCategorieService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassiine
 */


public class FXMLEditCController implements Initializable {
  @FXML
    private Text nom_cat;

    @FXML
    private TextField description;

    @FXML
    private Text id;

    @FXML
    private TextField nom;
       @FXML
    private Button edit;
          @FXML
    private Button delete;
          
           public static int test=0;

          ProduitCategorieService s1=new ProduitCategorieService();;
    /**
     * Initializes the controller class.
     */
          
          public void edit(ActionEvent e) throws SQLException, IOException {
        
        
        
         
      Categorie c= new Categorie(Integer.parseInt(FXMLProduitCategorieController.E_id_selection),nom.getText(),description.getText());
      System.out.println(c.toString());
      s1.editcat(c);
      test=1;
      
      
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
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setText(String.valueOf(FXMLProduitCategorieController.E_id_selection));
        
        nom_cat.setText(FXMLProduitCategorieController.E_nom_selection);
        nom.setText(FXMLProduitCategorieController.E_nom_selection);
        description.setText(FXMLProduitCategorieController.E_description_selection);
        
    }    
    
}
