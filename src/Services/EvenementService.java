/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Demande;
import Entity.Evenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;
import static utils.ConnexionBase.getInstance;

/**
 *
 * @author milim
 */
public class EvenementService {
    Connection cnx;
    ConnexionBase db = getInstance();
    
    private final String Create_Evenement = "insert into evenement(nom_evenement,adresse,description,date,heure,chef_id,nom_image) VALUES(?,?,?,?,?,?,?)";
    private final String GET_All_USER_Evenements = "select id_event, nom_evenement, date, heure, adresse, description, chef_id   from evenement where chef_id = ?";
    private final String Delete_Evenement_BY_ID = "delete from evenement where id_event=?";
    private final String Udate_Evenement = "update evenement set nom_evenement=?,adresse=?,date=?,heure=?,description=?,nom_image=? where id_event=?";
    private final String GET_All_Evenements = "select id_event, nom_evenement, date,heure, adresse, description, chef_id   from evenement";
    private final String GET_USER_BY_ID = "SELECT id_event, nom_evenement, date,heure, adresse, description, chef_id FROM evenement WHERE id_event=?";


    
    
     public EvenementService() {
        cnx = db.getCnx();
    }

    public int createEvent(Evenement e) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(Create_Evenement);
        PreparedStatement psid = cnx.prepareStatement("select last_insert_id()");
        ps.setString(1, e.getNom_evenement());
        ps.setString(2, e.getAdresse());
        ps.setString(3, e.getDescription());
        ps.setDate(4, e.getDate());
        ps.setString(5, e.getHeure());
        ps.setInt(6, e.getChef_id());
        ps.setString(7, e.getImage());

        ps.executeUpdate();
        ResultSet rs = psid.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        System.out.println("Evenement creer");

        return id;
    }
        public void adddemande(int id_event, int id_u) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("INSERT INTO demande(idE,idU,etat) VALUES(?,?,?)");
        ps.setInt(1, id_event);
        ps.setInt(2, id_u);
        ps.setString(3, "En cours !");

        ps.executeUpdate();
        System.out.println("demande ajoutée !");

    }
      public ObservableList<Evenement> getAllUserOwnedEvenements(int idchef) throws SQLException {
        ObservableList<Evenement> e = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_USER_Evenements);
        ps.setInt(1, idchef);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            e.add(ResultsToEvenement(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
        }

        return e;
    }   
       private Evenement ResultsToEvenement(int id, String nom, Date date,String heure, String adresse, String description, int idchef) {
        return new Evenement(id, nom, date, heure, adresse, description, idchef);
    }
    
       public ObservableList<Evenement> SearshUserEvenements(String s, int idchef) throws SQLException {
        ObservableList<Evenement> e = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("select id_event, nom_evenement, date, heure, adresse, description, chef_id  FROM evenement "
                + " WHERE chef_id =? AND nom_evenement LIKE ?");
        ps.setInt(1, idchef);
        ps.setString(2, "%" + s + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            e.add(ResultsToEvenement(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
        }

        return e;
    }
         public String getEvenementImageByID(int id) throws SQLException, FileNotFoundException, IOException {
        String is = "";
        PreparedStatement ps = cnx.prepareStatement("SELECT nom_image FROM evenement WHERE id_event = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            is = rs.getString("nom_image");
        }
        return is;
    }
        
        public void deleteEvenementById(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(Delete_Evenement_BY_ID);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("deleted");

    }
 public void updateEvenement(Evenement e) {

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(Udate_Evenement);
            ps.setString(1, e.getNom_evenement());
            ps.setString(2, e.getAdresse());
            ps.setDate(3, e.getDate());
            ps.setString(4, e.getHeure());
            ps.setString(5, e.getDescription());
            ps.setString(6, e.getImage());
            ps.setInt(7, e.getId_event());

            ps.executeUpdate();
            System.out.println("updated ");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
    public ObservableList<ImageView> getAllEvenementImages() throws SQLException, FileNotFoundException, IOException {
       
        String is = "";
        ObservableList<ImageView> img = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("SELECT nom_image FROM evenement");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            
            is = rs.getString("nom_image");
             Image image = new Image(new FileInputStream("D:\\wamp64\\www\\integration\\test1.1\\web\\images\\"+is));
               ImageView imgEvt = new ImageView(image);
            img.add(imgEvt);
        }
        return img;
    }
      
    
    public ObservableList<String> getAllSearshedEvenementImages(String nom) throws SQLException, FileNotFoundException, IOException {
        ObservableList<String> li = null;
        String is = "";
        ObservableList<ImageView> img = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("SELECT nom_image FROM evenement WHERE nom_evenement LIKE ?");
        ps.setString(1, "%" + nom + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            is = rs.getString("image");
            li.add(is);
        }
        return li;
    }
      public int getidEventByName(String s) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT id_event FROM evenement WHERE nom_evenement=?");
        ps.setString(1, s);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getInt(1);
    }
      
       public String getetatEventByName(int id_event, int id_user) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT etat FROM demande WHERE idE=? and idU=?");
        ps.setInt(1, id_event);
         ps.setInt(2, id_user);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getString(1);
    }

      
    public boolean alreadyMembre(int id_event, int id_user) throws SQLException {
        int count = 0;
        PreparedStatement ps = cnx.prepareStatement("select count(*) from demande where idE=? and idU=?");
        ps.setInt(1, id_event);
        ps.setInt(2, id_user);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }

    }
       public ObservableList<Evenement> getAllevenements() throws SQLException {
        ObservableList<Evenement> e = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Evenements);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            e.add(ResultsToEvenement(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
        }

        return e;
    }

 public Evenement getEVENTById(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_USER_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return ResultsToEvenement(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
    }
 
     public ObservableList<String> getAllEvenementusers(int idevent) throws SQLException {
        ObservableList<String> a = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("SELECT u.username, u.email FROM demande a INNER JOIN fos_user u ON u.id = a.idU WHERE idE =?");
        ps.setInt(1, idevent);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            a.add(rs.getString(1) + " email :" + rs.getString(2));
//            a.add("hello");
        }

        return a;
    }
     
         public void deleteparticipant(int idevent, int idu) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM demande WHERE idE=? AND idU=?");
        ps.setInt(1, idevent);
        ps.setInt(2, idu);
        ps.executeUpdate();
        System.out.println("no longer participant");

    }
         
             public int countparticipant() throws SQLException{
        int count = 0;
        PreparedStatement ps = cnx.prepareStatement("SELECT COUNT(DISTINCT idU) FROM demande");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    

   
   
}
