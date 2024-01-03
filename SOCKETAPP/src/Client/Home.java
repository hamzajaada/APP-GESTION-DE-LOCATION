package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    JPanel pane,pane1,pane2,pane3;
    JLabel Titre;
    JButton btnAdd ,btnUpdate, btnDelete;
    JTable tableCars;
    JScrollPane scrollPane;
    public Home(){

        pane = new JPanel(new BorderLayout());
        pane1 = new JPanel();
        pane2 = new JPanel();
        pane3 = new JPanel();
        Titre = new JLabel("Gestion des Voitures ");
        btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 new AjoutVoitureForm();
            }
        });
        btnUpdate = new JButton("Modifier");
        btnUpdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 new AjoutVoitureForm();
            }
        });
        btnDelete = new JButton("Supprimer");
        Object[][] donnees = {
            {"Dacia", "12309-A-32", 250},
            {"Dacia", "12309-A-32", 250},
            {"Dacia", "12309-A-32", 250}
       };
        String[] entetes = {"Module", "Matricule", "Prix"};
    
        tableCars = new JTable(donnees, entetes);
        scrollPane = new JScrollPane(tableCars);
        pane1.add(Titre);
        pane2.add(scrollPane);
        pane3.add(btnAdd);
        pane3.add(btnDelete);
        pane3.add(btnUpdate);
        pane.add(pane1,BorderLayout.NORTH);
        pane.add(pane2,BorderLayout.CENTER);
        pane.add(pane3,BorderLayout.SOUTH);
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
