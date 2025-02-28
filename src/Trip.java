import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String id;
    private String destination;
    private List<Activity> activities;
    private int availableSpots;
    private double price;
    private Guide assignedGuide;
    private List<Enums.Skill> requiredGuideSkills;
    private Enums.TripType tripType; // Nuovo tipo di viaggio
    private int minAge;          // Età minima
    private int maxAge;          // Età massima
    private double minBudget;    // Budget minimo
    private double maxBudget;    // Budget massimo
    private int durationDays;    // Durata in giorni

    public Trip(String id, String destination, List<Activity> activities, int availableSpots, double price,
                List<Enums.Skill> requiredGuideSkills, Enums.TripType tripType,
                int minAge, int maxAge, double minBudget, double maxBudget, int durationDays) {
        this.id = id;
        this.destination = destination;
        this.activities = activities;
        this.availableSpots = availableSpots;
        this.price = price;
        this.requiredGuideSkills = requiredGuideSkills;
        this.tripType = tripType;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.durationDays = durationDays;
    }

    public String getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public double getPrice() {
        return price;
    }

    public Guide getAssignedGuide() {
        return assignedGuide;
    }

    public List<Enums.Skill> getRequiredGuideSkills() {
        return requiredGuideSkills;
    }

    public Enums.TripType getTripType() {
        return tripType; // Restituisce il tipo di viaggio
    }

    // Metodo per assegnare una guida al viaggio
    public void assignGuide(Guide guide) {
        this.assignedGuide = guide;
    }

    // Metodo per prenotare un posto
    public boolean bookSpot() {
        if (availableSpots > 0) {
            availableSpots--;
            return true;
        }
        return false;
    }

    // Metodo per annullare una prenotazione
    public boolean cancelBooking() {
        if (availableSpots >= 0) {
            availableSpots++;
            return true;
        }
        return false;
    }

    // Metodo per aggiungere una nuova attività al viaggio
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Metodo per rimuovere un'attività dal viaggio
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public boolean isSuitableForTraveller(TravellerPreferences preferences) {
        boolean ageMatch = (preferences.getAge() >= minAge && preferences.getAge() <= maxAge);
        boolean budgetMatch = (preferences.getBudget() >= minBudget && preferences.getBudget() <= maxBudget);
        boolean durationMatch = (preferences.getDuration() <= durationDays);

        // Verifica se tutte le preferenze del viaggiatore combaciano con il tipo di viaggio
        boolean preferencesMatch = preferences.getPreferences().stream().allMatch(preference ->
                (preference == Enums.Preference.ADVENTURE && tripType == Enums.TripType.ADVENTURE_TRIP) ||
                        (preference == Enums.Preference.RELAX && (tripType == Enums.TripType.LUXURY_TRIP || tripType == Enums.TripType.FAMILY_FRIENDLY)) ||
                        (preference == Enums.Preference.CULTURE && tripType == Enums.TripType.CULTURAL)
        );

        return ageMatch && budgetMatch && durationMatch && preferencesMatch;
    }
}