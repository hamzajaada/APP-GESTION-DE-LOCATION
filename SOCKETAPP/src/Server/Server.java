package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import Utils.Car;
import Utils.CarDao;
import Utils.CarDaoImpl;
import Utils.User;
import Utils.UserDAO;
import Utils.UserDAOImlp;

public class Server {
  public static void main(String[] args) throws ClassNotFoundException {
    try {
      ServerSocket serverSocket = new ServerSocket(1234);
      System.out.println("Server waiting for client...");
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        // User receivedUser;
        // receivedUser = (User) objectInputStream.readObject();
        UserDAO userDAO = new UserDAOImlp();
        CarDao carDAO = new CarDaoImpl();
        Object receivedObject = objectInputStream.readObject(); // Méthode à implémenter
        if (receivedObject instanceof User) {
          userDAO.handleUserRequest((User) receivedObject);
        } else if (receivedObject instanceof Car) {
          carDAO.handleCarRequest((Car) receivedObject);
        } else if ("getCarList".equals(receivedObject)) {
          CarDao carDao = new CarDaoImpl();
          List<Car> cars = carDao.getAllCars();
          System.out.println(cars);
          objectOutputStream.writeObject(cars);
        }
        // UserDAO userDao = new UserDAOImlp();
        // userDao.Ajouter(receivedUser);
        // objectInputStream.close();
        // clientSocket.close();
        // User receivedUser = (User) objectInputStream.readObject();
        // String action = receivedUser.getAction();
        // UserDAO userDao = new UserDAOImlp();

        // switch (action) {
        // case "register":
        // userDao.Ajouter(receivedUser);
        // System.out.println("User registered");
        // break;
        // case "login":
        // boolean loginSuccess = userDao.login(receivedUser.getUsername(),
        // receivedUser.getPassword());
        // System.out.println("Login: " + (loginSuccess ? "Success" : "Failed"));
        // break;
        // default:
        // System.out.println("Unknown action");
        // break;
        // }
        // userDao.handleUserRequest(receivedUser);

      }
    } catch (IOException e) {

      e.printStackTrace();
    }

  }

}
