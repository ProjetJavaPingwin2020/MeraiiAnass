/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import javafx.collections.ObservableList;
/*import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;*/

/**
 *
 * @author USER
 */


public class Commentaires {

private int id;
private int user;
private Date date_pub;
private String Contenu;
private int article;
   private String sto;
   Articles_especes s;
    public Articles_especes getS() {
        return s;
    }

    public void setS(Articles_especes s) {
        this.s = s;
    }

    public String getSto() {
        return sto;
    }

    public void setSto(String sto) {
        this.sto = sto;
    }

  

    public Commentaires() {
    }


  

    public Commentaires(int id, int user,int article, Date date_pub, String Contenu) {
        this.id = id;
        this.user = user;
        this.date_pub = date_pub;
        this.Contenu = Contenu;
        this.article=article;
    }

    public Commentaires(int article,String Contenu) {
       this.Contenu = Contenu;
        this.article=article;
       
    }
      public Commentaires(String Contenu, Articles_especes s) {
       this.Contenu = Contenu;
        this.s=s;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

 
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

  
  

    public Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public void setDate_pub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
