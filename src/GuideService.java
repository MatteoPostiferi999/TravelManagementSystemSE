import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GuideService {
    private TripService tripService;

    public GuideService(TripService tripService) {
        this.tripService = tripService;
    }

    public void submitApplicationForTrip(Guide guide) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Filtra i viaggi per i quali la guida ha le competenze richieste
            List<Trip> suitableTrips = tripService.getAllTrips().stream()
                    .filter(trip -> trip.getRequiredGuideSkills().stream().allMatch(skill -> guide.getSkills().contains(skill)) && guide.hasLicense())
                    .collect(Collectors.toList());

            if (suitableTrips.isEmpty()) {
                System.out.println("❌ Nessun viaggio disponibile per le tue competenze.");
                return;
            }

            // Mostra i viaggi disponibili
            System.out.println("\n🌍 **Viaggi disponibili per te:**");
            for (int i = 0; i < suitableTrips.size(); i++) {
                Trip trip = suitableTrips.get(i);
                System.out.println((i + 1) + ". " + trip.getDestination() + " | Tipo: " + trip.getTripType());
            }
            System.out.println("0. 🔙 Torna indietro");

            System.out.print("\n📌 Inserisci il numero del viaggio per inviare la candidatura o 0 per tornare indietro: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > suitableTrips.size()) {
                System.out.println("❌ Scelta non valida, riprova.");
                continue;
            }

            // Seleziona il viaggio
            Trip selectedTrip = suitableTrips.get(choice - 1);

            // Crea la candidatura per la guida
            GuideApplication application = GuideApplication.submitApplication(guide, selectedTrip);
            if (application != null) {
                System.out.println("🎉 Candidatura inviata per il viaggio a " + selectedTrip.getDestination() + "!");
            }
        }
    }
}