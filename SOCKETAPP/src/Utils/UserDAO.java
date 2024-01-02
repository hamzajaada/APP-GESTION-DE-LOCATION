package Utils;

public interface UserDAO {
    public void handleUserRequest(User user);
    public void Ajouter(User user);
    public boolean login(String username, String password);
}
