import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GuideService {
    private TripService tripService;

    public GuideService(TripService tripService) {
        this.tripService = tripService;
    }

    public void assignGuideToTrip(Guide guide) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Trip> suitableTrips = tripService.getAllTrips().stream()
                    .filter(trip -> trip.getRequiredGuideSkills().stream().allMatch(skill -> guide.getSkills().contains(skill)))
                    .collect(Collectors.toList());

            if (suitableTrips.isEmpty()) {
                System.out.println("❌ Nessun viaggio disponibile per le tue competenze.");
                return;
            }

            System.out.println("\n🌍 **Viaggi disponibili per te:**");
            for (int i = 0; i < suitableTrips.size(); i++) {
                Trip trip = suitableTrips.get(i);
                System.out.println((i + 1) + ". " + trip.getDestination() + " | Tipo: " + trip.getTripType());
            }
            System.out.println("0. 🔙 Torna indietro");

            System.out.print("\n📌 Inserisci il numero del viaggio per assegnarti o 0 per tornare indietro: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > suitableTrips.size()) {
                System.out.println("❌ Scelta non valida, riprova.");
                continue;
            }

            Trip selectedTrip = suitableTrips.get(choice - 1);
            selectedTrip.assignGuide(guide);
            guide.addAssignedTrip(selectedTrip);
            System.out.println("🎉 Guida assegnata al viaggio a " + selectedTrip.getDestination() + "!");
        }
    }
}
