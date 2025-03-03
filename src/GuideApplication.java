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
}