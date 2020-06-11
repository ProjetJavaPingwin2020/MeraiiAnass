package Services;

import Entity.Formateur;
import Entity.Formation;
import Entity.FosUser;
import java.io.File;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static jdk.nashorn.internal.runtime.Debug.id;
import utils.ConnexionBase;

/**
 *
 * @author asus
 */
public class ServiceFormateur {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    //private Connection con;
    public ServiceFormateur() {
        cnx = ConnexionBase.getInstance().getCnx();
    }


    public static int ajouter(Formateur t) {

        int st = 0;

        try {
            String requeteInsert = "INSERT INTO `formateur` (`nom`, `prenom`, `nomImage`) VALUES ( ?,?,?);";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setString(1, t.getNom());
            pre.setString(2, t.getPrenom());
            pre.setString(3, t.getNomImage());
          //  pre.setBinaryStream(3, t.getNomImage());

            st = pre.executeUpdate();
            System.out.println("formateur ajouté avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;

    }

    public static int delete(String nom) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from formateur where nom='" + nom + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("Formateur supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static int update(Formateur f, String name) {
        int st = 0;
        try {
          String req = "UPDATE Formateur SET `nom`='" + f.getNom() + "', `prenom`='" + f.getPrenom()+ "', `nomImage`='" + f.getNomImage()+ "' WHERE `nom` ='" + name + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static Formateur getFormateur(String name) throws SQLException {
        String req = "SELECT * from formateur where nom='" + name + "'";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        Formateur f = new Formateur();
        ResultSet rs = pre.executeQuery(req);

        while (rs.next()) {

            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
           // String img = rs.getString("img");
           String nomImage = rs.getString("nomImage");
        //   InputStream image = rs.getBinaryStream("image");

            Formateur ff = new Formateur(nom, prenom, nomImage);
            f = ff;
        }
        return f;
    }
    
    public static List<Formateur> readAll() throws SQLException {
        List<Formateur> arr = new ArrayList<Formateur>();
        String req = "SELECT* from Formateur";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Formateur f = new Formateur();
          /*  String a = rs.getString("nomImage");
                System.out.println(a);
                Image image = new Image("file:src/images/"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                f.setPhoto(photo);
                f.setNom(rs.getString("nom"));
              f.setPrenom(rs.getString("prenom"));
             
                f.setNomImage(rs.getString("nomImage"));
            f.setNom(rs.getString(2));
            f.setPrenom(rs.getString(3));*/
           // f.setImg(rs.getString(4));
            //f.setImage(rs.getBinaryStream(4));
            
            arr.add(f);

        }
        return arr;
    }
    
     public String getFormateurImageByID(int id) throws SQLException, FileNotFoundException, IOException {
         String is ="";
        PreparedStatement ps = cnx.prepareStatement("SELECT nomImage FROM formateur WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        is = rs.getString("nomImage");
}
        
        return is;
    
     }
    public List<Formateur> readformateur() throws SQLException {
        List<Formateur> AL = new ArrayList<>();
        String req = "SELECT* from formateur";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom");
            Formateur f = new Formateur(nom);
            
            AL.add(f);
        }
        return AL;
    }
    /*
    public List<Formateur> readfor() throws SQLException {
          
        List<Formateur> AL = new ArrayList<>();
        st = cnx.createStatement();
        ResultSet rs = pre.executeQuery("select * from formateur");
        while (rs.next()) {
            String nom = rs.getString("nom");
            Formateur t = new Formateur(nom);
            AL.add(t);
        }
        return AL;
    }*/
    
   /* public int findbynomformateur(String name) throws SQLException
    {
        String req= "SELECT * FROM formateur";
        st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()){
            if(res.getString("nom").equals(name))
            {
                String val=res.getString("id");
                System.out.println(val);
                int valeur = Integer.parseInt(val);
                return valeur;
                       
                        }
        }
        int valeur=0;
        return valeur;
    }
  */
     private final String GET_All_Formateur = "select id,nom,prenom from formateur ";
     
     public ObservableList<Formateur> getAllFormateurs() throws SQLException {
        ObservableList<Formateur> d = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Formateur);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            d.add(ResultsToFormateur(rs.getInt(1),rs.getString(2), rs.getString(3)));
        }

        return d;
    } 
     private Formateur ResultsToFormateur(int id,String nom, String prenom) {
       return new Formateur(id , nom, prenom);
    } 
        private final String GET_Formateur_BY_ID = "SELECT * FROM formateur WHERE id=?";
      public Formateur getFormateurById(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_Formateur_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return mapResultsToFormateur(rs.getInt(1), rs.getString(2), rs.getString(3));
    }

    private Formateur mapResultsToFormateur(int id, String nom, String prenom) {
        return new Formateur(id, nom, prenom);
    }
}
    
