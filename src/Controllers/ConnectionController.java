/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.User;
import Dao.UserDao;
import Views.UserAdminView;
import Views.UserCandidateView;
import Views.UserVoterView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ppica
 */
public class ConnectionController {
    private final Connection cx;
    private final DaoFactory factory;

    public ConnectionController(){
        cx = getConnection();
        factory = new DaoFactory(cx);
    }
    
    public Connection getConnection()
    {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            // Gérer les éventuelles erreurs ici. 
            System.out.println("Echec de chargement du driver");
            return null;
        }
                // Connexion à la base de données 
        String url = "jdbc:mysql://localhost:3306/base1";
        String utilisateur = "louis";
        String motDePasse = "louis";
        
        try {
            return DriverManager.getConnection( url, utilisateur, motDePasse );
        } catch ( SQLException e ) {
            // Gérer les éventuelles erreurs ici 
            System.out.println("Echec de la connexion:"+e.getMessage());
        }
        return null;
    }
    
    public void closeConnection(Connection cx)
    {
        if ( cx != null )
        {
            try {
                // Fermeture de la connexion 
                cx.close();
            } catch ( SQLException ignore ) {
                // Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
                System.out.println("Echec fermeture de la connexion:"+ignore.getMessage());
            }
        }
    }
        
    public void connect(String email, String password){
        UserDao dao=factory.getUserDao();
        User u = dao.find(email, password);
        if (u != null)
        {
            switch (u.getRole())
            {
                case User.ADMIN:
                    new UserAdminView(u, cx).setVisible(true);
                    break;
                case User.VOTANT:
                    new UserVoterView(u, cx).setVisible(true);
                    break;
                case User.CANDIDAT:
                    new UserCandidateView(u, cx).setVisible(true);
                    break;
            }
        }
        
    }
}
