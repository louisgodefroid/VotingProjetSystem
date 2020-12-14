package Dao;

import Views.IWithElectionListView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
@author louis  */
public interface CandidateDao {
    boolean create (Candidate a);
    boolean delete(Candidate a);
    Candidate find (User u);
    boolean register(Candidate a, Election e);
    boolean unregister(Candidate a);
}
