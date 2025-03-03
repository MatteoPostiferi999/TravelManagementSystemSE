public class GuideApplication {
    private Guide guide;
    private Trip trip;
    private boolean approved;

    public GuideApplication(Guide guide, Trip trip) {
        this.guide = guide;
        this.trip = trip;
        this.approved = false;
    }

    public Guide getGuide() {
        return guide;
    }

    public void approve() {
        this.approved = true;
        trip.assignGuide(guide);  // Assegna la guida al viaggio
        guide.notifyApproval(trip);
    }

    public void reject() {
        this.approved = false;
        guide.notifyRejection(trip);
    }

    public boolean isApproved() {
        return approved;
    }

    // Metodo per annullare la candidatura
    public void cancelApplication() {
        trip.getGuideApplications().remove(this); // Rimuove la candidatura dalla lista del viaggio
        System.out.println("Candidatura di " + guide.getName() + " per il viaggio " + trip.getDestination() + " è stata cancellata.");
    }

    // Metodo per inviare una nuova candidatura, se soddisfa i requisiti
    public static GuideApplication submitApplication(Guide guide, Trip trip) {
        if (guide.hasLicense() && guide.getSkills().containsAll(trip.getRequiredGuideSkills())) {
            GuideApplication application = new GuideApplication(guide, trip);
            trip.addGuideApplication(application);
            System.out.println("Candidatura di " + guide.getName() + " per il viaggio " + trip.getDestination() + " è stata inviata.");
            return application;
        } else {
            System.out.println("Impossibile inviare la candidatura: la guida non soddisfa i requisiti.");
            return null;
        }
    }
}