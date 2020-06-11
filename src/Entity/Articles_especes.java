/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.List;
/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;*/
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

public class Articles_especes  {
    
  
    
    private int id;
    private String Titre;
    private String Contenu;
     private String Type;
 private String User;

    Set<Commentaires> com;
 

    private Date datepub;

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }
    private int numlike;
    private int accept;
    private String image;
     private ImageView photo;

private int Userid;

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int Userid) {
        this.Userid = Userid;
    }
    public Articles_especes() {
    }

  
        
    //enty ki taaml ajout eda5el id ?wela autoincrement ! le auto 
    public Articles_especes(String Type,String Titre, String Contenu,String image) {//
       
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.image = image;
         this.Type = Type;
        
         /*this.com=(Set<Commentaires>) com;*/
      
    }

    public Articles_especes(String Type, String TitreE, String Contenu, String img, String commentaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "Articles_especes{" + "commentaires=" + com + ", id=" + id + ", Titre=" + Titre + ", Contenu=" + Contenu + ", Type=" + Type + ", datepub=" + datepub + ", numlike=" + numlike + ", accept=" + accept + ", image=" + image + ", photo=" + photo + '}';
    }
       
        
    

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
        
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

   

  

  

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDatepub() {
        return datepub;
    }

    public void setDatepub(Date datepub) {
        this.datepub = datepub;
    }

    public int getNumlike() {
        return numlike;
    }

    public void setNumlike(int numlike) {
        this.numlike = numlike;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }


  
    
   public static void DeleteArticleBytitre(String titre) throws SQLException{
          
       Connection cnx = ConnexionBase.getInstance().getCnx();
        String sql="DELETE FROM `articles_especes` WHERE Titre ='"+titre+"'";
          PreparedStatement prt =cnx.prepareStatement(sql);
       try{
prt.executeUpdate();          
       /*oblist=cc_article.getItems();
        oo=cc_article.getSelectionModel().getSelectedItems();
        oo.forEach(oblist::remove);*/
 }
       catch (SQLException e){
     System.out.println("errror occured");
     e.printStackTrace();
     throw e;
     
 }}
   
   public static void updateArticle(String titre,String contenu)throws SQLException{
           
       Connection cnx = ConnexionBase.getInstance().getCnx();
        String sql="Update articles_especes set Contenu= '"+contenu+"' WHERE titre ='"+titre+"' " ;
          PreparedStatement prt =cnx.prepareStatement(sql);
       try{
prt.executeUpdate();          
       /*oblist=cc_article.getItems();
        oo=cc_article.getSelectionModel().getSelectedItems();
        oo.forEach(oblist::remove);*/ // bekrii fema whda bedeltt fihaa kima hekaa fct ema mehomchh hedhomm ..
 }
       catch (SQLException e){
     System.out.println("errror occured when updating");
     e.printStackTrace();
     throw e;
     
 }

   }


 /* public static ObservableList<Articles_especes> getAllRecords() throws SQLException {
      Connection cnx = ConnexionBase.getInstance().getCnx();
      try{
         ResultSet rs= cnx.createStatement().executeQuery("select * from articles_especes");
     ObservableList<Articles_especes> artlist=getArticleObject(rs);
     return artlist;
      }catch(SQLException e){
           System.out.println("error occured when you insert data"+e);
      }
        return null;
      }

       private static ObservableList<Articles_especes> getArticleObject(ResultSet rs) throws SQLException {
           try {
ObservableList<Articles_especes> artlist = FXCollections.observableArrayList();
         
while(rs.next()){
           
        Articles_especes art=new Articles_especes();
        art.setId(rs.getInt("id"));
        art.setTitre(rs.getString("Titre"));
         art.setContenu(rs.getString("Contenu"));
         art.setAccept(rs.getInt("Accept"));
          art.setNumlike(rs.getInt("like"));  
            art.setImage(rs.getString("image"));  
            artlist.add(art);

       }return artlist;
       }catch(SQLException e){
           System.out.println("error occured when you insert data"+e);
      }
        return null;
      }*/

   

    public Set<Commentaires> getCom() {
        return com;
    }

    public void setCom(Set<Commentaires> com) {
        this.com= com;
    }

    public Date getDatepub(Date date) {
      return datepub;
    }

 
   
   
}
