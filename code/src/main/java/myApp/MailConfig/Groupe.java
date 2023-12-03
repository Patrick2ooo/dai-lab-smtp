package myApp.MailConfig;

import java.util.ArrayList;
import myApp.MailConfig.Groupe;

public class Groupe{
    private ArrayList<Personne> groupe;
    private Personne sender;

    public Groupe(){
        this(new ArrayList<>());
    }

    public Groupe(ArrayList<Personne> groupe){
        this.groupe = groupe;
        this.sender = groupe.getFirst();
    }

    public ArrayList<Personne> getGroupe(){
        return groupe;
    }

    public Personne getSender(){
        return sender;
    }

    public void addPersonne(Personne personne){
        groupe.add(personne);
    }
}