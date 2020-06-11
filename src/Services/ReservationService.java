/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import Entity.FosUser;
import Entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionBase;

/**
 *
 * @author asus
 */
public class ReservationService {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    UserService us=new UserService();
    
    private final String CREATE_RESERVATION = "INSERT INTO reservation(idformation,,etat,avis,idu) VALUES(?,?,?,?)";
    private final String GET_RESERVATION_BY_ID = "SELECT idr,idformation,etat,avis,idu FROM reservation WHERE idu=?";
    private final String GET_RESERVATION_BY_IDformation = "SELECT idr,idformation,etat,avis,idu FROM reservation WHERE idformation=?";
    private final String GET_ALL_RESERVATION = "SELECT idr,idformation,etat,avis,idu FROM reservation";
    private final String DELETE_RESERVATION_BY_ID = "DELETE FROM reservation WHERE idu=?";
   // private final String UPDATE_RESERVATION= "UPDATE participant SET etat=?,avis=? WHERE idM=?";
     private final String GET_RESERVATION_BY_NOM = "SELECT idr,idformation,etat,avis,idu FROM reservation WHERE idu=?";
     private final String GET_All_USER_Reservations = "select idformation, etat, avis, idu from reservation where idu=?";
    public ReservationService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }
   /*public void createReservation(Formation f) throws SQLException {
       PreparedStatement ps = cnx.prepareStatement(CREATE_RESERVATION);
    
       ps.setInt(1,f.getId());
       ps.setString(2,"interessé");
       ps.setString(3,"pas encore");
       ps.setInt(4,us.getidUSERByusername());

        ps.executeUpdate();
        System.out.println("Réservation created");
    }*/
    public Reservation mapResultsToReservation(int idr,int idformation,String etat,String avis, int idu) {
         return new Reservation(idr,idformation,etat,avis,idu);
    }
    public void createReservation( Formation f ) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(CREATE_RESERVATION);
       ps.setInt(1,us.getidUSERByusername());
         ps.setInt(2,f.getId());
        ps.setString(3,"interessé");
        ps.setString(4,"pas encore");
      

        ps.executeUpdate();
        System.out.println("Réservation created");
    }

  
    public Reservation getReservationById(int idu)throws SQLException {
          PreparedStatement ps = cnx.prepareStatement(GET_RESERVATION_BY_ID);
        ps.setInt(1, idu);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
         System.out.println("la reservation correspondante est:");
        return mapResultsToReservation(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5));
    }

     public void addReservation(int idf, int iduser) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("INSERT INTO reservation(idformation,etat,avis,idu) VALUES(?,?,?,?)");
        ps.setInt(1, idf);
        ps.setString(2, "Interessé !");
        ps.setString(3, "Pas encore !");
        ps.setInt(4, iduser);
      //  ps.setString(3, "En cours !");

        ps.executeUpdate();
        System.out.println("Reservation ajoutée");
      }
 
    public ObservableList<Reservation> getAll()throws SQLException {
          ObservableList<Reservation> formation = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_ALL_RESERVATION);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            formation.add(mapResultsToReservation(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5)));
        }
 System.out.println("la liste des reservations:");
        return formation;
    }

 
    public void deleteReservationById(int idu) throws SQLException{
       PreparedStatement ps = cnx.prepareStatement(DELETE_RESERVATION_BY_ID);
        ps.setInt(1, idu);
        ps.executeUpdate();
        System.out.println("Reservation deleted");
    }
    public boolean getReservationByIdformation(int idformation)throws SQLException {
          PreparedStatement ps = cnx.prepareStatement(GET_RESERVATION_BY_IDformation);
        ps.setInt(1, idformation);
        ResultSet rs = ps.executeQuery();
         ObservableList<Reservation> formation = FXCollections.observableArrayList();
      
        while (rs.next()) {
             formation.add(mapResultsToReservation(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5)));
        }
   if(formation.size()==0){return true;}
   else{
       System.out.println("La reservation existe deja");
       return false;
   }
   }
    
     public boolean Reservationexiste(int id, int idu) throws SQLException {
        int count = 0;
        PreparedStatement ps = cnx.prepareStatement("select count(*) from reservation where idformation=? and idu=?");
        ps.setInt(1, id);
        ps.setInt(2, idu);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }
     }
     
   


    public void createReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public ObservableList<Reservation> getmyReservations(int iduser) throws SQLException {
        ObservableList<Reservation> a = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_USER_Reservations);
        ps.setInt(1, iduser);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            a.add(mapResultsToReservation(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getInt(5)));
        }

        return a;
    }
  /*public ObservableList<Reservation> Affichage() throws SQLException {
        ObservableList<Reservation> r = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_USER_Reservations);
        UserSevice us = new UserSevice();
        ServiceFormation sf =new ServiceFormation();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FosUser u = new FosUser();
            Formation f =new Formation();
            u=us.getUSERById(rs.getInt(3)); 
            f=sf.getFormationById(rs.getInt(4));
            
            r.add(rtd((f.getNom(),rs.getString(2),rs.getString(3),u.getUsername()));
           
        }

        return r;
    }*/
      public Reservation ResultsToReservation(int idr,int idformation, int idu) {
         return new Reservation(idr,idformation,idu);
    }

    public void deleteReservationById(int idUSERByusername, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}