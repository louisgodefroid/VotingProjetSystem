/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louis
 */
public class Result {

    private Candidate candidate;
    private Election election;
    private int ballotCount;
   
    public Candidate getCandidate ()
    {
        return candidate;
    }
    public void setCandidate (Candidate _candidate)
    {
        candidate=_candidate;
    }
    public Election getElection ()
    {
        return election;
    }
    public void setElection ( Election _elec)
    {
        election=_elec;
    }
    public int getBallotCount()
    {
        return ballotCount;
    }
    public void setBallotCount (int _ballot)
    {
        ballotCount=_ballot;
    }
}
