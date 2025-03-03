public class Booking {
    private Trip trip; // Il viaggio associato alla prenotazione
    private PaymentDetails paymentDetails; // Dettagli di pagamento
    private int numberOfPeople; // Numero di persone per cui è stata effettuata la prenotazione

    // Costruttore
    public Booking(Trip trip, PaymentDetails paymentDetails, int numberOfPeople) {
        this.trip = trip;
        this.paymentDetails = paymentDetails;
        this.numberOfPeople = numberOfPeople;
    }

    // Getter per il viaggio
    public Trip getTrip() {
        return trip;
    }

    // Getter per i dettagli di pagamento
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    // Getter per il numero di persone
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    // Metodo per prenotare un viaggio (inserisce la prenotazione nella lista del viaggio)
    public void book() {
        if (trip.bookSpots(numberOfPeople)) {
            System.out.println("Prenotazione effettuata con successo per " + numberOfPeople + " persone.");
        } else {
            System.out.println("Impossibile prenotare per " + numberOfPeople + " persone.");
        }
    }

    // Metodo per annullare la prenotazione
    public void cancel() {
        trip.cancelBooking(numberOfPeople);
        System.out.println("Prenotazione annullata.");
    }
}