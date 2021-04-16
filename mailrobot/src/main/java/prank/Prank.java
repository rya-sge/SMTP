package prank;

import mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {

    private Person victimSender;
    private final ArrayList<Person> victimeRec = new ArrayList<>();
    private final List<Person> witnessRec = new ArrayList<>();
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

    public ArrayList<Person> getVictimeRec() {
        return victimeRec;
    }

    public List<Person> getWitnessRec() {
        return witnessRec;
    }

    public String getMessage() {
        return message;
    }




}
