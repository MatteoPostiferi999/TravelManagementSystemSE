import java.util.List;

public class Traveller implements User{
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Enums.Preference> preferences;
    private List<Trip> bookedTrips;

    public Traveller(String id, String name, String email, String password, List<Enums.Preference> preferences) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
    }
    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public String getEmail() { return email; }
    @Override
    public String getRole() { return "TRAVELER"; }






}


