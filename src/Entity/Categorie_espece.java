/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;

/**
 *
 * @author TH3OMAR
 */

public class Categorie_espece {
     int id;
     String nom;
     private InputStream image;

    public Categorie_espece(int id, String nom, InputStream image) {
        this.id = id;
        this.nom = nom;
        this.image = image;
    }

    public Categorie_espece(String nom, InputStream image) {
        this.nom = nom;
        this.image = image;
    }

    public Categorie_espece() {
 
    }

    public Categorie_espece(String nom) {
        this.nom = nom;
    }

    public Categorie_espece(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Categorie_espece{" + "nom=" + nom + ", image=" + image + '}';
    }
     
    
    
}
