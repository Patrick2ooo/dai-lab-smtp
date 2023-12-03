package myApp;


import java.util.ArrayList;
import myApp.SMTP.Client;
import myApp.Config.*;
import myApp.MailConfig.*;



public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        String victimFile = "/home/pmaillard/dai-lab-smtp/config/victims.txt";
        String messageFile = "/home/pmaillard/dai-lab-smtp/config/message.txt";
        String configFile = "/home/pmaillard/dai-lab-smtp/config/config.txt";

        config.addVictims(victimFile);
        config.addMessage(messageFile);
        config.addNbrDeGroupe(configFile);
        config.configCheck();

        String serverAdress = "localhost";
        int serverPort = 1025;

        Client client = new Client(serverAdress, serverPort);
        PrankConfig prankconfig = new PrankConfig(config);
        ArrayList<Prank> pranks = prankconfig.PrankSetup();

        for(Prank prank : pranks){
            client.envoieMail(prank.setupMail());           
        }
    }
}
