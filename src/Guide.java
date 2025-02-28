import java.util.List;

public class Guide implements User{
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Enums.Skill> skills;
    private boolean hasLicense;
    private List<Trip> assignedTrips;

    public Guide(String id, String name, String email, String password, List<Enums.Skill> skills, boolean hasLicense) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.skills = skills;
        this.hasLicense = hasLicense;
    }

    public boolean hasLicense() {
        return hasLicense;
    }

    public List<Enums.Skill> getSkills() {
        return skills;
    }

    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public String getEmail() { return email; }
    @Override
    public String getRole() { return "GUIDE"; }
}


