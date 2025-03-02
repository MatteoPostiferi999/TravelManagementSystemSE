import java.time.LocalDateTime;
import java.util.List;

public class Activity {
    private String id;
    private String name;
    private String description;
    private LocalDateTime time;
    private String location;
    private List<Guide> guides;

    public Activity(String id, String name, String description, LocalDateTime time, String location, List<Guide> guides) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.location = location;
        this.guides = guides;
    }

    public String getName() {
        return name;
    }
}

