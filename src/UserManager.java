import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(User user) {
        if (user != null) {
            users.put(user.getEmail(), user);
            System.out.println("User registered successfully: " + user.getName());
        } else {
            System.out.println("Registration failed.");
        }
    }

    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user instanceof Traveller) { // Puoi aggiungere controlli su password
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Invalid email or password.");
            return null;
        }
    }

}
