/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import utils.ConnexionBase;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatistiqueController implements Initializable {

   private final ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
private PieChart pieChart;
BorderPane root;
   
    private ResultSet rs=null,rs1=null;
    private PreparedStatement pst,pst1;
    @FXML
    PieChart piechart1;
    ObservableList<PieChart.Data> piechartdata;
ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();

     private Connection cnx;
  
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          cnx = ConnexionBase.getInstance().getCnx();
        Piechart();
        piechart1.setData(piechartdata);
    }    
    private void Piechart(){
        piechartdata=FXCollections.observableArrayList();
    try {
        
        pst=cnx.prepareStatement("select * from categorie");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("SELECT COUNT(*) as countprodcat FROM produit WHERE categorie='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("countprodcat"));
            piechartdata.add(new PieChart.Data(rs.getString(2),i));
            np.add(i);
            cat.add(rs.getString("nom"));
        }
        }
    } catch (SQLException ex) {
        Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
        // TODO
   
        // TODO
    }    
    
}
