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
    String first_name;
    String last_name;
    String description;
    Election election;
    public int getId ()
    {
        return id;
    }
    public void setId (int _id)
    {
        id=_id;
    }
    public String getFirst_name ()
    {
        return first_name;
    }
    public void setFirst_name (String _first_name)
    {
        first_name=_first_name;
    }
    public String getLast_name ()
    {
        return last_name;
    }
    public void setLast_name (String _last_name)
    {
        last_name=_last_name;
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
