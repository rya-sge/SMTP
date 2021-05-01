import config.ConfigManager;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;
import mail.Message;
import java.util.List;

public class MailRobot {
    private final static ConfigManager configurationManager = new ConfigManager();

    public static void main(String... args) {
        SmtpClient client = new SmtpClient(configurationManager.getSmtpServerAddress(), configurationManager.getSmtpServerPort());
        PrankGenerator Prankgenerator = new PrankGenerator(configurationManager);
        List<Prank> g = Prankgenerator.generatePrank();
        for (Prank p : g) {
            Message m = new Message();
            m.setBody(p.getMessage().getBody());
            m.setSubject(p.getMessage().getSubject());
            p.setMailMessage(m);
            client.sendMessage(m);
        }
    }
}
