package Controllers;

import Dao.Candidate;
import Dao.Election;
import Dao.ElectionDao;
import Dao.User;
import Dao.Vote;
import Dao.VoteDao;
import Dao.Voter;
import Dao.VoterDao;
import Views.IElectionView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class VoteController {
    private DaoFactory factory;
    public VoteController(DaoFactory f)
    {
      factory=f;
    }
    
    public Voter getVoter(User v)
    {
        VoterDao dao = factory.getVoterDao();
        return dao.find(v);
    }
    
    public boolean fillCandidateList(Election e, IElectionView view)
    {
        ElectionDao dao = factory.getElectionDao();
        return dao.fillCandidateList(e, view);
    }
    
    public boolean voteFor(Candidate c, Election e, Voter v)
    {
        Vote vote = new Vote();
        vote.setCandidate(c);
        vote.setElection(e);
        vote.setVoter(v);
        VoteDao dao = factory.getVoteDao();
        return dao.create(vote);
    }

}
