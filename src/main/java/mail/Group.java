package mail;

import java.util.ArrayList;
import java.util.List;

public class Group {

    List<Person> persons = new ArrayList<>();

    public Group() { }

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
        return new ArrayList<>(persons);
    }


}
