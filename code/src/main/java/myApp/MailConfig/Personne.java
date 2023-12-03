package myApp.MailConfig;

public class Personne {
    private final String email;
    
    public Personne(String email) {
        this.email = email;
    }
    
    public String getMailAddress() {
        return email;
    }
}