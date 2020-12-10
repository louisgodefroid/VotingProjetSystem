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
public class Candidate {
    private int id;
    private User user;
    private String description;
    private Election election;
    public int getId ()
    {
        return id;
    }
    public void setId (int _id)
    {
        id=_id;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser(User u)
    {
        user = u;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription (String _description)
    {
        description=_description;
    }
    public Election getElection ()
    {
        return election;
    }
    public void setElection (Election _election)
    {
        election=_election;
    }
}
