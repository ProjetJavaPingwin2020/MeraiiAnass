/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Entity.Categorie_espece;
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
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

/**
 *
 * @author TH3OMAR
 */

public class ServiceCategorie_espece {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    //private Connection con;
    public ServiceCategorie_espece () {
        cnx = ConnexionBase.getInstance().getCnx();
    }


    public static int ajouter(Categorie_espece t) {

        int st = 0;

        try {
            String requeteInsert = "INSERT INTO `categorie_espece` (`id`, `nom`, `nom_image`) VALUES (NULL, ?, ?);";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setString(1, t.getNom());
            pre.setString(2, "");
          //  pre.setBinaryStream(2, t.getImage());

          st= pre.executeUpdate();
            System.out.println("categorie_espece ajouté avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;

    }

    public static int delete(int idc) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from categorie_espece WHERE id="+idc;
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("categorie_espece supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static int update(Categorie_espece f, int idc) {
        int st = 0;
        try {
         
          String query="UPDATE categorie_espece SET nom='"+f.getNom()+"' WHERE id="+idc;
          
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(query);
            pre.executeUpdate();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        };
        return st;
    }

    public static String getCategorie_espece(int Id) throws SQLException {
        String req = "SELECT * from categorie_espece where id='" + Id + "'";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        Categorie_espece c = new Categorie_espece();
        ResultSet rs = pre.executeQuery(req);

        while (rs.next()) {

            String nom = rs.getString("nom");
            
           // String img = rs.getString("img");
           // String Image = rs.getString("Image");
            InputStream image = rs.getBinaryStream("image");

            Categorie_espece cc = new Categorie_espece(nom, image);
            c = cc;
        }
        return c.getNom();
    }
    
    public static List<Categorie_espece> readAll() throws SQLException {
        List<Categorie_espece> cat = new ArrayList<Categorie_espece>();
        String req = "SELECT* from categorie_espece";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Categorie_espece c = new Categorie_espece();
            
            c.setNom(rs.getString(2));
            cat.add(c);

        }
        return cat;
    }
    
     public Image getCategorie_especeImageByID(int id) throws SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = cnx.prepareStatement("SELECT image FROM categorie_espece WHERE id = ?");
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
    public List<Categorie_espece> readcategorie_espece() throws SQLException {
        List<Categorie_espece> AL = new ArrayList<>();
        String req = "SELECT* from categorie_espece";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom");
            Categorie_espece c = new Categorie_espece(nom);
            AL.add(c);
        }
        return AL;
    }
  
     private final String GET_All_Categorie_espece = "select id,nom from categorie_espece ";
     
     public ObservableList<Categorie_espece> getAllCategorie_especes() throws SQLException {
        ObservableList<Categorie_espece> c = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Categorie_espece);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            c.add(ResultsToCategorie_espece(rs.getInt(1),rs.getString(2)));
        }

        return c;
    } 
     private Categorie_espece ResultsToCategorie_espece(int id,String nom) {
       return new Categorie_espece(id , nom);
    } 
}
   
