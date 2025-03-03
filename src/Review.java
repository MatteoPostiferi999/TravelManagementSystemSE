public class Review {
    private Guide guide;
    private Traveller traveller;
    private int rating;  // Punteggio da 1 a 5
    private String comment;  // Commento testuale

    public Review(Guide guide, Traveller traveller, int rating, String comment) {
        this.guide = guide;
        this.traveller = traveller;
        this.rating = rating;
        this.comment = comment;
    }

    public Guide getGuide() {
        return guide;
    }

    public Traveller getTraveller() {
        return traveller;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Rating: " + rating + "/5\nComment: " + comment;
    }
}