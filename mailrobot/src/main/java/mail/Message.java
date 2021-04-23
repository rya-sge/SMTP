package mail;

import java.util.LinkedList;
import java.util.List;

public class Message {
    private String from;

    public LinkedList<String> getTo() {
        return to;
    }

    public LinkedList<String> getCc() {
        return cc;
    }

    public LinkedList<String> getBcc() {
        return bcc;
    }

    private LinkedList<String> to = new LinkedList();
    private LinkedList<String> cc = new LinkedList();
    private LinkedList<String> bcc = new LinkedList();
    private String subject;
    private String body;
    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }



    public String getFrom(){
        return from;
    }
    public void setFrom(String from){
        this.from = from;
    }

}
