package Client;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Utils.User;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class Register extends JFrame {
   JLabel LblEmail;
   JLabel LblPassword;
   JLabel LblUsername;
   JLabel LblVille;
   JTextField EmailTxt;
   JTextField PasswordTxt;
   JTextField UsernameTxt;
   JTextField VilleTxt;
   JButton BtnCreer ;
   JButton BtnLogin;
   JPanel Pane1 ;
   JPanel Pane0 ;
   JLabel Titre;
   JPanel Pane2 ;
   JPanel Pane ;
   public Register(){
    LblUsername = new JLabel("Username:");
    LblEmail = new JLabel("Email  :");
    LblPassword =new JLabel("Password");
    LblVille =new JLabel("Ville");
    UsernameTxt = new JTextField(20);
    EmailTxt = new JTextField(20);
    PasswordTxt = new JTextField(40);
    VilleTxt = new JTextField(40);
    BtnCreer = new JButton("Creer Un compte");
    BtnCreer.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            try {
            Socket s = new Socket("localhost",1234);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            String Username = UsernameTxt.getText();
            String Email = EmailTxt.getText();
            String Password = PasswordTxt.getText();
            String Vile = VilleTxt.getText();
            User user = new User();
            user.setUsername(Username);
            user.setEmail(Email);
            user.setPassword(Password);
            user.setVille(Vile);
            user.setAction("register");
            os.writeObject(user);
            os.flush();
            os.close();
            s.close();
            UsernameTxt.setText(" ");
            EmailTxt.setText(" ");
            PasswordTxt.setText(" ");
            VilleTxt.setText(" ");
            // Registration successful, close the registration window
            dispose();
            // Open the login window
            LoginPage log = new LoginPage();
            log.setVisible(true);

        } catch (IOException e1) { 
            e1.printStackTrace();
        }

        };
    });
    BtnLogin = new JButton("Login");
    BtnLogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPage log = new LoginPage();
            log.setVisible(true);
        }
    });
    Pane1 = new JPanel(new GridLayout(4,4));
    Pane1.add(LblUsername);
    Pane1.add(UsernameTxt);
    Pane1.add(LblEmail);
    Pane1.add(EmailTxt);
    Pane1.add(LblPassword);
    Pane1.add(PasswordTxt);
    Pane1.add(LblVille);
    Pane1.add(VilleTxt);
    Pane1.setBackground(Color.ORANGE);
    Pane2 = new JPanel();
    Pane2.add(BtnCreer);
    Pane2.add(BtnLogin);
     Pane2.setBorder(new EmptyBorder(50, 50, 50, 50));
    Pane0 = new JPanel();
    Titre= new JLabel("REGISTER | GESTION DE LOCATION");
    Pane0.add(Titre);
    Pane = new JPanel(new GridLayout(3,0));
    Pane.add(Pane0);
    Pane.add(Pane1);
    Pane.add(Pane2);
    int topMargin = 50;
    Pane.setBorder(new EmptyBorder(topMargin, 50, 50, 50));
    getContentPane().add(Pane);
    setVisible(true);
    setSize(500,400);
    setBackground(Color.GREEN);
    




   }
    
}
