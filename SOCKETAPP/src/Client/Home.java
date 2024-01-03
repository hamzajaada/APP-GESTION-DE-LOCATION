package Client;

import javax.swing.*;

import Utils.Car;
// import Utils.CarDao;
// import Utils.CarDaoImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Home extends JFrame {
  JPanel pane, pane1, pane2, pane3;
  JLabel Titre;
  JButton btnAdd, btnUpdate, btnDelete;
  JTable tableCars;
  JScrollPane scrollPane;

  public Home() {

    pane = new JPanel(new BorderLayout());
    pane1 = new JPanel();
    pane2 = new JPanel();
    pane3 = new JPanel();
    Titre = new JLabel("Gestion des Voitures ");
    btnAdd = new JButton("Ajouter");
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new AjoutVoitureForm();
      }
    });
    btnUpdate = new JButton("Modifier");
    btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new AjoutVoitureForm();
      }
    });
    btnDelete = new JButton("Supprimer");

    try {
      Socket socket = new Socket("localhost", 1234);  // Remplacez "localhost" par l'adresse IP du serveur si nécessaire
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

      // Envoyez une requête au serveur pour récupérer la liste des voitures
      objectOutputStream.writeObject("getCarList");  // "getCarList" pourrait être un signal que le serveur doit renvoyer la liste des voitures
      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      List<Car> cars = (List<Car>) objectInputStream.readObject();

      

      // ... (Le reste du code reste inchangé)
      Object[][] data = new Object[cars.size()][3];
      for (int i = 0; i < cars.size(); i++) {
        data[i][0] = cars.get(i).getModule();
        data[i][1] = cars.get(i).getMatricule();
        data[i][2] = cars.get(i).getPrix();
        System.out.println(data);
      }

      String[] entetes = { "Module", "Matricule", "Prix" };

      tableCars = new JTable(data, entetes);
      scrollPane = new JScrollPane(tableCars);
      
      

      // Fermez les flux et la connexion
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // CarDao carDAO = new CarDaoImpl();
    // List<Car> cars = carDAO.getAllCars();
    

    pane1.add(Titre);
    pane2.add(scrollPane);
    pane3.add(btnAdd);
    pane3.add(btnDelete);
    pane3.add(btnUpdate);
    pane.add(pane1, BorderLayout.NORTH);
    pane.add(pane2, BorderLayout.CENTER);
    pane.add(pane3, BorderLayout.SOUTH);
    pane1.setBackground(Color.orange);
    pane2.setBackground(Color.orange);
    pane3.setBackground(Color.orange);
    getContentPane().add(pane);
    pack();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setVisible(true);

  }

  public static void main(String[] args) {
    new Home();
  }

}
