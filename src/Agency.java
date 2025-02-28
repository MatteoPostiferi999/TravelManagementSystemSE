public class Agency {
    public boolean approveGuide(Guide guide, Trip trip) {
        return guide.hasLicense() && guide.getSkills().containsAll(trip.getRequiredSkills());
    }
}
