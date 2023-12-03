package myApp.SMTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import myApp.MailConfig.*;

public class Client{
    private final String ServerAddress;
    private final int ServerPort;
    PrintWriter out;
    BufferedReader in;
    String line;

    public Client(String ServerAddress, int ServerPort){
        this.ServerAddress = ServerAddress;
        this.ServerPort = ServerPort;
    }

    private void sendAndCheck(String content) throws IOException{
        out.write(content + "\r\n");
        out.flush();
        line = in.readLine();
        if (!line.startsWith("250")) {
            throw new IOException("SMTP error: " + line);
        }
        System.out.println(line);
    }


    public void envoieMail(Mail mail){
        Socket clientSocket;
        try{
            clientSocket = new Socket(ServerAddress, ServerPort);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            line = in.readLine();
 
            sendAndCheck("EHLO " + ServerAddress);

            while(line.startsWith("250-")) {
                line = in.readLine();
                System.out.println(line);
            }

            sendAndCheck("MAIL FROM: " + "<" + mail.getSender().getMailAddress() + ">");

            for(Personne receiver : mail.getReceiver()){
                sendAndCheck("RCPT TO: " + "<" +receiver.getMailAddress() + ">");
            }

            out.write("DATA\r\n");
            out.flush();

            line = in.readLine();
           
            out.write("Content-Type: text/html; charset=utf-8\r\n");
            out.write("FROM: " + mail.getSender().getMailAddress() + "\r\n");

            out.write("TO: " + mail.getReceiver().get(0).getMailAddress() + "\r\n");
            for(int i = 1; i < mail.getReceiver().size(); i++){
                out.write("TO: " + mail.getReceiver().get(i).getMailAddress() + "\r\n");
            }

            out.write("Subject: " + mail.getSubject() + "\r\n\r\n");
            

            out.write(mail.getContent() + "\r\n");

            out.write("\r\n.\r\n");

            out.flush();

            line = in.readLine();

            out.write("QUIT\r\n");
            out.flush();

            System.out.println(line = in.readLine());

            clientSocket.close();
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
}