package Controllers;


import Dao.VoteDaoImpl;
import Dao.VoteDao;
import Dao.CandidateDaoImpl;
import Dao.CandidateDao;
import Dao.ElectionDaoImpl;
import Dao.ElectionDao;
import Dao.ResultDaoImpl;
import Dao.ResultDao;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Dao.VoterDaoImpl;
import Dao.VoterDao;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class DaoFactory {
     private Connection connexion;

     public Connection getConnexion ()
     {
         return connexion;
     }

    public DaoFactory (Connection cx)   
    {
        connexion=cx;
    }
    public UserDao getUserDao()
     {
         return new UserDaoImpl(connexion);
     }
    public CandidateDao getCandidateDao()
    {
        return new CandidateDaoImpl(connexion);
                
    }
    public ElectionDao getElectionDao()
    {
        return new ElectionDaoImpl(connexion);
    }
     public ResultDao getResultDao()
    {
        return new ResultDaoImpl(connexion);
    }
     public VoteDao getVoteDao()
    {
        return new VoteDaoImpl(connexion);
    }
     public VoterDao getVoterDao()
    {
        return new VoterDaoImpl(connexion);
    }
}
