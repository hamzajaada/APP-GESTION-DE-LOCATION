package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance = null;
    private static String DB_NAME ="socketapp";
    private static int PORT =3306;
    private static String USER_NAME = "root";
    private static String Password = "";
    private static String HOST = "127.0.0.1";
    private Connection connection;
    
    
    //Constructeur prive pour empêcher l'instanciation directe depuis l'extérieur:
    private DbConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection= DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST ,PORT ,DB_NAME), USER_NAME, Password);
        }catch(ClassNotFoundException |SQLException e ) {
            e.printStackTrace();
        }
    }
//Méthode pour obtenir l'instance unique
    public static DbConnection getInstance(){
        if(instance == null){
            instance =new DbConnection();
        }
        return instance;
    }
    //Méthode pour obtenir la connexion à la base de données
    public Connection getConnection(){
        return this.connection;
    }
}
