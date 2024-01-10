
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

                // Créer une nouvelle instance de ClientHandler pour gérer la connexion client
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            // UserDAO et CarDao à utiliser dans ce thread
            UserDAO userDAO = new UserDAOImlp();
            CarDao carDAO = new CarDaoImpl();

            Object receivedObject = objectInputStream.readObject();

            if (receivedObject instanceof User) {
                userDAO.handleUserRequest((User) receivedObject);
            } else if (receivedObject instanceof Car) {
                carDAO.handleCarRequest((Car) receivedObject);
            } else if ("getCarList".equals(receivedObject)) {
                List<Car> cars = carDAO.getAllCars();
                System.out.println(cars);
                objectOutputStream.writeObject(cars);
            }

            // Fermez les flux et la connexion client
            objectInputStream.close();
            objectOutputStream.close();
            clientSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
