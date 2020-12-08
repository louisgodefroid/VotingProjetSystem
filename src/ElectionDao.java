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
    boolean create (Election a);
    boolean delete(Election a);
    Election find (String state,String date);
    boolean updateEtat (Election a);
    
    
}
