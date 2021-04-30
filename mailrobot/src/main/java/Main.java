import config.ConfigManager;
import config.IConfigManager;
import smtp.SmtpClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static ConfigManager configurationManager = new ConfigManager();


    private static SmtpClient client;

    public static void main(String...args){
        Path currentRelativePath = Paths.get("");
        String s = ((Path) currentRelativePath).toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        client = new SmtpClient(configurationManager.getSmtpServerAddress(),configurationManager.getSmtpServerPort());

    }
}
