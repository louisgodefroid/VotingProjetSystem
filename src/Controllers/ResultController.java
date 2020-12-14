/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.Election;
import Dao.ResultDao;
import Views.IWithResultListView;
import java.sql.Connection;

/**
 *
 * @author ppica
 */
public class ResultController {
    private final DaoFactory factory;
    private final Election election;
    
    public ResultController(Connection cx, Election e){
        factory = new DaoFactory(cx);
        election = e;
    }
    
    public void fillResultList(IWithResultListView view){
        ResultDao dao = factory.getResultDao();
        dao.fillResultList(election, view);
    }
}
