import java.util.Scanner;

class Registration {
    public static User registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        System.out.println("Are you a Traveler or a Guide? (T/G)");
        String role = scanner.nextLine().toUpperCase();

        boolean hasLicense = false;
        if (role.equals("G")) {
            System.out.println("Do you have a guide license? (true/false)");
            hasLicense = Boolean.parseBoolean(scanner.nextLine());
        }

        try {
            return UserFactory.createUser(name, email, password, role, hasLicense);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

