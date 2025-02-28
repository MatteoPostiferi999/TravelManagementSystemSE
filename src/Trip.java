import java.util.List;

public class Trip {
    private String id;
    private String destination;
    private List<Activity> activities;
    private int availableSpots;
    private double price;
    private Guide assignedGuide;
    private List<Enums.Skill> requiredGuideSkills;

    public Trip(String id, String destination, List<Activity> activities, int availableSpots, double price) {
        this.id = id;
        this.destination = destination;
        this.activities = activities;
        this.availableSpots = availableSpots;
        this.price = price;
    }
    public List<Enums.Skill> getRequiredGuideSkills() {
        return requiredGuideSkills;
    }

}
