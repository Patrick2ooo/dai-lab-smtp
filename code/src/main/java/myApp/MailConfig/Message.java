package myApp.MailConfig;


public class Message {
    private String mailContent;
    private String subject;

    public Message(String content, String sujet){
        mailContent = content;
        subject = sujet;
    }

    public String getContent(){
        return mailContent;
    }

    public String getSubject(){
        return subject;
    }
}