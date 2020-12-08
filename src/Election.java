/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *@author louis
 */
public class Election {
    private int id;
    String state;
    String date;
    int etat;/// 
    static final int BEFORE_ELECTION=1;
    static final int ELECTION_DAY=2;
    static final int AFTER_ELECTION=3;
public int getId ()
    {
        return id;
    }
    public void setId (int _id)
    {
        id=_id;
    }    
     public String getState ()
    {
        return state;
    }
    public void setState (String _state)
    {
        state=_state;
    }
    public String getDate ()
    {
        return date;
    }
    public void setDate (String _date)
    {
        date=_date;
    }
    public int getEtat ()
    {
        return etat;
    
    }
    public void setEtat(int _etat)
    {
        etat=_etat;
    
    }
}
