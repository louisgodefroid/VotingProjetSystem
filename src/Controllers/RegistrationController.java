/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.Candidate;
import Dao.CandidateDao;
import Dao.Election;
import Dao.ElectionDao;
import Views.IWithElectionListView;
import java.sql.Connection;

/**
 *
 * @author louis
 */
public class RegistrationController {
    private final Connection connection;
    private final DaoFactory factory;
    private final CandidateDao dao;
    private final Candidate candidate;
    
    public RegistrationController(Candidate cd, Connection cx){
        connection = cx;
        factory = new DaoFactory(cx);
        dao = factory.getCandidateDao();
        candidate = cd;
    }
    
    public boolean register(Election e) {
        return dao.register(candidate, e);
    }
    
    public boolean unregister(){
        return dao.unregister(candidate);
    }
    
    public void fillElectionList(IWithElectionListView view, boolean startedOnly){
        ElectionDao dao = factory.getElectionDao();
        dao.fillElectionList(view, startedOnly);
    }
}
