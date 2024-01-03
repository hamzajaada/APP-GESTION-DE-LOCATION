package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

  @Override
  public List<Car> getAllCars() {
    DbConnection dbconnection = DbConnection.getInstance();
    Connection con = dbconnection.getConnection();
    List<Car> listcar = new ArrayList<Car>();

    if (con != null) {
      System.out.println("connected!!!");
    } else {
      System.out.println("erreru de connection");
    }
    String req = "select * from Car";
    try (PreparedStatement stm = con.prepareStatement(req)) {
      ResultSet rs = stm.executeQuery();
      if (rs.next()) {
        Car c = new Car(rs.getString("Module"), rs.getString("Matricule"), rs.getDouble("Prix"));
        listcar.add(c);
      }

      System.out.println(listcar + "hello from car dao impl");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return listcar;
  }

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
    Connection con = dbconnection.getConnection();
    if (con != null) {
      System.out.println("connected!!!");
    } else {
      System.out.println("erreru de connection");
    }
    String req = "insert into Car(Module,Matricule,Prix) VALUES(?,?,?)";
    try (PreparedStatement stm = con.prepareStatement(req)) {
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
