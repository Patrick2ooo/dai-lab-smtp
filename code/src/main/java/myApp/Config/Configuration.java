package myApp.Config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

import myApp.MailConfig.Message;
import myApp.MailConfig.Personne;

public class Configuration {
    ArrayList<Personne> victims = new ArrayList<Personne>();
    ArrayList<Message> messages = new ArrayList<Message>();
    int nbrDeGroupe;

    //found in https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 

    Pattern pat = Pattern.compile(emailRegex); 


    public Configuration(){}

    public void addVictims(String filename){        
        //lire fichier victims
        try(var reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))){
            String line;
            while((line = reader.readLine()) != null){
                victims.add(new Personne(line));
            }
        }catch(IOException e){
            System.out.println("Error in reading victims: " + e);
        }

    }
    public void addMessage(String filename){
        //lire fichier message
        String sujet = new String();
        boolean isBody = false;
        StringBuilder content = new StringBuilder();
            try(var reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.startsWith("Subject:")){
                    sujet = line.substring(line.indexOf(" ") + 1);
                }
                else if (line.startsWith("Body:")){
                    content.append(line.substring(line.indexOf(" ") + 1) + "\n");
                    isBody = true;
                }
                else if(isBody && !line.startsWith("---")){
                    content.append(line + "\n");
                }
                else if(line.startsWith("---")){
                    isBody = false;
                    messages.add(new Message(content.toString(), sujet));
                    content = new StringBuilder("");
                }
            }
        }catch(IOException e){
            System.out.println("Error in reading message: " + e);
        }
    }

    public void addNbrDeGroupe(String filename){

        try(var reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))){
            String line;
            line = reader.readLine();
            if(line.startsWith("NbrDeGroupe:")){
                nbrDeGroupe = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            } 
        }catch(IOException e){
            System.out.println("Error in reading configuration: " + e);
        }
    }

    public void configCheck(){
        if((victims.size() < nbrDeGroupe * 2)){
            throw new RuntimeException("Invalid number of victims");
        }
        if(nbrDeGroupe <= 0){
            throw new RuntimeException("Invalid number of groups");
        }
        for(Personne victim : victims){
            if(!pat.matcher(victim.getMailAddress()).matches()){
                throw new RuntimeException("The Email " + victim.getMailAddress() + " isn't valid");
            }
        }
        if(messages.size() == 0){
            throw new RuntimeException("Please add message to your configuration");
        }
    }

    public ArrayList<Personne> getVictims(){
        return victims;
    }

    public ArrayList<Message> getMessage(){
        return messages;
    }

    public int getNbrDeGroupe(){
        return nbrDeGroupe;
    }
}
