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
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                // User receivedUser;
                // receivedUser = (User) objectInputStream.readObject();
               
                // UserDAO userDao = new UserDAOImlp();
                // userDao.Ajouter(receivedUser);
                // objectInputStream.close();
                // clientSocket.close();
                User receivedUser = (User) objectInputStream.readObject();
                String action = receivedUser.getAction();
                
                UserDAO userDao = new UserDAOImlp();
                
                // switch (action) {
                //     case "register":
                //         userDao.Ajouter(receivedUser);
                //         System.out.println("User registered");
                //         break;
                //     case "login":
                //         boolean loginSuccess = userDao.login(receivedUser.getUsername(), receivedUser.getPassword());
                //         System.out.println("Login: " + (loginSuccess ? "Success" : "Failed"));
                //         break;
                //     default:
                //         System.out.println("Unknown action");
                //         break;
                // }
                userDao.handleUserRequest(receivedUser);
                
            }
        } catch (IOException e) {
           
            e.printStackTrace();
        }
         
    }
   
}
