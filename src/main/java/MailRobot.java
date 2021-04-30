import config.ConfigManager;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;

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
            //Prank p = new Prank
        }
    }
}
