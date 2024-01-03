package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierCars extends JFrame {
    private JPanel mainPanel, inputPanel, buttonPanel;
    private JLabel lblModule, lblMatricule, lblPrix;
    private JTextField txtModule, txtMatricule, txtPrix;
    private JButton btnAjouter;

    public ModifierCars() {
        mainPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel = new JPanel();

        lblModule = new JLabel("Module:");
        lblMatricule = new JLabel("Matricule:");
        lblPrix = new JLabel("Prix:");

        txtModule = new JTextField();
        txtMatricule = new JTextField();
        txtPrix = new JTextField();

        btnAjouter = new JButton("Modifier");
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajoutez ici le code pour traiter l'ajout de la voiture
                String module = txtModule.getText();
                String matricule = txtMatricule.getText();
                double prix = Double.parseDouble(txtPrix.getText());

                // Ajoutez ici le code pour traiter les données (par exemple, stockage dans une base de données)
                // ...

                // Affichez un message pour indiquer que l'ajout a été effectué
                JOptionPane.showMessageDialog(ModifierCars.this, "Voiture Modifer avec succès!");

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
        setTitle("Formulaire de modification de voiture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    private void clearFields() {
        txtModule.setText("");
        txtMatricule.setText("");
        txtPrix.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModifierCars());
    }
}
