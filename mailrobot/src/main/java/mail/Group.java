package mail;

import java.util.ArrayList;

public class Group {

    ArrayList<Person> persons;
    String nom;

    public Group(ArrayList<Person> persons, String nom) {
        System.arraycopy(persons, 0, this.persons, 0, persons.size());
        this.nom = nom;
    }

    public Group() {

    }

    public void ajouterMembre(Person p) {
        persons.add(p);
    }

    public ArrayList<Person> getPersons() {
        return new ArrayList<Person>(persons);
    }


}
