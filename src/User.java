public interface User {
    String getId();
    String getName();
    String getEmail();
    String getRole();
    boolean checkPassword(String password); // Metodo per verificare la password
}
