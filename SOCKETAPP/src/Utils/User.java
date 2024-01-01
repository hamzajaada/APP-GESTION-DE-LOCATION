package Utils;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String Email;
    private String Ville ;
    private String password;
    public User(){

    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return Email;
    }
    public String getVille() {
        return Ville;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setVille(String ville) {
        Ville = ville;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}


