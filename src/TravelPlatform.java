import java.util.Scanner;


public class TravelPlatform {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Pulisce il buffer

            switch (choice) {
                case 1: // Registrazione
                    User newUser = Registration.registerUser();
                    userManager.registerUser(newUser);
                    break;

                case 2: // Login
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    userManager.loginUser(email, password);
                    break;

                case 3: // Logout
                    userManager.logoutUser();
                    break;

                case 4: // Esci
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        scanner.close();
    }
}

