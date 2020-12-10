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
import Dao.User;
import Views.ICandidateView;

public class CandidateController {
    private final DaoFactory factory;
    public CandidateController(DaoFactory f)
    {
        factory=f;
    }

    public Candidate getCandidate(User u)
    {
        if (u.getRole()!=User.CANDIDAT)
        {
            return null;
        }
        CandidateDao dao = factory.getCandidateDao();
        return dao.find(u);
    }
    
    public boolean setCandidacy(Candidate c, Election e)
    {
        c.setElection(e);
        CandidateDao dao = factory.getCandidateDao();
        return dao.subscribe(c, e);
    }
    
    public boolean fillElectionList(ICandidateView view)
    {
        ElectionDao dao = factory.getElectionDao();
        dao.fillElectionList(view);
        return false;
    }
}
