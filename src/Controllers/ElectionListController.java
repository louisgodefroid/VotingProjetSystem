/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.Election;
import Dao.ElectionDao;
import Dao.User;
import Dao.VoteDao;
import Dao.Voter;
import Dao.VoterDao;
import Views.IWithElectionListView;
import java.sql.Connection;

/**
 *
 * @author ppica
 */
public class ElectionListController {
    private final Connection connection;
    private final DaoFactory factory;
    private final ElectionDao dao;
    private final VoterDao vDao;
    private final User user;
    
    public ElectionListController(User u, Connection cx){
        connection = cx;
        factory = new DaoFactory(cx);
        dao = factory.getElectionDao();
        vDao = factory.getVoterDao();
        user = u;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public boolean deleteElection(Election e){
        return dao.delete(e);
    }
    
    public void startElection(Election e){
        e.setEtat(Election.ELECTION_DAY);
        ElectionDao dao1 = factory.getElectionDao();
        dao1.updateEtat(e);
    }
    
    public void endElection(Election e){
        e.setEtat(Election.AFTER_ELECTION);
        ElectionDao dao1 = factory.getElectionDao();
        dao1.updateEtat(e);        
    }
    
    public boolean votedAlready(Election e){
        VoteDao vDao1 = factory.getVoteDao();
        return vDao1.exists(getVoter(), e);
    }
    
    public Voter getVoter(){
        return vDao.find(user);
    }
    
    public void fillElectionList(IWithElectionListView view, boolean startedOnly){
        dao.fillElectionList(view, startedOnly);        
    }
}
