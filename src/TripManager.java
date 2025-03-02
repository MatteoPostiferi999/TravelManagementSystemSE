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
        TravellerPreferences preferences = traveller.getPreferences(); // Ottieni le preferenze del viaggiatore

        return allTrips.stream()
                .filter(trip -> trip.isSuitableForTraveller(preferences)) // Usa il metodo già definito in Trip
                .collect(Collectors.toList());
    }

    // Prenota un posto in un viaggio
    public void searchAndBookTrip(Traveller traveller) {
        List<Trip> suitableTrips = getSuitableTrips(traveller);

        if (suitableTrips.isEmpty()) {
            System.out.println("Nessun viaggio disponibile in base alle tue preferenze.");
            return;
        }

        // Mostra i viaggi disponibili
        System.out.println("Viaggi disponibili:");
        for (int i = 0; i < suitableTrips.size(); i++) {
            System.out.println((i + 1) + ". " + suitableTrips.get(i).getDestination() +
                    " - Prezzo: " + suitableTrips.get(i).getPrice());
        }

        // L'utente sceglie un viaggio
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il numero del viaggio che vuoi prenotare: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > suitableTrips.size()) {
            System.out.println("Scelta non valida.");
            return;
        }

        Trip selectedTrip = suitableTrips.get(choice - 1);

        // Effettua la prenotazione
        if (selectedTrip.bookSpot()) {
            traveller.addBookedTrip(selectedTrip);
            System.out.println("Prenotazione confermata per il viaggio a " + selectedTrip.getDestination());
        } else {
            System.out.println("Prenotazione fallita: posti esauriti.");
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
}