import config.ConfigManager;
import mail.Person;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;
import mail.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MailRobot {
    private static ConfigManager configurationManager = new ConfigManager();


    private static SmtpClient client;
    private static PrankGenerator Prankgenerator;

    public static void main(String...args){
        client = new SmtpClient(configurationManager.getSmtpServerAddress(),configurationManager.getSmtpServerPort());
        Prankgenerator  = new PrankGenerator(configurationManager);
        List<Prank> g =  Prankgenerator.generatePrank();
        Iterator i =g.iterator();
        while(i.hasNext()){
            Prank p = (Prank)i.next();
            Message m = new Message();
            m.setFrom(p.getVictimSender().getEmail());

            List<String> toList = new ArrayList<>();
            for(Person per : p.getVictimRecipients())
            {
                toList.add(per.getEmail());
            }
            m.setTo(toList);

            List<String> witnessList = new ArrayList<>();
            for(Person per : p.getWitnessRecipients())
            {
                witnessList.add(per.getEmail());
            }

            m.setCc(witnessList);
            m.setBody(p.getMessage().getBody());
            m.setSubject(p.getMessage().getSubject());
            p.generateMailMessage(m);
            client.sendMessage(m);

        }
    }
}
