/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Louis
 */
public class VotingSystemProject {
    static Connection connexion = null;

    /**
     * @param args the command line arguments
     */
    
    /*public static void main(String[] args) {
        // TODO code application logic here
        // Chargement du driver JDBC pour MySQL 
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            // Gérer les éventuelles erreurs ici. 
            System.out.println("Echec de chargement du driver");
            return;
        }
                // Connexion à la base de données 
        String url = "jdbc:mysql://localhost:3306/base1";
        String utilisateur = "louis";
        String motDePasse = "azerty";
        
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            User u= new User();
            u.setFirst_name("pierre");
            u.setLast_name("Macron");
            u.setEmail("pm@yahoo.fr");
            u.setPassword("azerty");
            u.setRole(User.CANDIDAT);
            UserDaoImpl i=new UserDaoImpl(connexion);
            i.create(u);
            

        } catch ( SQLException e ) {
            // Gérer les éventuelles erreurs ici 
            System.out.println("Echec de la connexion:"+e.getMessage());
        } finally {
            if ( connexion != null )
                try {
                    // Fermeture de la connexion 
                    connexion.close();
                } catch ( SQLException ignore ) {
                    // Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
                     System.out.println("Echec fermeture de la connexion:"+ignore.getMessage());
                }
        }
    }
    */
}
