package Dao;


import Views.ICandidateView;
import Views.IElectionView;
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
public class ElectionDaoImpl implements ElectionDao {
    private Connection connexion;

    public ElectionDaoImpl (Connection cx)   
    {
        connexion=cx;
    }

    @Override
    public Election load(int election_id)
    {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT * FROM ELECTION WHERE id=%d",election_id);
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Election u=new Election();
                u.setId(election_id);
                u.setState(resultat.getString("state"));
                u.setDate(resultat.getString("date"));
                u.setEtat(resultat.getInt("etat"));
                return u;
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(Election a) {
        try {
            if(find(a.getState(),a.getDate())!=null) /// verif qu'on ait pas deux fois le meme
            {
                return false;
            }
                
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO ELECTION(state,date,etat,election) VALUES ('%s','%s',%d)", a.getState(),a.getDate(),a.getEtat());
            int resultat = statement.executeUpdate(sb.toString());
            ResultSet gk = statement.getGeneratedKeys();
            if (gk.next()) {
                a.setId(gk.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(Election a) {
        try {
            
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("DELETE FROM ELECTION WHERE  state='%s' AND date='%s' ",a.getState(),a.getDate());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public Election find(String state, String date) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT *FROM ELECTION WHERE state='%s' AND date='%s' ",state,date);
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Election u=new Election();
                u.setId(resultat.getInt("id"));
                u.setState(resultat.getString("state"));
                u.setDate(resultat.getString("date"));
                u.setEtat(resultat.getInt("etat"));
                return u;
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Election find(int id) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT * FROM ELECTION WHERE id=%d",id);
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Election u=new Election();
                u.setId(id);
                u.setState(resultat.getString("state"));
                u.setDate(resultat.getString("date"));
                u.setEtat(resultat.getInt("etat"));
                return u;
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        
        }
        return null;
    }

    @Override
      public boolean updateEtat (Election a)
      {
           try {
            
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("UPTDATE ELECTION SET ETAT=%d WHERE  state='%s' AND date='%s' ",a.getEtat(),a.getState(),a.getDate());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
      
      }
      
    @Override
      public boolean fillCandidateList(Election e, IElectionView view)
      {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT * FROM Candidate WHERE election_id=%d",e.getId());
            ResultSet resultat = statement.executeQuery(sb.toString());
            while(resultat.next())
            {
                Candidate c=new Candidate();
                c.setId(resultat.getInt("id"));
                c.setDescription(resultat.getString("description"));
                c.setElection(e);
                view.addCandidate(c);
            }
            return true;
        }
        catch(SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
      }

    @Override
      public boolean fillElectionList(ICandidateView view)
      {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT * FROM Election");
            ResultSet resultat = statement.executeQuery(sb.toString());
            while(resultat.next())
            {
                Election e=new Election();
                e.setId(resultat.getInt("id"));
                e.setState(resultat.getString("state"));
                e.setDate(resultat.getString("state"));
                e.setEtat(resultat.getInt("etat"));
                view.addElection(e);
            }
            return true;
        }
        catch(SQLException ex) {
            Logger.getLogger(ElectionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
      }
}
