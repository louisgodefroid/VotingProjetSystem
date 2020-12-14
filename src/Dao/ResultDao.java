package Dao;

import Views.IWithResultListView;
import Views.IWithUserListView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *@author louis
 */
public interface ResultDao {
    boolean computeResult(Election a);
    boolean delete(Result a);
    Result find (Candidate a,Election b);
    boolean fillResultList(Election e, IWithResultListView view);   
}
