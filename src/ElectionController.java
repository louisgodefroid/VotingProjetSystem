
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
public class ElectionController {
      DaoFactory factory;
    public ElectionController(Connection cx)
    {
      factory=new DaoFactory(cx);
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
    public boolean startElection (String state,String date)
    {
        Election u=new Election();
        u.setState(state);
        u.setDate(date);
        u.setEtat(Election.ELECTION_DAY);
        ElectionDao dao =factory.getElectionDao();
        return dao.updateEtat(u);
    
    }
    public boolean endElection(String state,String date)
    {
        ElectionDao dao =factory.getElectionDao();
        Election e=dao.find(state, date);
        if(e.getEtat()!=Election.ELECTION_DAY)
        {
            return false;
        }
        e.setEtat(Election.AFTER_ELECTION);
        ComputeResult result=new ComputeResult(factory.getConnexion());
        result.run(e);
        return dao.updateEtat(e);
        /// GEstion Transaction
    }
}
