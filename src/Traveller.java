import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Traveller implements User {
    private String id;
    private String name;
    private String email;
    private String password;
    private TravellerPreferences preferences; // Ora la preferenza è un oggetto separato
    private List<Trip> bookedTrips;

    public Traveller(String id, String name, String email, String password, TravellerPreferences preferences) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.bookedTrips = new ArrayList<>();
    }

    // Metodi getter per le preferenze
    public TravellerPreferences getPreferences() {
        return preferences;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getRole() {
        return "TRAVELER";
    }

    @Override
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    // Metodo per aggiungere un viaggio prenotato
    public void addBookedTrip(Trip trip) {
        this.bookedTrips.add(trip);
    }

    public void removeBookedTrip(Trip trip) {
        this.bookedTrips.remove(trip);
    }

    public List<Trip> getBookedTrips() {
        return bookedTrips;
    }

    public String getPassword() {
        return password;
    }

    public PaymentDetails providePaymentDetails() {
        // Implementa la logica per ottenere i dettagli di pagamento, ad esempio tramite input dell'utente
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il numero della carta di credito: ");
        String cardNumber = scanner.next();
        System.out.print("Inserisci la data di scadenza (MM/AA): ");
        String expirationDate = scanner.next();
        System.out.print("Inserisci il CVV: ");
        String cvv = scanner.next();

        return new PaymentDetails(cardNumber, expirationDate, cvv);
    }
}