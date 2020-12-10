package Dao;

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
    
}
