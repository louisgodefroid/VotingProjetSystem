
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
            if(find(a.getFirst_name(),a.getLast_name(),a.getElection())!=null) /// verif qu'on ait pas deux fois le meme
            {
                return false;
            }
                
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO CANDIDATE(first_name,last_name,description,election_id) VALUES ('%s','%s','%s',%d)", a.getFirst_name(),a.getLast_name(),a.getDescription(),a.getElection().getId());
            int resultat = statement.executeUpdate(sb.toString());
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
            formatter.format("DELETE FROM CANDIDATE WHERE  first_name='%s' AND last_name='%s' ",a.getFirst_name(),a.getLast_name());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CandidateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
        
        
    }

    @Override
    public Candidate find(String first_name, String last_name,Election a) {
        try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT *FROM CANDIDATE WHERE first_name='%s' AND last_name='%s' AND election_id=%d ",first_name,last_name,a.getId());
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Candidate u=new Candidate();
                u.setId(resultat.getInt("id"));
                u.setFirst_name(resultat.getString("first_name"));
                u.setLast_name(resultat.getString("last_name"));
                u.setDescription(resultat.getString("description"));
                u.setElection(a);
                return u;
            }
           
        }
        catch(SQLException ex) {
            Logger.getLogger(CandidateDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        
        }
        return null;
    }
    
    
}
