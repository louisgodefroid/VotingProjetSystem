/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dao.Candidate;

/**
 *
 * @author Louis
 */
public interface IElectionView {
    // add a candidate to the displayed list
    void addCandidate(Candidate c);
    
}
