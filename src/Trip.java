import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String id;
    private String destination;
    private List<Activity> activities;
    private int availableSpots;
    private double price;
    private List<Guide> assignedGuides;
    private List<GuideApplication> guideApplications;
    private List<Enums.Skill> requiredGuideSkills;
    private Enums.TripType tripType;
    private int minAge;
    private int maxAge;
    private double minBudget;
    private double maxBudget;
    private int durationDays;
    private int requiredGuides;
    private List<Review> reviews;  // Lista di recensioni per le guide


    public Trip(String id, String destination, List<Activity> activities, int availableSpots, double price,
                List<Enums.Skill> requiredGuideSkills, Enums.TripType tripType,
                int minAge, int maxAge, double minBudget, double maxBudget, int durationDays, int requiredGuides) {
        this.id = id;
        this.destination = destination;
        this.activities = (activities != null) ? activities : new ArrayList<>();
        this.availableSpots = availableSpots;
        this.price = price;
        this.requiredGuideSkills = requiredGuideSkills;
        this.tripType = tripType;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.durationDays = durationDays;
        this.requiredGuides = requiredGuides;
        this.assignedGuides = new ArrayList<>();
        this.guideApplications = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getDestination() { return destination; }
    public List<Activity> getActivities() { return activities; }
    public int getAvailableSpots() { return availableSpots; }
    public double getPrice() { return price; }
    public int getDurationDays() { return durationDays; }
    public int getMinAge() { return minAge; }
    public int getMaxAge() { return maxAge; }
    public double getMinBudget() { return minBudget; }
    public double getMaxBudget() { return maxBudget; }
    public List<Guide> getAssignedGuides() { return assignedGuides; }
    public List<GuideApplication> getGuideApplications() { return guideApplications; }
    public List<Enums.Skill> getRequiredGuideSkills() { return requiredGuideSkills; }
    public Enums.TripType getTripType() { return tripType; }

    // Aggiunge una candidatura per una guida
    public void addGuideApplication(GuideApplication application) {
        guideApplications.add(application);
        System.out.println("Candidatura aggiunta per guida: " + application.getGuide().getName());
    }

    // Assegna una guida al viaggio (supporta più guide)
    public void assignGuide(Guide guide) {
        if (assignedGuides.size() < requiredGuides) {
            assignedGuides.add(guide);
            guide.addAssignedTrip(this); // Aggiunge il viaggio alla guida
            guide.notifyApproval(this);
        } else {
            System.out.println("Numero massimo di guide già assegnato per questo viaggio.");
        }
    }


    // Metodo per prenotare più posti
    public boolean bookSpots(int numberOfPeople) {
        if (availableSpots >= numberOfPeople) {
            availableSpots -= numberOfPeople;
            return true;
        }
        return false;
    }

    // Metodo per annullare una prenotazione di più posti
    public boolean cancelBooking(int numberOfPeople) {
        availableSpots += numberOfPeople;
        return true;  // Puoi aggiungere una logica di controllo se necessario
    }

    // Metodo per aggiungere una nuova attività al viaggio
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Metodo per rimuovere un'attività dal viaggio
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // Metodo per verificare se il viaggio è adatto a un viaggiatore
    public boolean isSuitableForTraveller(TravellerPreferences preferences) {
        boolean ageMatch = (preferences.getAge() >= minAge && preferences.getAge() <= maxAge);
        boolean budgetMatch = (preferences.getBudget() >= minBudget && preferences.getBudget() <= maxBudget);
        boolean durationMatch = (preferences.getDuration() <= durationDays);

        boolean preferencesMatch = preferences.getPreferences().stream().allMatch(preference ->
                (preference == Enums.Preference.ADVENTURE && tripType == Enums.TripType.ADVENTURE_TRIP) ||
                        (preference == Enums.Preference.RELAX && (tripType == Enums.TripType.LUXURY_TRIP || tripType == Enums.TripType.FAMILY_FRIENDLY)) ||
                        (preference == Enums.Preference.CULTURE && tripType == Enums.TripType.CULTURAL)
        );

        return ageMatch && budgetMatch && durationMatch && preferencesMatch;
    }

    // Metodo per lasciare una recensione
    public void leaveReview(Guide guide, Traveller traveller, int rating, String comment) {
        // Verifica che il viaggiatore abbia partecipato al viaggio
        if (assignedGuides.contains(guide)) {
            Review review = new Review(guide, traveller, rating, comment);
            reviews.add(review);
            guide.addReview(review);  // Aggiunge la recensione alla guida
            System.out.println("📢 Recensione lasciata con successo!");
        } else {
            System.out.println("❌ Non puoi lasciare una recensione per una guida che non hai incontrato.");
        }
    }


}