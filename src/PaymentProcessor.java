public class PaymentProcessor {

    // Metodo per processare il pagamento
    public boolean processPayment(Booking booking) {
        // Verifica che i dettagli di pagamento siano validi
        if (booking == null || booking.getPaymentDetails() == null) {
            System.out.println("❌ Dettagli di pagamento non validi.");
            return false;
        }

        // Esegui la simulazione del pagamento o chiama il gateway di pagamento
        boolean paymentSuccess = simulatePaymentGateway(booking);

        // Verifica se il pagamento è riuscito
        if (paymentSuccess) {
            System.out.println("🎉 Pagamento effettuato con successo per il viaggio a " + booking.getTrip().getDestination());
            return true;
        } else {
            System.out.println("❌ Pagamento fallito.");
            return false;
        }
    }

    // Simulazione di chiamata al gateway di pagamento
    private boolean simulatePaymentGateway(Booking booking) {
        // Simulazione semplice di pagamento (ad esempio, verifica se la carta di credito è valida)
        if (booking.getPaymentDetails().getCardNumber().length() == 16) {
            return true; // Pagamento riuscito
        } else {
            return false; // Pagamento fallito
        }
    }
}