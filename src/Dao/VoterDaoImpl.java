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
 * @author louis
 */
public class VoterDaoImpl implements VoterDao{
    private Connection connexion;

    public VoterDaoImpl (Connection cx)   
    {
        connexion=cx;
    }

    @Override
    public boolean create(Voter a) {
        try {
            if(find(a.getUser())!=null) /// verif qu'on ait pas deux fois le meme
            {
                return false;
            }
                
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO VOTER(user_id) VALUES (%d,%d)",a.getUser().getId());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(VoterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(Voter a) {
        try {
            
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("DELETE FROM VOTER WHERE user_id=%d ",a.getUser().getId());
            int resultat = statement.executeUpdate(sb.toString());
            ResultSet gk = statement.getGeneratedKeys();
            if (gk.next()) {
                a.setId(gk.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public Voter find(User ax) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT *FROM VOTER WHERE user_id=%d ",ax.getId());
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Voter u=new Voter();
                u.setId(resultat.getInt("id"));
                u.setUser(ax);
                return u;
            }
           
        }
        catch(SQLException ex) {
            Logger.getLogger(VoterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        
        }
        return null;
    }
    
}
