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
public class Login {

    static int id;
    static int id_user;
    static String username;
    static String mail;
    static String nomformation;
    static String nom_event;

    public Login() {
    }

    public Login(int id, int id_user, String username, String mail) {
        this.id = id;
        this.id_user = id_user;
        this.username = username;
        this.mail = mail;
    }
    
      public Login(int id_user, String username, String mail) {
        this.id = id;
        this.id_user = id_user;
        this.username = username;
        this.mail = mail;
    }
    
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Login.id = id;
    }

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        Login.id_user = id_user;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Login.username = username;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        Login.mail = mail;
    }

    public static String getNomformation() {
        return nomformation;
    }

    public static void setNomformation(String nomformation) {
        Login.nomformation = nomformation;
    }

    public static String getNom_event() {
        return nom_event;
    }

    public static void setNom_event(String nom_event) {
        Login.nom_event = nom_event;
    }

}
