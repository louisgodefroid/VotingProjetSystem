package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *@author louis
 */
public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int role; // admin,voteur,candidat different droit d'accès etc
    
    public static final int CANDIDAT=0;
    public static final int ADMIN=1;
    public static final int VOTANT=2;
    
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
    public void setFirst_name (String name)
    {
        first_name=name;
    }
    public String getLast_name ()
    {
        return last_name;
    }
    public void setLast_name (String name)
    {
        last_name=name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail (String _email)
    {
       email=_email;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword (String _password)
    {
        password=_password;
    }
    public int getRole()
    {
        return role;
    }
    public void setRole (int _role)
    {
        role=_role;
    }
    
    @Override
    public String toString()
    {
        return first_name + " / " + last_name + " / " + email;
    }
}

