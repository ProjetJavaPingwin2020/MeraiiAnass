/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Articles_especes;
import Entity.Commentaires;
import Entity.FosUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import utils.ConnexionBase;


/**
 *
 * @author USER
 */
public class ArticleService {
    private final Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public ArticleService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }

 
  
   public ObservableList<Articles_especes> ArticleLoad()
           
           
   {
       
   ObservableList<Articles_especes> artList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * from articles_especes";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Articles_especes A = new Articles_especes();
                A.setType(rs.getString("Type"));
                A.setTitre(rs.getString("titre"));
                A.setContenu(rs.getString("Contenu"));
               // A.setUser(rs.getString("user"));
               A.setDatepub(rs.getDate("datepub"));
                A.setImage(rs.getString("image"));
            
             ImageView v=new ImageView();
                   v.setImage(new Image(rs.getString(8)));
                   v.setFitHeight(50);
                   v.setFitWidth(50);
                   A.setPhoto(v);
                   artList.add(A);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return artList;
    }
   
   
   public Articles_especes getStock(String a) throws SQLException {   
        Articles_especes an = new Articles_especes();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM Articles_especes WHERE Titre LIKE ?  ;");

        pre.setString(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setId(rs.getInt("id"));
           an.setTitre(a);


           
        }
        return an;

    }
      public String getStockType(int a) throws SQLException {
          String an="" ;
        PreparedStatement pre = cnx.prepareStatement("SELECT Titre FROM Articles_especes WHERE id = ?  ;");
//hne nhot chnouwa ?? el loula edhyka eli bech tafichih men table lokhra yaan
        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an=rs.getString(1);


           
        }
        return an;
     }
  /* 
   public int count(){
    try {
        String titre = null;
            String req = "SELECT COUNT("+titre+") FROM articles_especes";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            if (rs.next()) {
               return Integer.parseInt(rs.getString(2));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }finally{
    
    try{
    cnx.close();
    }
   catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }}
        return 0;
    
   
   }*/


     public void ajouuterArticle(Articles_especes A) throws IOException {
         
    
        try {

            String requete
                    = "INSERT INTO `articles_especes` (`Type`,`Titre`,`Contenu`,`image`) VALUES (?,?,?,?)";
        PreparedStatement bt = cnx.prepareStatement(requete);
         bt.setString(1, A.getType());
        bt.setString(2, A.getTitre());
        bt.setString(3, A.getContenu());
        bt.setString(4, A.getImage());
        
            bt.executeUpdate();
            System.out.println("article ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }}
 public void deleteArticle(int id) throws SQLException {
        String req = "DELETE FROM`articles_especes` WHERE idArticle='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }

public void updateArticle (Articles_especes A, int id) throws IOException {


         try {

                     
            String requete ="UPDATE `articles_especes` SET Titre = ? Comtenu =? ,image = ? ,Type=? WHERE id = ?";
             PreparedStatement st = cnx.prepareStatement(requete);
             st.setString(1,A.getType());
             st.setString(2,A.getTitre());
             st.setString(3,A.getContenu());
             st.setString(4,A.getImage());
             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(Articles_especes.class.getName()).log(Level.SEVERE, null, ex);
         }
              JOptionPane.showMessageDialog(null, "update avec succes");



    }

    /*public ArrayList<Commentaires> select() {
        // TODO Auto-generated method stub
        ArrayList<Commentaires>  list_epreuve = new ArrayList<Commentaires>();// tableau multi-dimensionnel
        
        try {
            Statement state = this.cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare = this.cnx.prepareStatement(
                    "SELECT com.id_epreuve, ep.heure_debut, ep.id_juge, ep.id_discipline, ep.id_categorie, ep.id_competition, " +
                    "j.nom, j.prenom, j.email, j.gsm, j.rue, j.numero, j.nom, j.code_postal, j.ville," +
                    "di.nom_discipline " +
                    "FROM Epreuve ep " +
                    "JOIN Juge j ON j.nom = ep.nom " +
                    "JOIN Discipline di ON di.nom_categorie = ep.nom_categorie");
 
            //On exécute la requête
            ResultSet resultat = prepare.executeQuery();
            int i=0;
            while(resultat.next()){
                list_epreuve.add(i, new Epreuve(
                        resultat.getInt("id_epreuve"), resultat.getString("heure_debut"), resultat.getInt("id_juge"), resultat.getInt("id_discipline"), resultat.getInt("id_categorie"),
                        resultat.getInt("id_competition"), resultat.getString("nom"), resultat.getString("prenom"), resultat.getString("email"),
                        resultat.getString("gsm"), resultat.getString("rue"), resultat.getInt("numero"), resultat.getInt("code_postal"),
                        resultat.getString("ville"), resultat.getString("nom_discipline")));
                i++;
            }
 
            prepare.close();
            state.close();
             
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return list_epreuve;
    }*/
    }

