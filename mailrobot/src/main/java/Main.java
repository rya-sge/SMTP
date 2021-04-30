import config.ConfigManager;
import config.IConfigManager;
import mail.Group;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static ConfigManager configurationManager = new ConfigManager();


    private static SmtpClient client;
    private static PrankGenerator Prankgenerator;

    public static void main(String...args){
        Path currentRelativePath = Paths.get("");
        String s = ((Path) currentRelativePath).toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        client = new SmtpClient(configurationManager.getSmtpServerAddress(),configurationManager.getSmtpServerPort());
        Prankgenerator  = new PrankGenerator(configurationManager);
        List<Prank> g =  Prankgenerator.generatePrank();
        Iterator i =g.iterator();
        while(i.hasNext()){
            //Prank p = new Prank
        }
    }
}
