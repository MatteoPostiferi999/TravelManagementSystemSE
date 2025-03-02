import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TripManager {
    private List<Trip> allTrips; // Lista di tutti i viaggi disponibili

    public TripManager() {
        this.allTrips = new ArrayList<>();
    }

    // Aggiunge un viaggio alla lista
    public void addTrip(Trip trip) {
        allTrips.add(trip);
    }

    // Rimuove un viaggio dalla lista
    public void removeTrip(Trip trip) {
        allTrips.remove(trip);
    }

    // Trova i viaggi che corrispondono alle preferenze di un viaggiatore
    public List<Trip> getSuitableTrips(Traveller traveller) {
        return allTrips.stream()
                .filter(trip -> trip.isSuitableForTraveller(traveller.getPreferences()))
                .collect(Collectors.toList());
    }

    public void searchAndBookTrip(Traveller traveller) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Trip> suitableTrips = getSuitableTrips(traveller);

            if (suitableTrips.isEmpty()) {
                System.out.println("❌ Nessun viaggio disponibile in base alle tue preferenze.");
                return;
            }

            // Mostra i viaggi disponibili con più dettagli
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

            // L'utente sceglie un viaggio
            System.out.print("\n📌 Inserisci il numero del viaggio per vedere i dettagli o 0 per tornare indietro: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("🔙 Ritorno al menu principale...");
                return;
            }

            if (choice < 1 || choice > suitableTrips.size()) {
                System.out.println("❌ Scelta non valida, riprova.");
                continue;
            }

            Trip selectedTrip = suitableTrips.get(choice - 1);

            // Mostra dettagli del viaggio selezionato
            System.out.println("\n📋 **Dettagli del viaggio a " + selectedTrip.getDestination() + "**");
            System.out.println("🔹 Prezzo: " + selectedTrip.getPrice() + "€");
            System.out.println("🔹 Durata: " + selectedTrip.getDurationDays() + " giorni");
            System.out.println("🔹 Tipo di viaggio: " + selectedTrip.getTripType());
            System.out.println("🔹 Età minima: " + selectedTrip.getMinAge());
            System.out.println("🔹 Età massima: " + selectedTrip.getMaxAge());
            System.out.println("🔹 Budget minimo: " + selectedTrip.getMinBudget() + "€");
            System.out.println("🔹 Budget massimo: " + selectedTrip.getMaxBudget() + "€");
            System.out.println("🔹 Posti disponibili: " + selectedTrip.getAvailableSpots());
            System.out.println("🔹 Attività incluse: ");
            for (Activity activity : selectedTrip.getActivities()) {
                System.out.println("   - " + activity.getName());
            }

            // Chiedi conferma per la prenotazione
            System.out.print("\n✅ Vuoi prenotare questo viaggio? (s/n): ");
            char confirm = scanner.next().toLowerCase().charAt(0);

            if (confirm == 's') {
                if (selectedTrip.bookSpot()) {
                    // Crea l'oggetto Booking
                    PaymentDetails paymentDetails = traveller.providePaymentDetails(); // Metodo per ottenere i dettagli di pagamento
                    Booking booking = new Booking(selectedTrip, paymentDetails);

                    // Processa il pagamento
                    PaymentProcessor paymentProcessor = new PaymentProcessor();
                    boolean paymentSuccess = paymentProcessor.processPayment(booking);

                    if (paymentSuccess) {
                        traveller.addBookedTrip(selectedTrip);
                        System.out.println("🎉 Prenotazione confermata per il viaggio a " + selectedTrip.getDestination() + "!");
                    } else {
                        System.out.println("❌ Il pagamento è fallito. Prenotazione non completata.");
                    }
                } else {
                    System.out.println("❌ Prenotazione fallita: posti esauriti.");
                }
            } else {
                System.out.println("🚫 Prenotazione annullata. Ritorno al menu...");
            }
        }
    }

    // Cancella una prenotazione
    public boolean cancelBooking(Traveller traveller, Trip trip) {
        if (trip.cancelBooking()) {
            traveller.removeBookedTrip(trip);
            return true;
        }
        return false;
    }

    // Aggiunge un'attività a un viaggio
    public void addActivityToTrip(Trip trip, Activity activity) {
        trip.addActivity(activity);
    }

    // Rimuove un'attività da un viaggio
    public void removeActivityFromTrip(Trip trip, Activity activity) {
        trip.removeActivity(activity);
    }

    // Assegna una guida a un viaggio
    public void assignGuideToTrip(Trip trip, Guide guide) {
        trip.assignGuide(guide);
    }

    // Ottiene la lista di tutti i viaggi
    public List<Trip> getAllTrips() {
        return new ArrayList<>(allTrips);
    }


    public List<Trip> getSuitableTripsForGuide(Guide guide) {
        return allTrips.stream()
                .filter(trip -> trip.getRequiredGuideSkills().stream().allMatch(skill -> guide.getSkills().contains(skill)))
                .collect(Collectors.toList());
    }

    // Metodo per far scegliere un viaggio da parte di una guida
    public void assignGuideToTrip(Guide guide) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Trip> suitableTrips = getSuitableTripsForGuide(guide);

            if (suitableTrips.isEmpty()) {
                System.out.println("❌ Nessun viaggio disponibile per le tue competenze.");
                return;
            }

            // Mostra i viaggi disponibili con i requisiti per la guida
            System.out.println("\n🌍 **Viaggi disponibili per te (con requisiti per la guida):**");
            for (int i = 0; i < suitableTrips.size(); i++) {
                Trip trip = suitableTrips.get(i);
                System.out.println((i + 1) + ". " + trip.getDestination() +
                        " | Tipo: " + trip.getTripType() +
                        " | Durata: " + trip.getDurationDays() + " giorni" +
                        " | Posti disponibili: " + trip.getAvailableSpots());

                // Mostra i requisiti specifici per il viaggio
                System.out.println("  ➡️ Requisiti guida: " + trip.getRequiredGuideSkills());
            }
            System.out.println("0. 🔙 Torna indietro");

            // L'utente sceglie un viaggio
            System.out.print("\n📌 Inserisci il numero del viaggio per vederne i dettagli o 0 per tornare indietro: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("🔙 Ritorno al menu principale...");
                return;
            }

            if (choice < 1 || choice > suitableTrips.size()) {
                System.out.println("❌ Scelta non valida, riprova.");
                continue;
            }

            Trip selectedTrip = suitableTrips.get(choice - 1);

            // Mostra dettagli del viaggio selezionato, concentrandosi sui requisiti della guida
            System.out.println("\n📋 **Dettagli del viaggio a " + selectedTrip.getDestination() + "**");
            System.out.println("🔹 Tipo di viaggio: " + selectedTrip.getTripType());
            System.out.println("🔹 Durata: " + selectedTrip.getDurationDays() + " giorni");
            System.out.println("🔹 Età minima richiesta: " + selectedTrip.getMinAge());
            System.out.println("🔹 Età massima richiesta: " + selectedTrip.getMaxAge());
            System.out.println("🔹 Budget minimo richiesto: " + selectedTrip.getMinBudget() + "€");
            System.out.println("🔹 Budget massimo richiesto: " + selectedTrip.getMaxBudget() + "€");
            System.out.println("🔹 Requisiti guida: ");
            for (Enums.Skill skill : selectedTrip.getRequiredGuideSkills()) {
                System.out.println("   - " + skill);
            }

            // Chiedi conferma per l'assegnazione della guida
            System.out.print("\n✅ Vuoi essere assegnato a questo viaggio? (s/n): ");
            char confirm = scanner.next().toLowerCase().charAt(0);

            if (confirm == 's') {
                selectedTrip.assignGuide(guide);
                guide.addAssignedTrip(selectedTrip);
                System.out.println("🎉 Guida assegnata al viaggio a " + selectedTrip.getDestination() + "!");
            } else {
                System.out.println("🚫 Assegnazione annullata. Ritorno al menu...");
            }
        }
    }
}
