import java.util.ArrayList;
import java.util.List;

public class Guide implements User{
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Enums.Skill> skills;
    private boolean hasLicense;
    private List<Trip> assignedTrips;
    private List<Review> reviews;  // Lista delle recensioni


    public Guide(String id, String name, String email, String password, List<Enums.Skill> skills, boolean hasLicense) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.skills = skills;
        this.hasLicense = hasLicense;
        this.assignedTrips = new ArrayList<>();
        this.reviews = new ArrayList<>();

    }

    public boolean hasLicense() {
        return hasLicense;
    }

    public List<Enums.Skill> getSkills() {
        return skills;
    }


    public void notifyApproval(Trip trip) {
        System.out.println("Congratulazioni! Sei stato approvato per il viaggio: " + trip);
    }

    public void notifyRejection(Trip trip) {
        System.out.println("Spiacente, la tua candidatura per il viaggio " + trip + " è stata rifiutata.");
    }

    // Metodo per aggiungere una recensione alla guida
    public void addReview(Review review) {
        reviews.add(review);
    }

    // Metodo per calcolare la valutazione media
    public double getAverageRating() {
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    // Metodo per visualizzare tutte le recensioni
    public void showAllReviews() {
        if (reviews.isEmpty()) {
            System.out.println("❌ Nessuna recensione disponibile.");
            return;
        }

        System.out.println("\n🌟 Recensioni per " + name + ":");
        for (Review review : reviews) {
            System.out.println("Rating: " + review.getRating() + "/5 - Commento: " + review.getComment());
        }
    }

    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public String getEmail() { return email; }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getRole() { return "GUIDE"; }
    @Override
    public boolean checkPassword(String password) { return false; }
    @Override
    public void setEmail(String newEmail) { this.email = newEmail; }
    @Override
    public void setPassword(String newPassword) { this.password = newPassword; }

    public void addAssignedTrip(Trip selectedTrip) {
        assignedTrips.add(selectedTrip);
    }
    public void removeAssignedTrip(Trip selectedTrip) {
        assignedTrips.remove(selectedTrip);
    }
    // Metodo per annullare la candidatura da un viaggio
    public void cancelApplication(Trip trip) {
        GuideApplication application = trip.getGuideApplications().stream()
                .filter(app -> app.getGuide().equals(this))
                .findFirst().orElse(null);
        if (application != null) {
            application.cancelApplication();
        } else {
            System.out.println("Nessuna candidatura trovata per questo viaggio.");
        }
    }
}


