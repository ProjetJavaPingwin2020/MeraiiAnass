/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.Evenement;
import Entity.Formateur;
import Entity.Formation;
import Entity.FosUser;
import Entity.Reservation;
import IServices.IService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

/**
 *
 * @author asus
 */
public class ServiceFormation implements IService<Formation>{

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private ResultSet res ;
    static String valeur_combo_type;
     UserService us= new UserService();
    
    public ServiceFormation() {
                cnx = ConnexionBase.getInstance().getCnx();

    }
    
    
    @Override
    public void ajouter(Formation f) throws SQLException {


            String requeteInsert = "INSERT INTO `formation` (`nom`, `type`, `date`,`lieu`, `description`,`heure`,`nbrplace`,`formateur`,`nomImage`) VALUES ( ?,?,?,?,?,?,?,?,?);";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setString(1, f.getNom());
            pre.setString(2, f.getType());
            pre.setDate(3, f.getDate());
            pre.setString(4, f.getLieu());
            pre.setString(5, f.getDescription());
            pre.setString(6, f.getHeure());
             
            pre.setInt(7, f.getNbrplace());
             pre.setInt(8, f.getFormateur());
            pre.setString(9, f.getImage());
            //  pre.setString(9, f.getFormateur().getNom());
           
                pre.executeUpdate();
            System.out.println("Formation ajoutée avec succés");        
    }


    
      public int findid(String id) {
          int idf = -1;
         String requete = " select * from formateur where nom='"+id+"'" ;
        try {
           
            st = cnx.createStatement();
            res=st.executeQuery(requete);
            if (res.next())
            {idf = res.getInt("id");
                System.out.println(idf);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idf ;
        
    }
    /*  public List<Formateur> readfor() throws SQLException {
          
     List<Formateur> AL = new ArrayList<>();
     st = cnx.createStatement();
     ResultSet rs = pre.executeQuery("select * from formateur");
     while (rs.next()) {
     String nom = rs.getString("nom");
     Formateur t = new Formateur(nom);
     AL.add(t);
     }
     return AL;
     }*/
     
    @Override
    public List<Formation> readAll() throws SQLException {
        List<Formation> AL = new ArrayList<>();
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from formation");
        while (rs.next()) {
            //int id = rs.getInt(1);
            String nom = rs.getString("nom");
             String type = rs.getString("type");
             Date date=rs.getDate("date");
             String lieu = rs.getString("lieu");
             String description = rs.getString("description");
             String heure = rs.getString("heure");
            String image = rs.getString("nomImage");
             int nbrplace = rs.getInt(8);
             
           // String formateur = rs.getString(9);
              int formateur = rs.getInt(9);
             
            Formation f = new Formation(nom, type,date,lieu,description, heure,image,nbrplace,formateur);
            AL.add(f);
        }
        return AL;
    }
  public String getFormationImageByID(int id) throws SQLException, FileNotFoundException, IOException {
       String is ="";
        PreparedStatement ps = cnx.prepareStatement("SELECT nomImage FROM formation WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        is = rs.getString("nomImage");
}
        
        return is;
     }

  
    private final String GET_All_Formation = "select id,nom,type,date,lieu,description,heure,nbrplace,formateur from formation ";
     
     public ObservableList<Formation> getAllFormations() throws SQLException {
        ObservableList<Formation> data = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Formation);
        UserService us = new UserService();
        ServiceFormateur sf = new ServiceFormateur();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Formateur formateur = new Formateur();
            Formation f = new Formation();
            formateur=sf.getFormateurById(rs.getInt(9)); 
         
            
           // //u=usrs.getUserByid
            data.add(ResultsToFormation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9)));
        }

        return data;
    } 
       private final String GET_All_Formationbytype = "select id,nom,type,date,lieu,description,heure,nbrplace from formation WHERE type=" + "'" + valeur_combo_type + "'"; 
     
     public ObservableList<Formation> getAllFormationsbytype() throws SQLException {
        ObservableList<Formation> data = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Formationbytype);
        UserService usrs = new UserService();
        ServiceFormation sf = new ServiceFormation();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FosUser u = new FosUser();
            Formation f = new Formation();
            
           // //u=usrs.getUserByid
            data.add(ResultsToFormation1(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)));
        }

        return data;
    } 
    private Formation ResultsToFormation1(int id,String nom, String type,Date date,String lieu,String description,String heure,int nbrplace) {
       return new Formation(id , nom, type,date,lieu,description,heure,nbrplace);
    } 
     private Formation ResultsToFormation(int id,String nom, String type,Date date,String lieu,String description,String heure,int nbrplace,int formateur) {
       return new Formation(id , nom, type,date,lieu,description,heure,nbrplace,formateur);
    } 

     public static int delete(String nom) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from formation where nom='" + nom + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("Formation supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }
      public void updatetab(Formation f) throws SQLException {
        PreparedStatement PS=cnx.prepareStatement("UPDATE `pidev`.`formation` SET `nom`=? ,`type`=? ,`date`=?,`lieu`=?,`description`=? ,`heure`=? ,`nbrplace`=? ,`formateur`=?,`nomImage`=? WHERE `id`=?");
        PS.setString(1,f.getNom());
        PS.setString(2, f.getType());
        PS.setDate(3,f.getDate());
        PS.setString(4,f.getLieu());
        PS.setString(5,f.getDescription());
          PS.setString(6,f.getHeure());
          PS.setInt(7,f.getNbrplace());
          PS.setInt(8,f.getFormateur());
          PS.setString(9, f.getImage());       
        PS.executeUpdate();
    }


   public ObservableList<ImageView> getAllFormationsImages() throws SQLException, FileNotFoundException, IOException {
       String is ="";
        ObservableList<ImageView> img = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("SELECT nomImage FROM formation");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
             is = rs.getString("nomImage");
              Image image = new Image(new FileInputStream("C:\\wamp64\\www\\integration\\test1.1\\web\\imageFormations\\"+is));
               ImageView imgEvt = new ImageView(image);
            img.add(imgEvt);
  
        }
        return img;
    }
    @Override
    public void delete(int id) throws SQLException {
         PreparedStatement ps = cnx.prepareStatement(DELETE_RESERVATION_BY_ID);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Reservation deleted");
    }
    private final String GET_Formation_By_ID = "select id, nom, type, date, lieu, description,heure,nbrplace,formateur,nomImage  from  formation where id=?";
    public Formation getFormationById() throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_Formation_By_ID);
   //     ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return ResultsToFormation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10));
    }

    @Override
   public void update(Formation f,int id) throws SQLException {
        PreparedStatement PS=cnx.prepareStatement("UPDATE `pidev`.`formation` SET `nom`=?,`type`=? ,`date`=?,`lieu`=?,`description`=? ,`heure`=? ,`nbrplace`=? ,`formateur`=? ,`nomImage`=? WHERE `id`=?");
        PS.setString(1,f.getNom());
        PS.setString(2,f.getType());
        PS.setDate(3,f.getDate());
        PS.setString(4,f.getLieu());
        PS.setString(5,f.getDescription());
        PS.setInt(6,id);
        PS.setString(7,f.getImage());
        PS.executeUpdate();
    }
   
   public Formation getByName(String n) {
          Formation a = null;
         String requete = " select* from formation  where (nom like '"+n+"%')" ;
        try {
           
            st = cnx.createStatement();
            res=st.executeQuery(requete);
            if (res.next())
            {a=new Formation(res.getInt(1),res.getString(2), res.getString(3), res.getDate(4),res.getString(5),res.getString(6),res.getString(7),res.getInt(8),res.getInt(9),res.getString(10));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
       
    }
   
     public int getidFormationByName(String s) throws SQLException {
       //PreparedStatement ps = cnx.prepareStatement("SELECT id FROM formation WHERE nom='"+s+"'");
       // ps.setString(1, s);
        PreparedStatement ps = cnx.prepareStatement("SELECT id FROM formation WHERE nom=?");
        ps.setString(1, s);   
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getInt(1);
    }
     
     
     
      public void addReservation(int idf, int iduser) throws SQLException {
          
          Reservation r = null;
        PreparedStatement ps = cnx.prepareStatement("INSERT INTO reservation(idu,idformation,etat,avis) VALUES(?,?,?,?)");
        ps.setInt(1, iduser);
        ps.setInt(2, idf);
        ps.setString(3, "Interessé !");
        ps.setString(4, "Pas encore !");
      
    
      //  ps.setString(3, "En cours !");

        ps.executeUpdate();
        System.out.println("Reservation ajoutée");
      }
      public boolean Reservationexiste(int idf, int iduser) throws SQLException {
        int count = 0;
        PreparedStatement ps = cnx.prepareStatement("select count(*) from reservation where idformation=? and idu=?");
        ps.setInt(1, idf);
        ps.setInt(2, iduser);
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
      public int getNbrPlace(int id) {
        int q = 0;

        String requete4 = "select nbrplace from formation where id=?;";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(requete4);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

  public ObservableList<Formation> RechercheAvanceeFormation(String s) throws SQLException {
        ObservableList<Formation> e = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("select id, nom, type, date, lieu, description, heure,nbrplace,formateur  FROM formation where nom LIKE ?");
                 
        ps.setString(1, "%" + s + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            e.add(ResultsToFormation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(8),rs.getInt(9)));
        }

        return e;
    }
     public void decrementnbr(int id) {
        int q = getNbrPlace(id);
        q--;
        String requete4 = "update formation SET nbrplace=? where id=?;";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(requete4);
            pst.setInt(1, q);

            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }
     
      public void nbrres(int id) {
        int q = getNbrPlace(id);
        q++;
        String requete4 = "update reservation SET nbrres=? where idr=? AND idu=?;";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(requete4);
            pst.setInt(1, q);

            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }
     
      private final String GET_my_res = "select f.nom,f.type,f.date,f.lieu,f.description,f.heure,f.nbrplace from formation f,reservation r where f.id=r.idformation and r.idu=?";
     
     public ObservableList<Formation> getMyRes(int id) throws SQLException {
        ObservableList<Formation> data = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("select f.nom,f.type,f.date,f.lieu,f.description,f.heure,f.nbrplace from formation f,reservation r where f.id=r.idformation and r.idu=?");

        ps.setInt(1, id);
       // UserSevice usrs = new UserSevice();
        //ServiceFormation sf = new ServiceFormation();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
         //   FosUser u = new FosUser();
          //  Formation f = new Formation();
            
           // //u=usrs.getUserByid
            System.out.println(rs.getString(1));
            data.add(ResultsToFormation2(rs.getString(1), rs.getString(2), rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
        }

        return data;
    }  
      private Formation ResultsToFormation2(String nom, String type,Date date,String lieu,String description,String heure,int nbrplace) {
       return new Formation(nom, type,date,lieu,description,heure,nbrplace);
    } 

      public String GetNomFormation(int idd) throws SQLException {
           ResultSet rs;       
        st=cnx.createStatement();
        String name = null;
        
        String req="SELECT * FROM `formation` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         name= rs.getString("nom");
        } 
         return name;
            }
   
      public Reservation getresbyid(int idd) {

        
    Reservation r = new Reservation();
        try {

            String req = "SELECT *  FROM reservation";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if (Integer.parseInt(res.getString("idr"))==idd)
                {
               
                r.setIdr(Integer.parseInt(res.getString("idr")));
                r.setIdformation(Integer.parseInt(res.getString("idformation")));
               r.setIdu(Integer.parseInt(res.getString("idu")));
               
              r.setUsername(us.getusername(r.getIdu()));
               r.setNomformation(GetNomFormation(r.getIdformation()));
               //String a=s2.getnomimage(p.getProduit());
               //Image image = new Image("file:"+a+"",70, 70, true, true);
                //ImageView photo= new ImageView(image);
              //  p.setPhoto2(photo);
                

                return r;
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return r;
    }
      private final String DELETE_RESERVATION_BY_ID = "DELETE FROM reservation WHERE idformation=? AND idu=?";
      public void deleteReservationById(int iduser,int idf) throws SQLException{
       PreparedStatement ps = cnx.prepareStatement(DELETE_RESERVATION_BY_ID);
        ps.setInt(1, iduser);
        ps.executeUpdate();
        System.out.println("Reservation deleted");
    }
      
        private final String GET_All_USER_Reservations = "select idr,idformation, etat, avis, idu from reservation where idu=?";
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
          public Reservation mapResultsToReservation(int idr,int idformation,String etat,String avis, int idu) {
         return new Reservation(idr,idformation,etat,avis,idu);
    }
          
          
         private final String detailform=" select f.nom ,f.type,f.heure,f.description from formation f , reservation r where f.id=r.idformation and idu=?";
          public ObservableList<Formation> Detail(int iduser) throws SQLException {
        ObservableList<Formation> a = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(detailform);
        ps.setInt(1, iduser);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           // a.add(mapResultsToDetail(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)));
        }

        return a;
    }
            public Reservation mapResultsToDetail(String nom,String type, String heure,String description) {
         return new Reservation(nom,type,heure,description);
    }
          
 public Formation findbyid(int id) {
        Formation u = new Formation();
        try {

            PreparedStatement pre = cnx.prepareStatement("Select * from formation  WHERE id=? ");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int nbrplace =rs.getInt("nbrplace");
                String nom = rs.getString("nom");
             
            //  String image = rs.getString("image");
              String lieu=rs.getString("lieu");
             String description=rs.getString("description");
             Date date=rs.getDate("date");
           
          u.setNom(nom);
             
             // u.setImage(image);
              u.setNbrplace(nbrplace);
              u.setLieu(lieu);
              u.setDescription(description);
              u.setDate(date);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
              return u;
    }

    private Formation ResultsToFormation(int aInt, String string, String string0, Date date, String string1, String string2, String string3, int aInt0, int aInt1, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          

}
