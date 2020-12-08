
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class ComputeResult {
    private Connection co;
    public ComputeResult (Connection cx)
    {
        co=cx;
    }
    
    public boolean run (Election a)
    {
        if(a.getEtat()!=Election.AFTER_ELECTION)
            return false;
       
     /// on utilise un GROUP BY pour compter le nombre de vote par candidat et on insere directement dans RESULT    
        try {
           
            Statement statement;
            statement = co.createStatement();
            StringBuilder sb = new StringBuilder();
           
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("INSERT INTO RESULT (candidate_id,election_id,ballot_count)SELECT candidate_id,election_id,COUNT(*) FROM VOTE WHERE election_id=%d GROUP BY candidate_id,election_id",a.getId());
            int resultat = statement.executeUpdate(sb.toString());
            return true;
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
            
        }
        
    }
}
