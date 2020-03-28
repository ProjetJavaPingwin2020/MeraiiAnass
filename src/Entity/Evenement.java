/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author milim
 */
public class Evenement {
     private  int id_event;
    private  int chef_id;
    private String nom_evenement;
    private String adresse;
    private Date date;
    private String description;
    private String heure;
    private InputStream image;
    
    public Evenement() {
    }

    public Evenement(int id_event, String nom_evenement, Date date,String heure, String adresse, String description,int chef_id) {
        this.id_event = id_event; 
        this.chef_id = chef_id;
        this.nom_evenement = nom_evenement;
        this.adresse = adresse;
        this.date = date;
        this.heure = heure;
        this.description = description;
    }

    public Evenement(int id_event, int chef_id, String nom_evenement, String adresse, Date date, String description, String heure, InputStream image) {
        this.id_event = id_event;
        this.chef_id = chef_id;
        this.nom_evenement = nom_evenement;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
        this.heure = heure;
        this.image = image;
    }

    public Evenement(int id_event, String nom_evenement, String adresse, String description, InputStream image) {
        this.id_event = id_event;
        this.nom_evenement = nom_evenement;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
    }

    public Evenement(String nom_evenement, String adresse, String description, InputStream image) {
        this.nom_evenement = nom_evenement;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
    }
        public Evenement(String nom_evenement, String adresse, String description,Date date, String heure, int chef_id,InputStream img) {
        this.nom_evenement = nom_evenement;
        this.adresse = adresse;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.chef_id = chef_id;
        this.image = img;
    }

    public Evenement(int chef_id, String nom_evenement, String adresse, Date date, String description, String heure, InputStream image) {
        this.chef_id = chef_id;
        this.nom_evenement = nom_evenement;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
        this.heure = heure;
        this.image = image;
    }

    public Evenement(String ss, String ss0, String ss1, String ss2, String ss3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", chef_id=" + chef_id + ", nom_evenement=" + nom_evenement + ", adresse=" + adresse + ", date=" + date + ", description=" + description + ", heure=" + heure + ", image=" + image + '}';
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getChef_id() {
        return chef_id;
    }

    public void setChef_id(int chef_id) {
        this.chef_id = chef_id;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
    
}
