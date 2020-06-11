/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import Entity.Formateur;
import Entity.Formation;
import Services.ServiceFormateur;
import Services.ServiceFormation;
import Services.UserService;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import utils.ConnexionBase;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLAfficherFormationController implements Initializable {
     private Connection cnx;
    private Statement st;
    @FXML
    private TableView<Formation> table;
    @FXML
    private ImageView image;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField description;
    @FXML
    private TextField heure;
    @FXML
    private TextField nbrplace;
    @FXML
    private TextField formateur;
    @FXML
    private Button supprimer;
    @FXML
    private ImageView retour;
  
    ServiceFormation sf=new ServiceFormation();
    UserService us=new UserService();
    
     private ObservableList<Formation> data = FXCollections.observableArrayList();
    @FXML
    private TextField recherche;
    @FXML
    private Button stat;
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
    private Button listeformations;
    @FXML
    private Button listeformateurs;
    @FXML
    private Button addformation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
            backgroundAnimation();
       
        
        // TODO
          TableColumn nom = new TableColumn("Nom");
        TableColumn type = new TableColumn("Type");
        TableColumn date = new TableColumn("Date");
        TableColumn lieu = new TableColumn("Lieu");
        TableColumn description = new TableColumn("Description");
        TableColumn heure = new TableColumn("Heure");
        TableColumn nbrplace = new TableColumn("Nbrplace");
        TableColumn formateur = new TableColumn("Formateur");
        table.getColumns().addAll(nom, type,date,lieu,description,heure,nbrplace,formateur);
        
          //ServiceFormation sf = new ServiceFormation();
        
         nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<Formation, String>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        heure.setCellValueFactory(new PropertyValueFactory<Formation, String>("heure"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbrplace"));
        formateur.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("formateur"));
       // type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
         table.setItems(data);
            table.setEditable(true);
            nom.setCellFactory(TextFieldTableCell.forTableColumn());
            type.setCellFactory(TextFieldTableCell.forTableColumn());
           // nbhtab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //  date.setCellFactory(TextFieldTableCell.forTableColumn());
             lieu.setCellFactory(TextFieldTableCell.forTableColumn());
              description.setCellFactory(TextFieldTableCell.forTableColumn());
               heure.setCellFactory(TextFieldTableCell.forTableColumn());
                nbrplace.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
     
        try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recherche.textProperty().addListener((observable, oldvalue, newvalue) -> {
            try {
                table.setItems(sf.RechercheAvanceeFormation(recherche.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      
        
    }   
        public void Aff() throws SQLException{
            
            //data.clear();
      
       
        
         TableColumn nom = new TableColumn("Nom");
        TableColumn type = new TableColumn("Type");
        TableColumn date = new TableColumn("Date");
        TableColumn lieu = new TableColumn("Lieu");
        TableColumn description = new TableColumn("Description");
        TableColumn heure = new TableColumn("Heure");
        TableColumn nbrplace = new TableColumn("Nbrplace");
        TableColumn formateur = new TableColumn("Formateur");
          ServiceFormation ser = new ServiceFormation();
        List<Formation> list = ser.readAll();
        for (Formation aux : list) { 
      table.getColumns().addAll(nom, type,date,lieu,description,heure,nbrplace,formateur);
        
        }
        
        ServiceFormation sf = new ServiceFormation();
        nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<Formation, String>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        heure.setCellValueFactory(new PropertyValueFactory<Formation, String>("heure"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbrplace"));
        formateur.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("formateur"));
       // type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
     
        try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficheFormateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
          RechercheAV();
        
          
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
    private void display(MouseEvent event) throws SQLException, IOException {
          System.out.println("aaaaaaaaaaaaaaaaaaaa");
        Formation f = table.getSelectionModel().getSelectedItem();
        ServiceFormation sf = new ServiceFormation();
        nom.setText(f.getNom());
        type.setText(f.getType());
        //date.setDate(f.getDate());
        description.setText(f.getDescription());
        System.out.println(f.getNom());
        Image img = new Image(new FileInputStream("C:\\wamp64\\www\\integration\\test1.1\\web\\imageFormations\\"+sf.getFormationImageByID(f.getId())));
            //   ImageView imgEvt = new ImageView(image);
        image.setImage(img);
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
            
         
         String Nom = nom.getText(); //yekhou nom eli aatithoulou besh yfasakh bih

        int status = ServiceFormation.delete(Nom);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");
            //table.setItems(data);
            
            alert.showAndWait();
            ServiceFormation sf = new ServiceFormation();
             try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");
            /// table.setItems(data);
            alert.showAndWait();
            ServiceFormation sf = new ServiceFormation();
             try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }    
        
     
}

    
    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        retour.getScene().setRoot(root);
    }
     
    public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Formation tab_Formationselected = table.getSelectionModel().getSelectedItem();
     tab_Formationselected.setNom(bb.getNewValue().toString());
     sf.updatetab(tab_Formationselected);
 }
     
     
     @FXML
 public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Formation> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(formation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (formation.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (formation.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(formation.getLieu()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Formation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }

      private void redirection(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
        
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLyassineController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    private void redirectionformation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }


    @FXML
    private void listeformations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormation.fxml"));
        Parent root = loader.load();
        FXMLAfficherFormationController acc = loader.getController();
        listeformations.getScene().setRoot(root);
    }

    @FXML
    private void listeformateurs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormateur.fxml"));
        Parent root = loader.load();
        FXMLAfficheFormateurController acc = loader.getController();
        listeformateurs.getScene().setRoot(root);
    }

    @FXML
    private void addformation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        addformation.getScene().setRoot(root);
        
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLstatreservation.fxml"));
        Parent root = loader.load();
        FXMLstatreservationController acc = loader.getController();
        stat.getScene().setRoot(root);
        
    }

    @FXML
    private void redirectionFormation(ActionEvent event) {
    }
}