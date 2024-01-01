package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

                // Obtention des flux d'entrée
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Lecture de l'objet User
                User receivedUser;
                receivedUser = (User) objectInputStream.readObject();
                System.out.println(receivedUser.getEmail());
                System.out.println(receivedUser.getUsername());
                System.out.println(receivedUser.getPassword());
                System.out.println(receivedUser.getVille());
                 UserDAO userDao = new UserDAOImlp();
                 userDao.Ajouter(receivedUser);
                // Fermeture des flux et de la connexion
                objectInputStream.close();
                clientSocket.close();
                
            }
        } catch (IOException e) {
           
            e.printStackTrace();
        }
         
    }
}
