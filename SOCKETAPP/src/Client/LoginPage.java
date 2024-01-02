package Client;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Utils.User;


public class LoginPage extends JFrame {
      JLabel LblUsername;
    JLabel LblPassword;
    JTextField UsernameTxt;
    JPasswordField PasswordTxt;
    JButton BtnLogin;
    JPanel Pane1;
    JPanel Pane2;
    JPanel Pane;
    JLabel Titre;

    public LoginPage() {
        LblUsername = new JLabel("Username:");
        LblPassword = new JLabel("Password");
        UsernameTxt = new JTextField(20);
        PasswordTxt = new JPasswordField(40);
        BtnLogin = new JButton("Login");
        
        BtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket s = new Socket("localhost",1234);
                    ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                    String username = UsernameTxt.getText();
                    char[] passwordChars = PasswordTxt.getPassword();
                    String password = new String(passwordChars);
                    User us = new User();
                    us.setUsername(username);
                    us.setPassword(password);
                    us.setAction("login");
                    os.writeObject(us);
                    os.flush();
                    os.close();
                    s.close();
                    dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
            }
        });

        Pane1 = new JPanel(new GridLayout(2, 2));
        Pane1.add(LblUsername);
        Pane1.add(UsernameTxt);
        Pane1.add(LblPassword);
        Pane1.add(PasswordTxt);
        Pane1.setBackground(Color.ORANGE);

        Pane2 = new JPanel();
        Pane2.add(BtnLogin);
        Pane2.setBorder(new EmptyBorder(50, 50, 50, 50));

        Pane = new JPanel(new GridLayout(3, 0));
        Titre = new JLabel("LOGIN | GESTION DE LOCATION");
        Pane.add(Titre);
        Pane.add(Pane1);
        Pane.add(Pane2);
        int topMargin = 50;
        Pane.setBorder(new EmptyBorder(topMargin, 50, 50, 50));

        getContentPane().add(Pane);
        setVisible(true);
        setSize(500, 400);
        setBackground(Color.CYAN);
    }

    public static void main(String[] args) {
       new LoginPage();
    }
}
