package Controllers;


import Dao.Election;
import Dao.ElectionDao;
import Dao.ResultDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class ElectionController {
    private final DaoFactory factory;
    public ElectionController(DaoFactory f)
    {
      factory=f;
    }
    
    public Election getElection(String state,String date)
    {
        ElectionDao dao =factory.getElectionDao();
        return dao.find(state, date);
    }
    
    public boolean create (String state,String date)
    {
        Election u=new Election();
        u.setState(state);
        u.setDate(date);
        u.setEtat(Election.BEFORE_ELECTION);
        ElectionDao dao =factory.getElectionDao();
        return dao.create(u);
    }
    
    public boolean startElection (Election e)
    {
        if (e.getEtat()!=Election.BEFORE_ELECTION)
            return false;
        e.setEtat(Election.ELECTION_DAY);
        ElectionDao dao =factory.getElectionDao();
        return dao.updateEtat(e);
    }
    
    public boolean endElection(Election e)
    {
        if(e.getEtat()!=Election.ELECTION_DAY)
        {
            return false;
        }
        e.setEtat(Election.AFTER_ELECTION);
        ElectionDao electionDao =factory.getElectionDao();
        electionDao.updateEtat(e);
        ResultDao resultDao =factory.getResultDao();
        return resultDao.computeResult(e);
        /// GEstion Transaction
    }
    
}
