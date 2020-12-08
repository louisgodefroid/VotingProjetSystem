
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class UserController {
  
    DaoFactory factory;
    public UserController(Connection cx)
    {
      factory=new DaoFactory(cx);
    }
    
    private boolean create (String first_name,String last_name,String email,String password,int role)
    {
        User u=new User();
        u.setFirst_name(first_name);
        u.setLast_name(last_name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);
        UserDao dao=factory.getUserDao();
        return dao.create(u);
        
    }
    public boolean createAdmin (String first_name,String last_name,String email,String password)
    {
        return create(first_name,last_name,email,password,User.ADMIN);
    }
   public boolean createCandidate (String first_name,String last_name,String email,String password)
    {
        return create(first_name,last_name,email,password,User.CANDIDAT);
    }
   public boolean createVotant (String first_name,String last_name,String email,String password)
    {
        return create(first_name,last_name,email,password,User.VOTANT);
    }
   public boolean delete (String first_name,String last_name)
   {
        User u=new User();
        u.setFirst_name(first_name);
        u.setLast_name(last_name);
        UserDao dao=factory.getUserDao();
        return dao.delete(u);
   }
   
}
