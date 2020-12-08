/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class Vote {
    private int id;
    Voter voter;
    Election election;
    Candidate candidate;
    public int getId ()
    {
        return id;
    }
    public void setId (int _id)
    {
        id=_id;
    }
    public Voter getVoter ()
    {
        return voter;
    }
    public void setVoter (Voter _voter)
    {
        voter=_voter;
    }
    public Election getElection ()
    {
        return election;
    }
    public void setElection (Election _election)
    {
        election=_election;
    }
    public Candidate getCandidate ()
    {
        return candidate;
    }
    public void setCandidate (Candidate _candidate)
    {
        candidate=_candidate;
    }
    
}
