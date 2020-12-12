package Dao;


import Controllers.DaoFactory;
import Views.IElectionView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import Views.IWithElectionListView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class CandidateDaoImpl implements CandidateDao{
    private Connection connexion;

    public CandidateDaoImpl (Connection cx)   
    {
        connexion=cx;
    }

    @Override
    public boolean create(Candidate a) {
         try {
             if(a.getElection()==null)
             {
                 System.out.println("Erreur objet election manquant");
                 return false;
             }
            if(find(a.getUser())!=null) /// verif qu'on ait pas deux fois le meme
            {
                return false;
            }
                
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO CANDIDATE(user_id,description,election_id) VALUES (%d,'%s',%d)", a.getUser().getId(),a.getDescription(),a.getElection().getId());
            int resultat = statement.executeUpdate(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            ResultSet gk = statement.getGeneratedKeys();
            if (gk.next()) {
                a.setId(gk.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(Candidate a) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("DELETE FROM CANDIDATE WHERE  id=%d",a.getId());
            int resultat = statement.executeUpdate(sb.toString());
            
            // delete the corresponding user too
            DaoFactory factory = new DaoFactory(connexion);
            UserDao dao=factory.getUserDao();
            User u = new User();
            u.setId(a.getUser().getId());
            dao.delete(u);
        } catch (SQLException ex) {
            Logger.getLogger(CandidateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Candidate find(User u) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT * FROM CANDIDATE WHERE user_id=%d",u.getId());
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Candidate c=new Candidate();
                c.setId(resultat.getInt("id"));
                c.setUser(u);
                c.setDescription(resultat.getString("description"));
                DaoFactory factory = new DaoFactory(connexion);
                ElectionDao dao = factory.getElectionDao();
                c.setElection(dao.load(u.getId()));
                return c;
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(CandidateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean subscribe(Candidate a, Election e)
    {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("UPDATE CANDIDATE SET election_id=%d WHERE id=%d", e.getId(),a.getId());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CandidateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        return true;        
    }
    
}
