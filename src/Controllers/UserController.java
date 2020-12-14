package Controllers;

import Dao.Candidate;
import Dao.CandidateDao;
import Dao.User;
import Dao.UserDao;
import Dao.Voter;
import Dao.VoterDao;
import Views.IWithUserListView;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class UserController {
    private final DaoFactory factory;

    public UserController(Connection cx)
    {
        factory = new DaoFactory(cx);
    }
    
    public ElectionController getElectionController(User u)
    {
        if (u.getRole()!=User.ADMIN)
            return null;
        
        return new ElectionController(factory);
    }
    
    public VoteController getVoteController(User u)
    {
        if (u.getRole()!=User.VOTANT)
            return null;
        return new VoteController(factory);
    }
    
    private User create (String first_name, String last_name, String email, String password, int role)
    {
        User u=new User();
        u.setFirst_name(first_name);
        u.setLast_name(last_name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);
        UserDao dao=factory.getUserDao();
        dao.create(u);
        return u;
    }
    public User createAdmin (String first_name, String last_name, String email, String password)
    {
        return create(first_name,last_name,email,password,User.ADMIN);
    }
    public Candidate createCandidate(String first_name, String last_name, String email, String description, String password)
    {
        User u = create(first_name,last_name,email,password,User.CANDIDAT);
        CandidateDao dao=factory.getCandidateDao();
        Candidate c = new Candidate();
        c.setUser(u);
        c.setDescription(description);
        dao.create(c);
        return c;
    }
    public Voter createVotant (String first_name, String last_name, String email, String password)
    {
        User u = create(first_name,last_name,email,password,User.VOTANT);
        VoterDao vDao = factory.getVoterDao();
        Voter v = new Voter();
        v.setUser(u);
        vDao.create(v);
        return v;
    }
    public boolean delete (User u)
    {
        if (u.getRole() == User.CANDIDAT)
        {
            // delete candidate too
            CandidateDao dao=factory.getCandidateDao();
            Candidate c = dao.find(u);
            dao.delete(c);
        }
        else if (u.getRole() == User.VOTANT)
        {
            VoterDao dao = factory.getVoterDao();
            Voter v = dao.find(u);
            dao.delete(v);
        }
        UserDao dao=factory.getUserDao();
        return dao.delete(u);
    }
    public void fillUserList(IWithUserListView view){
        UserDao dao=factory.getUserDao();
        dao.fillUserList(view);
    }
}
