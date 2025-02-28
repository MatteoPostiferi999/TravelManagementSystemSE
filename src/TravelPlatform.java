import java.util.Scanner;

public class TravelPlatform {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Update Details\n5. Delete Account\n6. Exit");
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

                case 4: // Modifica Dati
                    if (userManager.getLoggedInUser() != null) {
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        userManager.updateUserDetails(newEmail, newPassword);
                    } else {
                        System.out.println("You must be logged in to update your details.");
                    }
                    break;

                case 5: // Cancella Account
                    if (userManager.getLoggedInUser() != null) {
                        System.out.print("Are you sure you want to delete your account? (yes/no): ");
                        String confirm = scanner.nextLine();
                        if (confirm.equalsIgnoreCase("yes")) {
                            userManager.deleteUserAccount();
                        } else {
                            System.out.println("Account deletion cancelled.");
                        }
                    } else {
                        System.out.println("You must be logged in to delete your account.");
                    }
                    break;

                case 6: // Esci
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