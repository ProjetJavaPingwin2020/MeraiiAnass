/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import java.sql.SQLException;
import java.util.Calendar;
import Entity.FosUser;
import Entity.Login;
import Entity.SendMail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBase;

/**
 *
 * @author Yass
 */
public class UserService {
UpdatableBCrypt s2 = new UpdatableBCrypt(10);
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public UserService() {
        cnx = ConnexionBase.getInstance().getCnx();
    }

public void ajouterUser(FosUser u) {
        try {
            String e = u.getUsername();
            String em = u.getEmail();
            u.setUsername_canonical(e);
            u.setEmail_canonical(em);
            String can = u.getUsername_canonical();
            String mail = u.getEmail_canonical();

            u.setEnabled(1);
            int en = u.getEnabled();
            System.out.println(01);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            u.setLast_login(date);
            System.out.println(02);

         /*   String req = "INSERT INTO `fos_user`(`username`,`username_canonical`, `email`,`email_canonical`,`enabled`,`password`,`last_login`, `roles`) "
                    + "VALUES ('" + u.getUsername() + "','" + can + "','" + u.getEmail() + "','" + mail + "','" + en + "','" + u.getPassword() + "','" + u.getLast_login()
                    + "','" + u.getRoles() + "') ";*/
                String req = "INSERT INTO `fos_user`(`username`,`username_canonical`, `email`,`email_canonical`,`enabled`,`password`,`last_login`, `roles`, `nom`, `prenom`, `adresse`, `telephone`) "
                    + "VALUES ('"+u.getUsername()+"','"+can+"','"+u.getEmail()+"','"+mail+"','"+en+"','"+u.getPassword()+"','"+u.getLast_login()
                    +"','"+u.getRoles()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getAdresse()+"','"+u.getTelephone()+"') ";
            

            System.out.println(03);

            st = cnx.createStatement();

            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public FosUser existLogin(String username, String password) {
        
                Cryptage Cryptage = new Cryptage("lv39eptlvuhaqqsr");
        FosUser user = new FosUser();
        user=null;
        try {
            String req = "select * from fos_user where username =?  ";
            PreparedStatement preparedStatement;

            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString(8));
                System.out.println( s2.verifyHash(password,resultSet.getString(8)));
                System.out.println((password));
                try {
                   
                    if ( s2.verifyHash(password,resultSet.getString(8)) && (resultSet.getString(2).equals(username) )) {
                        
                        
                        user = new FosUser(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("email"), password, resultSet.getString("roles"));
                       
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
               
                user = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return user;
    }
    
    /*
    //ajoutitha ena
    public static FosUser login(String usernam, String pwd) throws SQLException {
        String req = "SELECT * from Fos_user where username='" + usernam + "' and password='" + pwd + "'";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        FosUser u = new FosUser();
        ResultSet rs = pre.executeQuery(req);
        // System.out.println(rs);
        while (rs.next()) {
            String emalil = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String role = rs.getString("role");
            FosUser uu = new FosUser(username, emalil, password, role);
            u = uu;
        }
        return u;
    }*/

    public void ajouterlogin(FosUser u) {
        if (u.getId() != 0 && u.getEmail() != null && u.getUsername() != null) {

            try {
                String e = u.getUsername();
                String em = u.getEmail();
                int id_user = u.getId();

                String req = "INSERT INTO `login`(`id_user`,`username`,`mail`) "
                        + "VALUES ('" + id_user + "','" + e + "','" + em + "') ";

                st = cnx.createStatement();

                st.executeUpdate(req);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void deletelogin() {
        try {

            String req = "DELETE FROM `login` ";

            st = cnx.createStatement();

            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getlogin() throws SQLException {
        ResultSet rs;

        st = cnx.createStatement();
        String pseudoL = null;
        int i = 0;

        String req = "SELECT id_user FROM `login` ";
        rs = st.executeQuery(req);
        while (rs.next()) {

            pseudoL = rs.getString("id_user");
        }
        return pseudoL;
    }

     private final String GET_USER_BY_ID = "SELECT * FROM fos_user WHERE id=?";
      public FosUser getUSERById(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_USER_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return mapResultsToUSER(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }
       private FosUser mapResultsToUSER(int id, String username,  String username_canoncial, String email,  String email_canoncial) {
        return new FosUser(id, username, username_canoncial, email, email_canoncial);
    }
   
    private final String GET_ID_USER_BY_username = "SELECT id FROM fos_user WHERE username=?";
    
    public int getidUSERByusername() throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_ID_USER_BY_username);
        ps.setString(1, Login.getUsername());
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getInt(1);
    }
     private final String GET_email_USER_BY_username = "SELECT email FROM fos_user where username=?";
    public int getemailUSERByusername() throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_email_USER_BY_username);
        ps.setString(1, Login.getUsername());
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getInt(1);
    }
    public FosUser getUSERByusername() throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("select id,username,email from fos_user where username=?");
        ps.setString(1, Login.getUsername());
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return mapResultsToUSER(rs.getInt(1), rs.getString(2), rs.getString(3));
    }

    private FosUser mapResultsToUSER(int id,String username,String email) {
       return new FosUser(id,username,email); //To change body of generated methods, choose Tools | Templates.
    }


    
    public String getEmailUSERByusername() throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT email FROM fos_user WHERE username=?");
        ps.setString(1, Login.getUsername());
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getString(1);
    }
    
    
     public int countMembres() throws SQLException{
        int count = 0;
        PreparedStatement ps = cnx.prepareStatement("SELECT COUNT(*) FROM fos_user");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
     
      public String getEmailAllusers(Formation f) throws SQLException {
         String s="";
        PreparedStatement ps = cnx.prepareStatement("SELECT email FROM fos_user ");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SendMail.sendMail(rs.getString(1), "Une nouvelle Formation ", "La nouvelle formation nommée : " +  f.getNom()  + "\n"+ "De type : " + f.getType()  + "\n"+ "Se déroulera le : " + f.getDate()  + "\n"+" à : "+ f.getHeure()  + "\n"+ "à : " + f.getLieu()  + "\n" + "ayant pour but de : " + f.getDescription());
        }
        return s;
    }
       
            
       public String getloginusername() throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT username FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            } 
            
            public String getusername(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `fos_user` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            }

            
            public String gettelephone(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM `fos_user` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("telephone");
        } 
         return pseudoL;
            }
            
   
            
            
            
            
            
 public List<String> listeuser() {

        List<String> listC = new ArrayList<>();

        try {

            String req = "SELECT * FROM fos_user  ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                
                {
                String p=null;
               p=res.getString("username");
               

                listC.add(p);
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
}
