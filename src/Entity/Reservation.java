/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author asus
 */
public class Reservation {
    
   
    private int idr;
    private int idformation;
  
    public int idu;
      public String etat;
    public String avis;
    private String username;
    private String nomformation;
    public int nbrres;

    public Reservation() {
    }

    public Reservation(int idformation, int idu, String etat, String avis) {
        this.idformation = idformation;
        this.idu = idu;
        this.etat = etat;
        this.avis = avis;
    }
    

    public Reservation(int idformation, String etat, String avis, int idu) {
        this.idformation = idformation;
        this.etat = etat;
        this.avis = avis;
        this.idu = idu;
    }

    public Reservation(int idr, int idformation, String etat, String avis, int idu, String username, String nomformation) {
        this.idr = idr;
        this.idformation = idformation;
        this.etat = etat;
        this.avis = avis;
        this.idu = idu;
        this.username = username;
        this.nomformation = nomformation;
    }

    public Reservation(int idformation, String etat, String avis, int idu, String username, String nomformation) {
        this.idformation = idformation;
        this.etat = etat;
        this.avis = avis;
        this.idu = idu;
        this.username = username;
        this.nomformation = nomformation;
    }

    public Reservation(String etat, String avis, String username, String nomformation) {
        this.etat = etat;
        this.avis = avis;
        this.username = username;
        this.nomformation = nomformation;
    }
     

    public Reservation(int idr, int idformation, String etat, String avis, int idu) {
        this.idr = idr;
        this.idformation = idformation;
        this.etat = etat;
        this.avis = avis;
        this.idu = idu;
    }

    
    public Reservation(int idr, int idformation, int idu) {
        this.idr = idr;
        this.idformation = idformation;
        this.idu = idu;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public int getIdformation() {
        return idformation;
    }

    public void setIdformation(int idformation) {
        this.idformation = idformation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getNbrres() {
        return nbrres;
    }

    public void setNbrres(int nbrres) {
        this.nbrres = nbrres;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomformation() {
        return nomformation;
    }

    public void setNomformation(String nomformation) {
        this.nomformation = nomformation;
    }

    
    @Override
    public String toString() {
        return "Reservation{" + "idr=" + idr + ", idformation=" + idformation + ", etat=" + etat + ", avis=" + avis + ", idu=" + idu + '}';
    }
     
    
     
     
}
