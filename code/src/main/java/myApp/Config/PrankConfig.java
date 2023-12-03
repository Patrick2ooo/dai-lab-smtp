package myApp.Config;


import java.util.ArrayList;
import java.util.Collections;
import myApp.MailConfig.*;

public class PrankConfig {
    private final Configuration config;

    public PrankConfig(Configuration config){
        this.config = config;
    }

    //créer les différents groupe de victime aléatoirement
    public ArrayList<Groupe> PrankGroupes(){
        ArrayList<Groupe> groupes = new ArrayList<>();
        ArrayList<Personne> victims = new ArrayList<Personne>(config.getVictims());
        Collections.shuffle(victims);

        for(int i = 0; i < config.getNbrDeGroupe(); i++){
            int taille = victims.size();
            ArrayList<Personne> tmp = new ArrayList<>(victims);
            for(int j = config.getNbrDeGroupe(); j < taille; j++){
                tmp.remove(config.getNbrDeGroupe());
            }
            groupes.add(new Groupe(tmp));
            
            Collections.shuffle(victims);
        }
   
        return groupes;
    }

    //créer les différent prank
    public ArrayList<Prank> PrankSetup(){
        ArrayList<Prank> pranks = new ArrayList<>();
        ArrayList<Groupe> groupes = PrankGroupes();
        ArrayList<Message> messages = config.getMessage();
        Collections.shuffle(messages);

        for(Groupe groupe : groupes){
            Prank prank = new Prank(groupe, messages.get(0));
            Collections.shuffle(messages);
            pranks.add(prank);           
        }
        return pranks;
    }
}
