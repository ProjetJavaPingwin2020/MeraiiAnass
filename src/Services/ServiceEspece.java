/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie_espece;
import Entity.Espece;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import utils.ConnexionBase;

/**
 *
 * @author TH3OMAR
 */
public class ServiceEspece {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    //private Connection con;
    public ServiceEspece () {
        cnx = ConnexionBase.getInstance().getCnx();
    }


    public static int ajouter(Espece t) {

        int st = 0;

        try {
          String requeteInsert = "INSERT INTO `espece` (`id`, `categorie`, `nom`, `type`, `description`, `nom_image`) VALUES (NULL, ?, ?, ?, ?, ?);";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setInt(1, t.getCategorie());
            pre.setString(2, t.getNom());
            pre.setString(3, t.getType());
            pre.setString(4, t.getDescription());
            pre.setString(5, "");
           

            st = pre.executeUpdate();
            System.out.println("espece ajouté avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;

    }

    public static int delete(String nom) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from espece where nom='" + nom + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("espece supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static int update(Espece f, String name) {
        int st = 0;
        try {
          String req = "UPDATE espece SET `nom`='" + f.getNom() +"', `type`='" + f.getType()  +"', `description`='" + f.getDescription()  +"', `categorie`='" + f.getCategorie()+ "' WHERE `nom` ='" +name+ "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static Espece getEspece(String name) throws SQLException {
        String req = "SELECT * from espece where nom='" + name + "'";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        Espece e = new Espece();
        ResultSet rs = pre.executeQuery(req);

        while (rs.next()) {

            String nom = rs.getString("nom");
            String type = rs.getString("type");
            String description = rs.getString("description");
            Integer categorie = rs.getInt("categorie");
            
           // String img = rs.getString("img");
           // String Image = rs.getString("Image");
            InputStream image = rs.getBinaryStream("image");

            Espece ee = new Espece(nom, type,description,categorie);
            e = ee;
        }
        return e;
    }
    
    public static List<Espece> readAll() throws SQLException {
        List<Espece> esp = new ArrayList<Espece>();
        String req = "SELECT * from espece";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Espece e = new Espece();
            
            e.setNom(rs.getString(2));
            e.setType(rs.getString(3));
            e.setDescription(rs.getString(4));
            e.setCategorie(rs.getInt(5));
            esp.add(e);

        }
        return esp;
    }
    
     public Image getEspeceImageByID(int id) throws SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = cnx.prepareStatement("SELECT image FROM espece WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            InputStream is = rs.getBinaryStream("image");
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
            os.close();
            is.close();

        }
        Image image = new Image("file:photo.jpg");
        return image;
    
    
    
     }
    public List<Espece> readEspece() throws SQLException {
        List<Espece> AL = new ArrayList<>();
        String req = "SELECT* from Espece";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String type = rs.getString("type");
            String description = rs.getString("description");
            int categorie = rs.getInt("categorie");
            
           Espece c = new Espece(nom,type,description,categorie);
            AL.add(c);
        }
        return AL;
    }
  
     private final String GET_All_Espece = "select nom,type,description,categorie from espece ";
     
     public ObservableList<Espece> getAllEspeces() throws SQLException {
        ObservableList<Espece> e = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Espece);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            e.add(ResultsToEspece(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }

        return e;
    } 
    public ObservableList<Espece> getEspecesbycategorie(int idc) throws SQLException {
        String GETEspecebycategorie = "SELECT nom,type,description,categorie  FROM `espece` WHERE `categorie` =" +idc;
        ObservableList<Espece> e = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GETEspecebycategorie);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            
            e.add(ResultsToEspece(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            
            System.out.println(e.toString());
        }

        return e;
    } 
     private Espece ResultsToEspece(String nom,String type,String description,int categorie) {
       return new Espece( nom, type, description,categorie);
    } 
}
