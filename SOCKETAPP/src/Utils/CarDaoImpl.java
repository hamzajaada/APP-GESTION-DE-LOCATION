package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Client.Home;
import Client.LoginPage;

public class CarDaoImpl implements CarDao {

    @Override
    public void handleCarRequest(Car car) {
       switch (car.getAction()) {
            case "AjouterCar":
                // Traitement de l'ajout d'utilisateur
                AjouterCar(car);
                System.out.println("La voiture enregistré avec succès !");
                break;
            // Ajoutez d'autres cas selon vos besoins
            default:
                System.out.println("Action non reconnue.");
        }
    }

    @Override
    public void AjouterCar(Car car) {
        DbConnection dbconnection = DbConnection.getInstance();
        Connection con =dbconnection.getConnection();
        if(con != null ){
            System.out.println("connected!!!");
        }
        else{
            System.out.println("erreru de connection");
        }
        String req = "insert into Car(Module,Matricule,Prix) VALUES(?,?,?)";
        try(PreparedStatement stm = con.prepareStatement(req)) {
                stm.setString(1, car.getModule());
                stm.setString(2, car.getMatricule());
                stm.setDouble(3, car.getPrix());
                stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateCar(Car car) {
      
    }

    @Override
    public void DeleteCar(Car car) {
       
    }
    
}
