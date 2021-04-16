package prank;

import mail.Group;
import mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());

    public PrankGenerator() {
    }

    public List<Prank> generatePrank(){
            List<Prank>pranks = new ArrayList<>();

            List<String> messages; //Voir avec Viotti
            int msgIndex = 0;

            int nombreGroupes = 0; //Voir avec Viotti
            int nombreDeVictims = 0; //Voir avec Nico

            if(nombreDeVictims / nombreGroupes < 3){
                LOG.warning("Vous êtes trop gentis. Il n'y a pas assez de victimes");
            }
            List<Group> groups = null;
            for(Group group : groups){
                Prank prank = new Prank();
                List<Person> victims = group.getPersons();
                Collections.shuffle(victims);
                Person sender = victims.remove(0);
                prank.setVictimSender(sender);
                //prank.add

                //String message = messages.get(messageIndex);
                //messageIndex = (messageIndex + 1) % messages.size();
               // prank.setMessage(message);

                pranks.add(prank);
            }
            return pranks;
    }
}
