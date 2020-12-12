package Dao;


import Dao.VoteDao;
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
public class VoteDaoImpl implements VoteDao {
    private Connection connexion;

    public VoteDaoImpl (Connection cx)   
    {
        connexion=cx;
    }

    @Override
    public boolean create(Vote a) {
         try {
            if(a.getVoter()==null)
            {
                System.out.println("Erreur : Voteur manquant");
            }
            if(a.getCandidate()==null)
            {
                System.out.println("Erreur : Candidat manquant");
            }
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO VOTE(voter_id,candidate_id,election_id) VALUES (%d,%d,%d)", a.getVoter().getId(),a.getCandidate().getId(),a.getElection().getId());
            int resultat = statement.executeUpdate(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            ResultSet gk = statement.getGeneratedKeys();
            if (gk.next()) {
                a.setId(gk.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(Election b) {
          try {
            
            Statement statement;
            statement = connexion.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("DELETE FROM VOTE WHERE  election_id=%d",b.getId());
            int resultat = statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(VoteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
