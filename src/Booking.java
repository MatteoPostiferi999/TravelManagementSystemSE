public class Booking {
    private Trip trip; // Il viaggio associato alla prenotazione
    private PaymentDetails paymentDetails; // Dettagli di pagamento

    public Booking(Trip trip, PaymentDetails paymentDetails) {
        this.trip = trip;
        this.paymentDetails = paymentDetails;
    }

    public Trip getTrip() {
        return trip;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
}
