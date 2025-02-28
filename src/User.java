public interface User {
    String getId();
    String getName();
    String getEmail();
    String getRole();
    public abstract boolean checkPassword(String password); // Metodo per verificare la password

    void setEmail(String newEmail);

    void setPassword(String newPassword);
}
