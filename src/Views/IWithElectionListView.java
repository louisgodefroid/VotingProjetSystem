/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dao.Election;

/**
 *
 * @author Louis
 */
public interface IWithElectionListView {
    // add an election in the displayed list
    void addElection(Election e);
}
