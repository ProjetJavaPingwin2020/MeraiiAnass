/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author milim
 */
public class Demande {

    private int id;
    private String etat;
    private int idU;
    private int idE;
    private String username;
    private String nomevent;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomevent() {
        return nomevent;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }


    public Demande(String etat, int idU, int idE) {
        this.etat = etat;
        this.idU = idU;
        this.idE = idE;
    }

    public Demande() {
    }

    public Demande(int id, String etat, String username, String nomevent) {
        this.id = id;
        this.etat = etat;
        this.username = username;
        this.nomevent = nomevent;
    }



    public Demande(int id, String etat, int idU, int idE) {
        this.id = id;
        this.etat = etat;
        this.idU = idU;
        this.idE = idE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

}
