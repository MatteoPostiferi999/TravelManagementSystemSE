import java.util.ArrayList;
import java.util.List;
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
    public boolean bookTrip(Traveller traveller, Trip trip) {
        if (trip.bookSpot()) {
            traveller.addBookedTrip(trip);
            return true;
        }
        return false;
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