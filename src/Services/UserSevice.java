/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import utils.session;
import java.sql.SQLException;
import java.util.Calendar;
import Entity.FosUser;
import Entity.Login;
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
public class UserSevice {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    private final String GET_USER_BY_username = "SELECT * FROM fos_user WHERE username=?";
    // private final String CREATE_USER = "INSERT INTO fos_user(nom,prenom,sexe,datedenaissance,email,email_canonical,adresse,telephone,username,username_canonical,password,grade,enabled,roles) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String GET_USER_BY_ID = "SELECT * FROM fos_user WHERE id=?";
    private final String GET_ID_USER_BY_username = "SELECT id FROM fos_user WHERE username=?";
    private final String GET_ALL_USER = "SELECT *FROM fos_user";
    //    private final String DELETE_USER_BY_ID="DELETE FROM membre WHERE id=?";
    // private final String UPDATE_USER = "UPDATE fos_user SET nom=?,prenom=?,sexe=?,datedenaissance=?,email=?,adresse=?,telephone=?,password=?,grade=? WHERE id=?";
    // private final String GET_USER_BY_USERNAME = "SELECT * FROM fos_user WHERE nom=?";

    public UserSevice() {
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

            String req = "INSERT INTO `fos_user`(`username`,`username_canonical`, `email`,`email_canonical`,`enabled`,`password`,`last_login`, `roles`) "
                    + "VALUES ('" + u.getUsername() + "','" + can + "','" + u.getEmail() + "','" + mail + "','" + en + "','" + u.getPassword() + "','" + u.getLast_login()
                    + "','" + u.getRoles() + "') ";

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
        user = null;
        try {
            String req = "select * from fos_user where username =?  ";
            PreparedStatement preparedStatement;

            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                try {

                    if (Cryptage.decrypte(resultSet.getString(8)).equals(password) && (resultSet.getString(2).equals(username))) {

                        user = new FosUser(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("email"), password, resultSet.getString("roles"));

                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserSevice.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                user = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSevice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

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

    private FosUser mapResultsToUSER(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_token, Date password_requested_at, String roles) {
        return new FosUser(id, username, username_canonical, email, email_canonical, enabled, salt, password, last_login, confirmation_token, password_requested_at, roles);
    }

    public FosUser getUSERById(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_USER_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return mapResultsToUSER(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12));
    }

    public int getidUSERByusername() throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(GET_ID_USER_BY_username);
        ps.setString(1, Login.getUsername());
        ResultSet rs = ps.executeQuery();
        rs.next();//next return boolean
        return rs.getInt(1);
    }

}
