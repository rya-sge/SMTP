package mail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Message {
    private String from;

    public ArrayList<String> getTo() {
        return to;
    }

    public ArrayList<String> getCc() {
        return cc;
    }

    public ArrayList<String> getBcc() {
        return bcc;
    }

    public void setCc(ArrayList<String> cc) {
        System.arraycopy(cc, 0, this.cc, 0, cc.size());
    }

    public void setTo(ArrayList<String> to) {
        System.arraycopy(to, 0, this.to, 0, to.size());
    }

    private ArrayList<String> to = new ArrayList();
    private ArrayList<String> cc = new ArrayList();
    private ArrayList<String> bcc = new ArrayList();
    private String subject;

    public void setBody(String body) {
        this.body = body;
    }

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
