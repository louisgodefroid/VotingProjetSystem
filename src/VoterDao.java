/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public interface VoterDao {
    boolean create (Voter a);
    boolean delete(Voter a);
    Voter find (User ax);
    
}
