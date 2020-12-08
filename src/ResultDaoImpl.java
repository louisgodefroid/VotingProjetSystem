
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
public class ResultDaoImpl implements ResultDao {
    private Connection connexion;

    public ResultDaoImpl (Connection cx)   
    {
        connexion=cx;
    }

   @Override
    public boolean create(Result a) {
         try {
             
             if(a.getCandidate()==null) 
             {
                 System.out.println("Erreur Candidat null");
                 return false;
                 
             }
             if(a.getElection()==null) 
             {
                 System.out.println("Erreur Election null");
                 return false;
                 
             }
             if(find(a.getCandidate(),a.getElection())!=null) /// on verifie que le resultat n'existe pas deja
            {
                return false;
            }
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO Result(Candidate_id,Election_id,ballot_count) VALUES (%d,%d,%d)",a.getCandidate().getId(),a.getElection().getId(),a.getBallotCount());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ResultDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(Result a) {
        try {
            
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("DELETE FROM RESULT WHERE  election_id=%d AND candidate_id=%d",a.getElection().getId(),a.getCandidate().getId());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ResultDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public Result find(Candidate a, Election b) {
          try {
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("SELECT *FROM RESULT WHERE candidate_id=%d AND election_id=%d ",a.getId(),b.getId());
            ResultSet resultat = statement.executeQuery(sb.toString());
            if(resultat.next())
            {
                Result u=new Result();
                u.setCandidate(a);
                u.setElection(b);
                u.setBallotCount(resultat.getInt("ballot_count"));
                return u;
            }
           
        }
        catch(SQLException ex) {
            Logger.getLogger(ResultDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        
        }
        return null;
    }
    
}
