package prank;

import config.IConfigManager;
import mail.Group;
import mail.Message;
import mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private IConfigManager configurationManager;

    /**
     * @param configurationManager
     */
    public PrankGenerator(IConfigManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    /**
     * @return
     */
    public List<Prank> generatePrank() {
        List<Prank> pranks = new ArrayList<>();

        List<Message> messages = configurationManager.getMessages();
        int messageIndex = 0;

        int nombreGroupes = configurationManager.getNumberOfGroups();
        int nombreDeVictims = configurationManager.getVictims().size();

        if (nombreDeVictims / nombreGroupes < 3) {
            LOG.warning("Vous êtes trop gentils. Il n'y a pas assez de victimes");
        }
        List<Group> groups = generateGroups(configurationManager.getVictims(), nombreGroupes);
        for (Group group : groups) {
            Prank prank = new Prank();
            List<Person> victims = group.getPersons();
            Collections.shuffle(victims);

            //Choix du sender
            Person sender = victims.remove(0);
            prank.setVictimSender(sender);

            //Ajout des victimes
            prank.addVictimRecipients(victims);

            //Ajout des témoins
            prank.addWitnessRecipients(configurationManager.getWitnesses());

            Message message = messages.get(messageIndex);
            messageIndex = (messageIndex + 1) % messages.size();
            prank.setMessage(message);

            pranks.add(prank);
        }
        return pranks;
    }

    /**
     * @param victims
     * @param numberOfGroups
     * @return
     */
    private List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
        List<Person> availableVictims = new ArrayList(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        Group targetGroup;
        while (availableVictims.size() > 0) {
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = availableVictims.remove(0);
            targetGroup.addMember(victim);
        }
        return groups;
    }
}
