package prank;

import config.IConfigManager;
import mail.Group;
import mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private IConfigManager configurationManager;

    public PrankGenerator(IConfigManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    public List<Prank> generatePrank(){
            List<Prank>pranks = new ArrayList<>();

            List<String> messages = configurationManager.getMessages();
            int messageIndex = 0;

            int nombreGroupes = 0;//Voir avec Viotti
            int nombreDeVictims = 0; //Voir avec Nico

            if(nombreDeVictims / nombreGroupes < 3){
                LOG.warning("Vous êtes trop gentis. Il n'y a pas assez de victimes");
            }
            List<Group> groups = generateGroups(configurationManager.getVictims(), nombreGroupes);
            for(Group group : groups){
                Prank prank = new Prank();
                List<Person> victims = group.getPersons();
                Collections.shuffle(victims);
                Person sender = victims.remove(0);
                prank.setVictimSender(sender);
                prank.addVictimeRecipients(victims);

                prank.addWitnessRecipients(victims);

                String message = messages.get(messageIndex);
                messageIndex = (messageIndex + 1) % messages.size();
                prank.setMessage(message);

                pranks.add(prank);
            }
            return pranks;
    }

    public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
        List<Person> availableVictims = new ArrayList(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for(int i = 0; i < numberOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        Group targetGroup;
        while(availableVictims.size() > 0){
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = availableVictims.remove(0);
            targetGroup.ajouterMembre(victim);
        }
        return groups;
    }
}
