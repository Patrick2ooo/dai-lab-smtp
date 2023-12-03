package myApp.MailConfig;

import java.util.ArrayList;

public class Prank {
    private final Groupe groupe;
    private final Message message;

    public Prank(Groupe groupe, Message message){
        this.groupe = new Groupe(groupe.getGroupe());
        this.message = message;
    }

    public Mail setupMail(){
        ArrayList<Personne> receiver = new ArrayList<Personne>();
        receiver.addAll(groupe.getGroupe().subList(1, groupe.getGroupe().size()));

        return new Mail(
            message.getSubject(),
            message.getContent(),
            groupe.getSender(),
            receiver
        );
    }
}
