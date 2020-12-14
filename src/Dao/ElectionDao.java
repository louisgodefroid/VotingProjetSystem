package Dao;

import Views.IWithCandidateListView;
import Views.IWithElectionListView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public interface ElectionDao {
    Election load(int election_id);
    boolean create (Election a);
    boolean delete(Election a);
    Election find (String state,String date);
    Election find(int id);
    boolean updateEtat (Election a);
    boolean fillCandidateList(Election e, IWithCandidateListView view); 
    boolean fillElectionList(IWithElectionListView view, boolean openOnly);
}
