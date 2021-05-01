package prank;

import mail.Message;
import mail.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Prank {

    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String message;

    /**
     *
     * @param victimSender
     * @param message
     */
    public Prank(Person victimSender, String message) {
        this.victimSender = victimSender;
        this.message = message;
    }

    /**
     *
     */
    public Prank() {

    }

    /**
     *
     * @param victimSender
     */
    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public Person getVictimSender() {
        return victimSender;
    }

    /**
     *
     * @return
     */
    public List<Person> getVictimRecipients() {
        return victimRecipients;
    }

    /**
     *
     * @return
     */
    public List<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return
     */
    public Message generateMailMessage(){
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" +  victimSender.getNom());
        Iterator i = victimRecipients.iterator();
        List<String> b = new ArrayList();
        while(i.hasNext()){
            b.add((String) i.next());
        }
        msg.setTo(b);
        return msg;
    }


    /**
     *
     * @param victims
     */
    public void addVictimRecipients(List<Person> victims) {
        this.victimRecipients.addAll(victims);
    }

    /**
     *
     * @param witnesses
     */
    public void addWitnessRecipients(List<Person> witnesses) {
        this.witnessRecipients.addAll(witnesses);
    }
}
