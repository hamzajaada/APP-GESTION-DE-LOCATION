package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImlp implements UserDAO {

    @Override
    public void Ajouter(User user) {
        DbConnection dbconnection = DbConnection.getInstance();
        Connection con =dbconnection.getConnection();
        if(con != null ){
            System.out.println("connected!!!");
        }
        else{
            System.out.println("erreru de connection");
        }
        String req = "insert into utilisateurs(username,email,ville,password) VALUES(?,?,?,?)";
        try(PreparedStatement stm = con.prepareStatement(req)) {
                stm.setString(1, user.getUsername());
                stm.setString(2, user.getEmail());
                stm.setString(3, user.getVille());
                stm.setString(4, user.getPassword());
                stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
