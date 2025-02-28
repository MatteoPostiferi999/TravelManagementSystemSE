import java.util.HashMap;
import java.util.Map;


class UserManager {
    private Map<String, User> users = new HashMap<>();
    private User loggedInUser = null; // Tiene traccia dell'utente connesso

    public void registerUser(User user) {
        if (user != null) {
            if (users.containsKey(user.getEmail())) {
                System.out.println("Error: Email already registered.");
            } else {
                users.put(user.getEmail(), user);
                System.out.println("User registered successfully: " + user.getName());
            }
        } else {
            System.out.println("Registration failed.");
        }
    }

    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.checkPassword(password)) {
            loggedInUser = user;
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Invalid email or password.");
            return null;
        }
    }

    public void logoutUser() {
        if (loggedInUser != null) {
            System.out.println("Goodbye, " + loggedInUser.getName() + "!");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}