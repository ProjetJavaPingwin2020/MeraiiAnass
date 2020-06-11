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
public class Rating {
    private int id;
    private int produit;
    private int user;
    private String degre;       
    private String commentaire;
    private ImageView photo;
    private String nom_user; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDegre() {
        return degre;
    }

    public void setDegre(String degre) {
        this.degre = degre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", produit=" + produit + ", user=" + user + ", degre=" + degre + ", commentaire=" + commentaire + '}';
    }

    public Rating(int produit, int user, String degre, String commentaire) {
        this.produit = produit;
        this.user = user;
        this.degre = degre;
        this.commentaire = commentaire;
    }

    public Rating() {
    }

    public Rating(int id, int produit, int user, String degre, String commentaire) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.degre = degre;
        this.commentaire = commentaire;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }
    
    
}
