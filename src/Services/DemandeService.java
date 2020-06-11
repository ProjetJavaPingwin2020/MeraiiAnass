/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Demande;
import Entity.Evenement;
import Entity.FosUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionBase;
import static utils.ConnexionBase.getInstance;

/**
 *
 * @author milim
 */
public class DemandeService {

    Connection cnx;
    ConnexionBase db = getInstance();

    public DemandeService() {
        cnx = db.getCnx();
    }

    private Statement st;
    private PreparedStatement pre;
    private ResultSet res;

    private final String GET_All_USER_Demandes = "select id, etat, idU, idE from demande ";

       public ObservableList<Demande> Affichage() throws SQLException {
        ObservableList<Demande> d = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_USER_Demandes);
        UserService us = new UserService();
        EvenementService es =new EvenementService();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FosUser u = new FosUser();
            Evenement e =new Evenement();
            u=us.getUSERById(rs.getInt(3)); 
            e=es.getEVENTById(rs.getInt(4));
            
            d.add(rtd(rs.getInt(1), rs.getString(2), u.getUsername(),  e.getNom_evenement()));
        }

        return d;
    }
        private Demande rtd(int id, String etat, String  username, String nomevent) {
        return new Demande(id, etat, username, nomevent);
    }
    public void updateapp(Demande d) throws SQLException {
        try {
            PreparedStatement PS = cnx.prepareStatement("UPDATE `test1.1`.`Demande` SET `etat`=? WHERE `id`=?");
            PS.setString(1, d.getEtat());
            PS.setInt(2, d.getId());

            PS.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    
    

   


}
