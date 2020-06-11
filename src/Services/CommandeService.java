/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entity.Categorie;
import Entity.Commande;
import Entity.Notif;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import integrationjava.DetailsCommandePayController;
import utils.ConnexionBase;

/**
 *
 * @author Yassiine
 */
public class CommandeService {
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public CommandeService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }
    UserService s1= new UserService();
    ProduitCategorieService s2= new ProduitCategorieService();
    
    public void ajouterCommande(Commande p) {

        try {
           String req = "INSERT INTO commande (produit, user, quantite,prixtotal,etat,pay) VALUES "
                    + "('" + p.getProduit() + "', '" + p.getUser() + "', '" + p.getQuantite()+ "', '" + p.getPrixtotal() + "', '" + p.getEtat() + "', '" + p.getPay()+ "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
public List<Commande> afficherCommande() {

        List<Commande> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM commande";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Commande p = new Commande();
                p.setId(Integer.parseInt(res.getString("id")));
                p.setProduit(Integer.parseInt(res.getString("produit")));
               p.setUser(Integer.parseInt(res.getString("user")));
               p.setQuantite(Integer.parseInt(res.getString("quantite")));
               p.setPrixtotal(Double.parseDouble(res.getString("prixtotal")));
               p.setEtat((res.getString("etat")));
               p.setPay((res.getString("pay")));
               p.setNom_client(s1.getusername(p.getUser()));
               p.setNom_prod(s2.getnomprod(p.getProduit()));
               String a=s2.getnomimage(p.getProduit());
               Image image = new Image("file:src/images/"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto2(photo);
                

                listC.add(p);
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }

public List<Commande> afficherpanier() {

        List<Commande> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM commande";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if (res.getInt(3)== Integer.parseInt(s1.getlogin()))
                {
                    
                Commande p = new Commande();
                p.setId(Integer.parseInt(res.getString("id")));
                p.setProduit(Integer.parseInt(res.getString("produit")));
               p.setUser(Integer.parseInt(res.getString("user")));
               p.setQuantite(Integer.parseInt(res.getString("quantite")));
               p.setPrixtotal(Double.parseDouble(res.getString("prixtotal")));
               p.setEtat((res.getString("etat")));
               p.setPay((res.getString("pay")));
               p.setNom_client(s1.getusername(p.getUser()));
               p.setNom_prod(s2.getnomprod(p.getProduit()));
               String a=s2.getnomimage(p.getProduit());
                    System.out.println(a);
               Image image = new Image("file:src/images/"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto2(photo);
                

                listC.add(p);
            }
            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
public void edit(Commande P) throws SQLException {
   
                    pre = cnx.prepareStatement("update commande set etat=? where id=?");

                    pre.setString(1, "Validé");
                   
                    pre.setInt(2, P.getId());
                    pre.executeUpdate();
   
       
        }

public void editquantite(int idd,int quantitee,Double prix) throws SQLException {
   
                    pre = cnx.prepareStatement("update commande set quantite=?,prixtotal=? where id=?");

                    pre.setInt(1, quantitee);
                    pre.setDouble(2, prix);
                    pre.setInt(3, idd);
                    pre.executeUpdate();
   
       
        }

public void Delete(int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("DELETE FROM commande where id=?");

                    pre.setInt(1,idd);
                    pre.executeUpdate();
}


public int NbrProduit() throws SQLException
{
    String req = "SELECT *  FROM commande ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            int i=0;
        while (res.next()) {
               if(res.getInt(3)==Integer.parseInt(s1.getlogin()))
               {
                   i++;
               }
            }
            
            
            
            return i;
            
}

public Double TotalProduit() throws SQLException
{
    String req = "SELECT *  FROM commande ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            Double i=0.0;
        while (res.next()) {
               if(res.getInt(3)==Integer.parseInt(s1.getlogin()))
               {
                   i= i+res.getDouble("prixtotal");
               }
            }
            
            
            
            return i;
            
}

public String getemail(int idd) throws SQLException {
           ResultSet rs;
       
       
        st=cnx.createStatement();
        String pseudoL = null;
       
       
        String req="SELECT * FROM fos_user ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("email");
        }
         System.out.println(pseudoL);
         return pseudoL;
         
            }

public Commande findcommandebyid(int idd) {

        
 Commande p = new Commande();
        try {

            String req = "SELECT *  FROM commande";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if (Integer.parseInt(res.getString("id"))==idd)
                {
               
                p.setId(Integer.parseInt(res.getString("id")));
                p.setProduit(Integer.parseInt(res.getString("produit")));
               p.setUser(Integer.parseInt(res.getString("user")));
               p.setQuantite(Integer.parseInt(res.getString("quantite")));
               p.setPrixtotal(Double.parseDouble(res.getString("prixtotal")));
               p.setEtat((res.getString("etat")));
               p.setPay((res.getString("pay")));
               p.setNom_client(s1.getusername(p.getUser()));
               p.setNom_prod(s2.getnomprod(p.getProduit()));
               String a=s2.getnomimage(p.getProduit());
               Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                p.setPhoto2(photo);
                

                return p;
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return p;
    }

public void PayerCommande(int idd) throws SQLException {
   
                    pre = cnx.prepareStatement("update commande set pay=? where id=?");

                    pre.setString(1, "Payée");
                    pre.setInt(2, idd);
                    pre.executeUpdate();
   
       
        }

public double findprixbyid(int idd) {

        Double p=0.0;
 
        try {

            String req = "SELECT *  FROM commande";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if (Integer.parseInt(res.getString("id"))==idd)
                {
               
                p= Double.parseDouble(res.getString("prixtotal"));
                

                return p;
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return p;
    }
public void ajouter_notif() throws SQLException
{    
    double last=DetailsCommandePayController.price;
        String username= s1.getloginusername();
     try {
            
            
            String req3 = "INSERT INTO `notif`(`nom_user`,`prix`) "
                    + "VALUES ('"+username+"','"+last+"') ";
            
         
             st = cnx.createStatement();
             st.executeUpdate(req3);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
 
}

    public void deletenotif() {
        try {
         
 
            
            String req = "DELETE FROM `notif` ";
          st = cnx.createStatement();

            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
public List<Notif> afficherNotif() {

        List<Notif> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM notif";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                
                {
                    
                Notif p = new Notif();
                p.setId(Integer.parseInt(res.getString("id")));
               p.setNom_user(res.getString("nom_user"));
               p.setPrix(res.getDouble("prix"));
                
             
                listC.add(p);
            }
            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
   
}



