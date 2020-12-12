package Dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *@author louis
 */
public class UserDaoImpl implements UserDao {
    private Connection connexion;

    public UserDaoImpl (Connection cx)   
    {
        connexion=cx;
    }
    @Override
    public boolean create(User a) {
        try {
            if(find(a.getFirst_name(),a.getLast_name())!=null) /// verif qu'on ait pas deux fois le meme
            {
                return false;
            }
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO User(first_name,last_name,email,password,role) VALUES ('%s','%s','%s','%s',%d)", a.getFirst_name(),a.getLast_name(),a.getEmail(),a.getPassword(),a.getRole());
            int resultat = statement.executeUpdate(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            ResultSet gk = statement.getGeneratedKeys();
            if (gk.next()) {
                a.setId(gk.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(User a) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("DELETE FROM USER WHERE  id=%d",a.getId());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public User find(String email,String password) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT *FROM USER WHERE email='%s' AND password='%s' ",email,password);
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                User u=new User();
                u.setId(resultat.getInt("id"));
                u.setFirst_name(resultat.getString("first_name"));
                u.setLast_name(resultat.getString("last_name"));
                u.setEmail(resultat.getString("email"));
                u.setPassword(resultat.getString("password"));
                u.setRole(resultat.getInt("role"));
                return u;
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
}
