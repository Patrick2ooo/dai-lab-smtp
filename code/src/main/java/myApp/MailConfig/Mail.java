package myApp.MailConfig;

import java.util.ArrayList;

public class Mail extends Message {
    private final Personne sender;
    private final ArrayList<Personne> receiver;
    
    public Mail(String subject, String content, Personne sender, ArrayList<Personne> receiver) {
        super(content, subject);
        
        this.sender = sender;
        this.receiver = receiver;
    }
    
    public Personne getSender() {
        return sender;
    }
    
    public ArrayList<Personne> getReceiver() {
        return receiver;
    }
}