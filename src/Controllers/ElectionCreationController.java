/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.Election;
import Dao.ElectionDao;
import Views.IWithElectionListView;
import java.sql.Connection;

/**
 *
 * @author ppica
 */
public class ElectionCreationController {
    private Connection connection;
    private IWithElectionListView electionView;

    public ElectionCreationController(Connection cx, IWithElectionListView view){
        connection = cx;
        electionView = view;        
    }
    public void create(String name, String state, String date){
        Election e = new Election();
        e.setName(name);
        e.setState(state);
        e.setDate(date);
        e.setEtat(Election.BEFORE_ELECTION);
        DaoFactory factory = new DaoFactory(connection);
        ElectionDao dao = factory.getElectionDao();
        if (dao.create(e))
        {
            electionView.addElection(e);
        }
    }
}
