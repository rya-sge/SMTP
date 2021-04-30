package prank;

import mail.Message;
import mail.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Prank {

    private Person victimSender;
    private final ArrayList<Person> victimRecipients = new ArrayList<>();
    private final ArrayList<Person> witnessRecipients = new ArrayList<>();
    private String message;

    public Prank(Person victimSender, String message) {
        this.victimSender = victimSender;
        this.message = message;
    }

    public Prank() {

    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getVictimSender() {
        return victimSender;
    }

    public ArrayList<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public ArrayList<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    public String getMessage() {
        return message;
    }

    public Message generateMailMessage(){
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" +  victimSender.getNom());
        Iterator i = victimRecipients.iterator();
        ArrayList<String> b = new ArrayList();
        while(i.hasNext()){
            b.add((String) i.next());
        }
        msg.setTo(b);
        return msg;
    }


    public void addVictimRecipients(List<Person> victims) {
        System.arraycopy(this.victimRecipients,this.victimRecipients.size(), victims,0, victims.size());
    }

    public void addWitnessRecipients(List<Person> witnesses) {
        System.arraycopy(this.witnessRecipients,this.witnessRecipients.size(), witnesses,0, witnesses.size());
    }
}
