package mail;

import java.util.ArrayList;
import java.util.List;

public class Group {

    List<Person> persons;
    String nom;

    /**
     *
     * @param persons
     * @param nom
     */
    public Group(List<Person> persons, String nom) {
        System.arraycopy(persons, 0, this.persons, 0, persons.size());
        this.nom = nom;
    }

    public Group() {

    }

    /**
     *
     * @param p
     */
    public void addMember(Person p) {
        persons.add(p);
    }

    /**
     *
     * @return
     */
    public List<Person> getPersons() {
        return new ArrayList<Person>(persons);
    }


}
