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
    
    int getId ()
    {
        return id;
    }
    void setId (int _id)
    {
        id=_id;
    }
    String getFirst_name ()
    {
        return first_name;
    }
    void setFirst_name (String name)
    {
        first_name=name;
    }
    String getLast_name ()
    {
        return last_name;
    }
    void setLast_name (String name)
    {
        last_name=name;
    }
    String getEmail()
    {
        return email;
    }
    void setEmail (String _email)
    {
       email=_email;
    }
    String getPassword()
    {
        return password;
    }
    void setPassword (String _password)
    {
        password=_password;
    }
    int getRole()
    {
        return role;
    }
    void setRole (int _role)
    {
        role=_role;
    }
    
    
}

