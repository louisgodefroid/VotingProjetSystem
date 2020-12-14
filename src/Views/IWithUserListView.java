/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dao.User;

/**
 *
 * @author louis
 */
public interface IWithUserListView {
    // add a user in the displayed list
    void addUser(User e);   
}
