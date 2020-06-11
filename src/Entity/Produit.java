/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.scene.image.ImageView;

/**
 *
 * @author Yassiine
 */
public class Produit {

    public static void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   private int id;
   private int categorie;
   private String nom;
   private String description;
   private double prix;
   private String nomimage;
   private int quantite;
   private double rating;
   private int nbrating;
   private int sommerating;
   private ImageView photo;
   private ImageView photorating;

    public Produit(int id, int categorie, String nom, String description, double prix, String nomimage, int quantite, double rating, int nbrating, int sommerating) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nomimage = nomimage;
        this.quantite = quantite;
        this.rating = rating;
        this.nbrating = nbrating;
        this.sommerating = sommerating;
    }

    public ImageView getPhotorating() {
        return photorating;
    }

    public void setPhotorating(ImageView photorating) {
        this.photorating = photorating;
    }

    public Produit(int categorie, String nom, String description, double prix, String nomimage, int quantite, double rating, int nbrating, int sommerating, ImageView photo, ImageView photorating) {
        this.categorie = categorie;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nomimage = nomimage;
        this.quantite = quantite;
        this.rating = rating;
        this.nbrating = nbrating;
        this.sommerating = sommerating;
        this.photo = photo;
        this.photorating = photorating;
    }
   

   

   
   

    public Produit() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNomimage() {
        return nomimage;
    }

    public void setNomimage(String nomimage) {
        this.nomimage = nomimage;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNbrating() {
        return nbrating;
    }

    public void setNbrating(int nbrating) {
        this.nbrating = nbrating;
    }

    public int getSommerating() {
        return sommerating;
    }

    public void setSommerating(int sommerating) {
        this.sommerating = sommerating;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", categorie=" + categorie + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", nomimage=" + nomimage + ", quantite=" + quantite + ", rating=" + rating + ", nbrating=" + nbrating + ", sommerating=" + sommerating + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Produit(int categorie, String nom, String description, double prix, String nomimage, int quantite, double rating, int nbrating, int sommerating) {
        this.categorie = categorie;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nomimage = nomimage;
        this.quantite = quantite;
        this.rating = rating;
        this.nbrating = nbrating;
        this.sommerating = sommerating;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Produit(int categorie, String nom, String description, double prix, String nomimage, int quantite, double rating, int nbrating, int sommerating, ImageView photo) {
        this.categorie = categorie;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nomimage = nomimage;
        this.quantite = quantite;
        this.rating = rating;
        this.nbrating = nbrating;
        this.sommerating = sommerating;
        this.photo = photo;
    }

    
    
    
    
   
   
   
    
}
