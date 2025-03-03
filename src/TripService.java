import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TripService {
    private List<Trip> allTrips;

    public TripService() {
        this.allTrips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        allTrips.add(trip);
    }

    public void removeTrip(Trip trip) {
        allTrips.remove(trip);
    }

    public List<Trip> getAllTrips() {
        return new ArrayList<>(allTrips);
    }

    public List<Trip> getSuitableTrips(Traveller traveller) {
        return allTrips.stream()
                .filter(trip -> trip.isSuitableForTraveller(traveller.getPreferences()))
                .collect(Collectors.toList());
    }
}
