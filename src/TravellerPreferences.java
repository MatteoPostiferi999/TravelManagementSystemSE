import java.util.ArrayList;
import java.util.List;

public class TravellerPreferences {
    private int age;
    private double budget;
    private int duration;
    private List<Enums.Preference> preferences;

    public TravellerPreferences(int age, double budget, int duration, List<Enums.Preference> preferences) {
        this.age = age;
        this.budget = budget;
        this.duration = duration;
        this.preferences = new ArrayList<>(preferences);
    }

    public int getAge() { return age; }
    public double getBudget() { return budget; }
    public int getDuration() { return duration; }
    public List<Enums.Preference> getPreferences() { return preferences; }

    public void setAge(int age) { this.age = age; }
    public void setBudget(double budget) { this.budget = budget; }
    public void setDuration(int duration) { this.duration = duration; }

    public void addPreference(Enums.Preference preference) {
        if (!preferences.contains(preference)) {
            preferences.add(preference);
        }
    }

    public void removePreference(Enums.Preference preference) {
        preferences.remove(preference);
    }
}