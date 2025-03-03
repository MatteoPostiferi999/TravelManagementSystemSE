import java.util.*;

public class Agency {
    public void submitGuideApplication(Guide guide, Trip trip) {
        trip.addGuideApplication(new GuideApplication(guide, trip));
    }

    public void reviewGuideApplication(GuideApplication application, boolean approved) {
        if (approved) {
            application.approve();
        } else {
            application.reject();
        }
    }
}