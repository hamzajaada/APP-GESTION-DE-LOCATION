package Client;

import javax.swing.*;

import Utils.Car;
import Utils.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AjoutVoitureForm extends JFrame {
    private JPanel mainPanel, inputPanel, buttonPanel;
    private JLabel lblModule, lblMatricule, lblPrix;
    private JTextField txtModule, txtMatricule, txtPrix;
    private JButton btnAjouter;

    public AjoutVoitureForm() {
        mainPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel = new JPanel();

        lblModule = new JLabel("Module:");
        lblMatricule = new JLabel("Matricule:");
        lblPrix = new JLabel("Prix:");

        txtModule = new JTextField();
        txtMatricule = new JTextField();
        txtPrix = new JTextField();

        btnAjouter = new JButton("Ajouter");
        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Socket s = new Socket("localhost",1234);
                    ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                    // Ajoutez ici le code pour traiter l'ajout de la voiture
                    String module = txtModule.getText();
                    String matricule = txtMatricule.getText();
                    double prix = Double.parseDouble(txtPrix.getText());
                    Car car = new Car();
                    car.setModule(module);
                    car.setMatricule(matricule);
                    car.setPrix(prix);
                    car.setAction("AjouterCar");
                    os.writeObject(car);
                    os.flush();
                    os.close();
                    s.close();
                    dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(AjoutVoitureForm.this, "Voiture ajoutée avec succès!");
                // Effacez les champs après l'ajout
                clearFields();
            }
        });

        // Ajoutez les composants au panneau d'entrée
        inputPanel.add(lblModule);
        inputPanel.add(txtModule);
        inputPanel.add(lblMatricule);
        inputPanel.add(txtMatricule);
        inputPanel.add(lblPrix);
        inputPanel.add(txtPrix);

        // Ajoutez le bouton au panneau de boutons
        buttonPanel.add(btnAjouter);

        // Ajoutez les panneaux principaux au panneau principal
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Configurations de la fenêtre principale
        setTitle("Formulaire d'ajout de voiture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400,200);
        setVisible(true);
    }

    private void clearFields() {
        txtModule.setText("");
        txtMatricule.setText("");
        txtPrix.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AjoutVoitureForm());
    }
}
