package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Client.Home;
import Client.LoginPage;

public class UserDAOImlp implements UserDAO {

    @Override
    public void Ajouter(User user) {
        DbConnection dbconnection = DbConnection.getInstance();
        Connection con =dbconnection.getConnection();
        if(con != null ){
            System.out.println("connected!!!");
        }
        else{
            System.out.println("erreru de connection");
        }
        String req = "insert into utilisateurs(username,email,ville,password) VALUES(?,?,?,?)";
        try(PreparedStatement stm = con.prepareStatement(req)) {
                stm.setString(1, user.getUsername());
                stm.setString(2, user.getEmail());
                stm.setString(3, user.getVille());
                stm.setString(4, user.getPassword());
                stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean login(String username, String password) {
        DbConnection dbconnection = DbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        if (con != null) {
            System.out.println("connected!!!");
        } else {
            System.out.println("erreur de connection");

            return false;
        }

        String req = "SELECT * FROM utilisateurs WHERE username = ? AND password = ?";
        try (PreparedStatement stm = con.prepareStatement(req)) {
            stm.setString(1, username);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next(); // Si le résultat de la requête n'est pas vide, les informations de connexion sont correctes.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void handleUserRequest(User user) {
        switch (user.getAction()) {
            case "register":
                // Traitement de l'ajout d'utilisateur
                Ajouter(user);
                System.out.println("Utilisateur enregistré avec succès !");
                break;
            case "login":
                // Traitement de la connexion
                if (login(user.getUsername(), user.getPassword())) {
                    System.out.println("Connexion réussie !");
                    new Home();
                } else {
                    System.out.println("Échec de la connexion. Vérifiez vos informations.");
                    new LoginPage();
                }
                break;
            // Ajoutez d'autres cas selon vos besoins
            default:
                System.out.println("Action non reconnue.");
        }
    }
}
