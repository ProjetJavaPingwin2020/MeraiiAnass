/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;
import javafx.scene.image.ImageView;

/**
 *
 * @author asus
 */
public class Formateur {
   private int id;
    private String nom;
     private String prenom;

     //private InputStream image;
   private String nomImage;
    // private ImageView photo;

    public Formateur() {
    }

    public Formateur(int id, String nom, String prenom, String nomImage) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nomImage = nomImage;
    }

    public Formateur(String nom, String prenom, String nomImage) {
        this.nom = nom;
        this.prenom = prenom;
        this.nomImage = nomImage;
    }

    public Formateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

   

  /*  public Formateur(String nom, String prenom, InputStream img) {
        this.nom = nom;
        this.prenom = prenom;
      //  this.img = img;
        this.image = img;
    }
    

    public Formateur(int id, String nom, String prenom,  InputStream img) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
       // this.img = img;
        this.image = img;
    }*/

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

 /*   public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    
*/
    
   

    
    public Formateur(String nom) {
        this.nom = nom;
    }    

    public Formateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /*public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
*/
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Formateur { "+nom+" "+prenom+"  "+nomImage+" }";
    }
    
    
    
}
