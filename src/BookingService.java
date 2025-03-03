import java.util.List;
import java.util.Scanner;

public class BookingService {
    private TripService tripService;
    private PaymentProcessor paymentProcessor;

    public BookingService(TripService tripService, PaymentProcessor paymentProcessor) {
        this.tripService = tripService;
        this.paymentProcessor = paymentProcessor;
    }

    public void searchAndBookTrip(Traveller traveller) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Trip> suitableTrips = tripService.getSuitableTrips(traveller);

            if (suitableTrips.isEmpty()) {
                System.out.println("❌ Nessun viaggio disponibile in base alle tue preferenze.");
                return;
            }

            System.out.println("\n🌍 **Viaggi disponibili:**");
            for (int i = 0; i < suitableTrips.size(); i++) {
                Trip trip = suitableTrips.get(i);
                System.out.println((i + 1) + ". " + trip.getDestination() +
                        " | Prezzo: " + trip.getPrice() + "€" +
                        " | Durata: " + trip.getDurationDays() + " giorni" +
                        " | Tipo: " + trip.getTripType() +
                        " | Posti disponibili: " + trip.getAvailableSpots());
            }
            System.out.println("0. 🔙 Torna indietro");

            System.out.print("\n📌 Inserisci il numero del viaggio per prenotarlo o 0 per tornare indietro: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > suitableTrips.size()) {
                System.out.println("❌ Scelta non valida, riprova.");
                continue;
            }

            Trip selectedTrip = suitableTrips.get(choice - 1);

            // Chiedere il numero di persone per la prenotazione
            System.out.print("📌 Inserisci il numero di persone per cui stai prenotando: ");
            int numberOfPeople = scanner.nextInt();

            if (numberOfPeople <= 0) {
                System.out.println("❌ Il numero di persone deve essere maggiore di 0.");
                continue;
            }

            if (selectedTrip.bookSpots(numberOfPeople)) {
                PaymentDetails paymentDetails = traveller.providePaymentDetails();
                Booking booking = new Booking(selectedTrip, paymentDetails, numberOfPeople);
                boolean paymentSuccess = paymentProcessor.processPayment(booking);

                if (paymentSuccess) {
                    traveller.addBookedTrip(selectedTrip);
                    System.out.println("🎉 Prenotazione confermata per " + numberOfPeople + " persone al viaggio " + selectedTrip.getDestination() + "!");
                } else {
                    System.out.println("❌ Pagamento fallito. Prenotazione non completata.");
                }
            } else {
                System.out.println("❌ Non ci sono abbastanza posti disponibili per " + numberOfPeople + " persone.");
            }
        }
    }
}