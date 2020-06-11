/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
/*import javafx.collections.ObservableList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;*/

/**
 *
 * @author USER
 */


public class CommentaireEvenement {


    private int id;
    private int id_user;
    private String message;
private int article;
   private String sto;
   Articles_especes s;
 private String User;

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

  
    public CommentaireEvenement(int aInt, String string) {
      
    }
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

    public CommentaireEvenement() {

    }

    public CommentaireEvenement(int id, int id_user, String meessage) {
        this.id = id;
        this.id_user = id_user;
        this.message = message;
    }

  

    public String getMessage() {
        return message;
    }

    public void setMeessage(String meessage) {
        this.message = meessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    
        
        
    
}
