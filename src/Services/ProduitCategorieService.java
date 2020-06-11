/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entity.Categorie;
import Entity.Produit;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import integrationjava.DetailsCommandePayController;
import static integrationjava.DetailsCommandePayController.last_change;
import utils.ConnexionBase;




/**
 *
 * @author fakhreddine
 */
public class ProduitCategorieService {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public ProduitCategorieService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }

 
    public List<Categorie> afficherCat() {

        List<Categorie> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM categorie";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Categorie p = new Categorie();
                p.setId(Integer.parseInt(res.getString("id")));
                p.setNom(res.getString("nom"));
                p.setDescription(res.getString(2));
                

                listC.add(p);
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
public List<Produit> afficherProd() {

        List<Produit> listC = new ArrayList<>();

        try {

            String req = "SELECT * FROM produit";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Produit p = new Produit();
                String a = res.getString("nom_image");
                System.out.println(a);
                Image image = new Image("file:src/images/"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto(photo);
                p.setNom(res.getString("nom"));
                p.setCategorie(res.getInt(2));
                p.setPrix(res.getDouble(4));
                p.setQuantite(res.getInt(7));
                p.setDescription(res.getString("description"));
                p.setNomimage(res.getString("nom_image"));
                p.setId(Integer.parseInt(res.getString("id")));
                p.setRating(res.getDouble("rating"));
                p.setSommerating(res.getInt("sommerating"));
                p.setNbrating(res.getInt("nbrrating"));
                
                

                listC.add(p);
                
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }



public void ajouterCategorie(Categorie p) {

        try {
            String req = "INSERT INTO categorie (nom, description) VALUES "
                    + "('" + p.getNom() + "', '" + p.getDescription() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

public int findbynomcategorie(String name) throws SQLException
{
    
String req = "SELECT * FROM categorie";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("nom").equals(name))
                {
String val=res.getString("id");
System.out.println(val);
int valeur= Integer.parseInt(val);  

return valeur;
                }
}
        int valeur=0;
          return valeur;
}










public void ajouterProduit(Produit p) {

        try {
             
            String req = "INSERT INTO produit (categorie,nom,prix,description,nom_image,quantite,rating,nbrrating,sommerating) VALUES "
                    + "('" + p.getCategorie() + "', '" + p.getNom() + "', '" + p.getPrix() + "', '" + p.getDescription() + "', '" + p.getNomimage() + "', '" + p.getQuantite() + "', '" + p.getRating() + "', '" + p.getNbrating() + "', '" + p.getSommerating() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


public String findbyidcategorie(int id) throws SQLException
{
    
String req = "SELECT * FROM categorie";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                int value= Integer.parseInt(res.getString("id"));
                if (value==id)
                {
String val=res.getString("nom");


return val;
                }
}
        
          return null;
}

public Categorie findbyidcategorie2(int id) throws SQLException
{
    Categorie c = new Categorie();
String req = "SELECT * FROM categorie";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                int value= Integer.parseInt(res.getString("id"));
               
                
                if (value==id)
                {    c.setDescription (res.getString("description")) ;
                     c.setNom (res.getString("nom")) ;
                     c.setId(value);
                     
String val=res.getString("nom");


return c;
                }
}
        
          return null;
}


   
    
    public void edit(Produit P) throws SQLException {
   
                    pre = cnx.prepareStatement("update Produit set nom=?,description=?,categorie=?,quantite=?,prix=?,nom_image=? where id=?");

                    pre.setString(1, P.getNom());
                    pre.setString(2, P.getDescription());
                    pre.setInt(3, P.getCategorie());
                    pre.setInt(4, P.getQuantite());
                    pre.setDouble(5, P.getPrix());
                    pre.setString(6, P.getNomimage());
                    pre.setInt(7, P.getId());
                    pre.executeUpdate();
   
       
        }
    
    public void Delete(int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("DELETE FROM Produit where id=?");

                    pre.setInt(1,idd);
                    pre.executeUpdate();
   
       
        }
    
     public void editcat(Categorie c) throws SQLException {
   
                    pre = cnx.prepareStatement("update Categorie set nom=?,description=? where id=?");

                    pre.setString(1, c.getNom());
                    pre.setString(2, c.getDescription());
                   
                    pre.setInt(3, c.getId());
                    pre.executeUpdate();
   
       
        }
     
     
    public void Deletec(int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("DELETE FROM Categorie where id=?");

                    pre.setInt(1,idd);
                    pre.executeUpdate();
   
       
        }
    
    
    public List<Produit> afficherProdPeche() {

        List<Produit> listC = new ArrayList<>();

        try {

            String req = "SELECT * FROM produit";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if( findbyidcategorie(res.getInt(2)).equals("Peche") )
                        {
                Produit p = new Produit();
                String a = res.getString("nom_image");
                System.out.println(a);
                Image image = new Image("file:src/images"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto(photo);
                p.setNom(res.getString("nom"));
                p.setCategorie(res.getInt(2));
                p.setPrix(res.getDouble(4));
                p.setQuantite(res.getInt(7));
                p.setDescription(res.getString("description"));
                p.setNomimage(res.getString("nom_image"));
                p.setId(Integer.parseInt(res.getString("id")));
                p.setRating(res.getDouble("rating"));
                p.setSommerating(res.getInt("sommerating"));
                p.setNbrating(res.getInt("nbrrating"));
               String a2 = "";
               if (p.getRating()<=0.2)
               {
                   a2="src/images/Rating0.png";
               }
                if (p.getRating()>0.2 && p.getRating()<=0.4)
               {
                   a2="src/images/Rating20.png";
               }
                   if (p.getRating()>0.4 && p.getRating()<=0.6)
               {
                   a2="src/images/Rating40.png";
               }
                      if (p.getRating()>0.6 && p.getRating()<=0.8)
               {
                   a2="src/images/Rating60.png";
               }
                         if (p.getRating()>0.8 && p.getRating()<1.0)
               {
                   a2="src/images/Rating80.png";
               }
                         if (p.getRating()==1.0)
               {
                   a2="src/images/Rating100.png";
               } 
                
                Image image2 = new Image("file:"+a2+"",70, 70, true, true);
                ImageView photo1= new ImageView(image2);
                p.setPhotorating(photo1);
                
                
                
                
                

                listC.add(p);
                
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }

 public List<Produit> afficherProdChasse() {

        List<Produit> listC = new ArrayList<>();

        try {

            String req = "SELECT * FROM produit";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if( findbyidcategorie(res.getInt(2)).equals("Chasse") )
                        {
                Produit p = new Produit();
                String a = res.getString("nom_image");
                System.out.println(a);
                Image image = new Image("file:src/images/"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto(photo);
                p.setNom(res.getString("nom"));
                p.setCategorie(res.getInt(2));
                p.setPrix(res.getDouble(4));
                p.setQuantite(res.getInt(7));
                p.setDescription(res.getString("description"));
                p.setNomimage(res.getString("nom_image"));
                p.setId(Integer.parseInt(res.getString("id")));
                p.setRating(res.getDouble("rating"));
                p.setSommerating(res.getInt("sommerating"));
                p.setNbrating(res.getInt("nbrrating"));
                 String a2 = "";
               if (p.getRating()<=0.2)
               {
                   a2="src/images/Rating0.png";
               }
                if (p.getRating()>0.2 && p.getRating()<=0.4)
               {
                   a2="src/images/Rating20.png";
               }
                   if (p.getRating()>0.4 && p.getRating()<=0.6)
               {
                   a2="src/images/Rating40.png";
               }
                      if (p.getRating()>0.6 && p.getRating()<=0.8)
               {
                   a2="src/images/Rating60.png";
               }
                         if (p.getRating()>0.8 && p.getRating()<1.0)
               {
                   a2="src/images/Rating80.png";
               }
                         if (p.getRating()==1.0)
               {
                   a2="src/images/Rating100.png";
               } 
                
                Image image2 = new Image("file:"+a2+"",70, 70, true, true);
                ImageView photo1= new ImageView(image2);
                p.setPhotorating(photo1);
                
                
                

                listC.add(p);
                
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
        
   public String getnomprod(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `produit` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("nom");
        } 
         return pseudoL;
            }


public String getnomimage(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `produit` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("nom_image");
        } 
         return pseudoL;
            }

public void editproduit(int quantitee,int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("update Produit set quantite=? where id=?");

                    pre.setInt(1, quantitee);
                    pre.setInt(2, idd);
                    
                    pre.executeUpdate();
   
       
        }


public Produit Produitfindbyid(int idd)
{
    Produit p= new Produit();
    
    
    try {
 
            String req = "SELECT * FROM produit";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if( res.getInt(1)== idd )
                        {
                p.setId(idd)  ;          
                String a = res.getString("nom_image");
                System.out.println(a);
                Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto(photo);
                p.setNom(res.getString("nom"));
                p.setCategorie(res.getInt(2));
                p.setPrix(res.getDouble(4));
                p.setQuantite(res.getInt(7));
                p.setDescription(res.getString("description"));
                p.setNomimage(res.getString("nom_image"));
                p.setId(Integer.parseInt(res.getString("id")));
                p.setRating(res.getDouble("rating"));
                p.setSommerating(res.getInt("sommerating"));
                p.setNbrating(res.getInt("nbrrating"));
                
                

               
                
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }


    return p;
}

public void ratingproduit(int nbrating,int sommerating, double rating,int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("update Produit set nbrrating=?, sommerating=?, rating=? where id=?");

                    pre.setInt(1, nbrating);
                    pre.setInt(2, sommerating);
                    pre.setDouble(3, rating);
                    pre.setInt(4, idd);
                    pre.executeUpdate();
   
       
        }




public void ajouter_revenu() throws SQLException
{    
    double last=DetailsCommandePayController.price;
        LocalDateTime last2 = LocalDateTime.now();
     try {
            
            String req = "SELECT * FROM `revenu` ";
            
             st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

            while (res.next()) {
                cal.setTime(res.getDate("date"));
                System.out.println(cal.get(Calendar.DAY_OF_MONTH));
                if (cal.get(Calendar.DAY_OF_MONTH) - last2.getDayOfMonth() !=0 )
                {
                    
                    last=last;
                }
                else 
                {   
                    
                    last +=res.getInt("revenu");
                }
                
            }
            
             String req2 = "delete FROM `revenu` ";
             
             
             st = cnx.createStatement();

            
            st.executeUpdate(req2);
             
            String req3 = "INSERT INTO `revenu`(`revenu`,`date`) "
                    + "VALUES ('"+last+"','"+last2+"') ";
            
         
             st = cnx.createStatement();
             st.executeUpdate(req3);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
 
}
public double revenu() throws SQLException
{
    
            String req = "SELECT * FROM `revenu` ";
            
             st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
             while (res.next()) {
                 return res.getDouble("revenu");
             }
            return 0.0;
            
}

public int jour() throws SQLException
{
    
            String req = "SELECT * FROM `revenu` ";
            
             st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
             while (res.next()) {
                  cal.setTime(res.getDate("date"));
                  return cal.get(Calendar.DAY_OF_MONTH);
                  
             }
            return 0;
            
}

public void editrevenu() throws SQLException {
   
                    pre = cnx.prepareStatement("update revenu set revenu=?");

                    pre.setInt(1, 0);
                    
                    pre.executeUpdate();
   
       
        }


}
