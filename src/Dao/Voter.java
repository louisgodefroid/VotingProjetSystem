package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class Voter {
    private int id;
    User user;
    public int getId ()
    {
        return id;
    }
    public void setId (int _id)
    {
        id=_id;
    }
    public User getUser ()
    {
        return user;
    }
    public void setUser (User _user)
    {
        user=_user;
    }
}
