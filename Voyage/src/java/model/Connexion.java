package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 

public class Connexion {
    public Connection GetConnection() throws Exception {
        Connection connexion = null;
        try {
            if (connexion == null) {
                Class.forName("org.postgresql.Driver");
                connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voyage", "postgres", "root");
            } else {
                throw new Exception("La connexion à la base de données a échoué.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
        return connexion;
    }
}
