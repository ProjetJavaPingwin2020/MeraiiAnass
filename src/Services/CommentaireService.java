/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Articles_especes;
import Entity.Commentaires;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import utils.ConnexionBase;


/**
 *
 * @author USER
 */
public class CommentaireService {
    private final Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public CommentaireService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }
public ObservableList<Commentaires> afficher(Commentaires A) throws SQLException {

        ObservableList<Commentaires> arr = FXCollections.observableArrayList();
         
        st = cnx.createStatement();//sahytk hahaha
        ResultSet rs = st.executeQuery("select * from Commentaire ");

        while (rs.next()) {
    
            arr.add(new Commentaires(rs.getInt("article"),rs.getString("contenu")));

        }
         
              
        return arr;

    }
  
   public ObservableList<Commentaires> CommentaireLoad()
   {
   ObservableList<Commentaires> myListC = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM Commentaire";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Commentaires C = new Commentaires();
                C.setContenu(rs.getString("Contenu"));
                C.setArticle(rs.getInt("article"));
                myListC.add(C);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myListC;
    }

     public void ajouuterCommentaire(Commentaires C) throws IOException {
   
    
        try {

            String requete = "INSERT INTO `Commentaire` (`article`,`Contenu`) VALUES (?,?)";//h
           //thi hiya l cle etranger maw

           //lezm thothom mnadhmin kima el base alma el article iji 9abl choftou ena 
           PreparedStatement st = cnx.prepareStatement(requete);
          st.setInt(1, C.getArticle());
            st.setString(2, C.getContenu());
           
            st.executeUpdate();
            System.out.println("Commentaire ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }//mch logique ema njarabha hhh bebe mo5i t3eeb
     
                //ena mafhemtch chniya el mochekla mte3ouhhhhh aamel ithrab 

    
}
  
}
