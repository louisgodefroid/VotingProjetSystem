/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.Candidate;
import Dao.Election;
import Dao.ElectionDao;
import Dao.Vote;
import Dao.VoteDao;
import Dao.Voter;
import Views.IWithCandidateListView;
import java.sql.Connection;

/**
 *
 * @author louis
 */
public class ElectionCandidatesController {
    private final DaoFactory factory;
    private final Voter voter;
    private final Election election;
    
    public ElectionCandidatesController(Voter v, Election e, Connection cx){
        factory = new DaoFactory(cx);
        voter = v;
        election = e;
    }
    public boolean createVote(Candidate c){
        VoteDao dao = factory.getVoteDao();
        Vote v = new Vote();
        v.setVoter(voter);
        v.setCandidate(c);
        v.setElection(election);
        return dao.create(v);
    }
    public void fillCandidateList(IWithCandidateListView view){
        ElectionDao eDao = factory.getElectionDao();
        eDao.fillCandidateList(election, view);        
    }
}
