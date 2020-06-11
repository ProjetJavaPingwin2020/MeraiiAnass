/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Services.ServiceCategorie_espece;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author TH3OMAR
 */
public class Espece extends Categorie_espece{
    private int id;
    private String nom;
    private String type;
    private String description;
    private InputStream image;
    private int categorie;
   ServiceCategorie_espece sce = new ServiceCategorie_espece();

    public Espece(int id, String nom, String type, String description, InputStream image, int categorie) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    public Espece(String nom, String type, String description, int categorie) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.categorie = categorie;
    }

    public Espece() {
        
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public int getCategorie() {
        return categorie;
    }
    public String getNomCategorie (int categorie) throws SQLException {
        return sce.getCategorie_espece(categorie);
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Espece{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", description=" + description + ", image=" + image + ", categorie=" + categorie + '}';
    }

    
    
    
}
