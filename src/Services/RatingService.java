/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.Produit;
import Entity.Rating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

/**
 *
 * @author Yassiine
 */
public class RatingService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public RatingService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }
   
    UserService s1= new UserService();
    ProduitCategorieService s2= new ProduitCategorieService();
    
    
    
    
     public void ajouterRating(Rating p) {

        try {
           String req = "INSERT INTO `ratingp` (`produit`, `user`, `degre`, `commentaire`) VALUES  "
                    + "('" + p.getProduit() + "', '" + p.getUser() + "', '" + p.getDegre()+ "', '" + p.getCommentaire() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public List<Rating> afficherRating() {

        List<Rating> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM ratingp";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Rating p = new Rating();
                p.setId(Integer.parseInt(res.getString("id")));
                p.setProduit(Integer.parseInt(res.getString("produit")));
               p.setUser(Integer.parseInt(res.getString("user")));
               
               p.setDegre((res.getString("degre")));
               p.setCommentaire((res.getString("commentaire")));
               Produit p6=s2.Produitfindbyid(p.getProduit());
               String s=p6.getNomimage();
               Image image = new Image("file:"+s+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto(photo);
                p.setNom_user(s1.getusername(p.getUser()));
                
                

                listC.add(p);
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
     
      public boolean TestUser(int id_prod) {

        

        try {

            String req = "SELECT *  FROM ratingp";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
               if(res.getString("user").equals(s1.getlogin()) && Integer.parseInt(res.getString("produit"))==id_prod)
               {
                   return true;
               }
                   
                

                
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }
     
    
}






