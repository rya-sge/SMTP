package smtp;

//import javax.mail.Message;
import mail.Message;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.logging.Logger;

public class SmtpClient implements iSmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private String smtpServerAddrese;
    private int smtpServerPort = 25;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String sautLigne = "\r\n";
    public SmtpClient(String smtpServerAdresse, int port) {
        this.smtpServerAddrese = smtpServerAdresse;
        this.smtpServerPort = port;
    }
    @Override
    public void sendMessage(Message message) {
            LOG.info("Sending message via SMTP");

            try {
                Socket socket = new Socket(smtpServerAddrese, smtpServerPort);

                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                String line = reader.readLine();
                LOG.info(line);

                //Prendre contact avec serveur smtp
                writer.write("EHLO localhost\r\n");
                line = reader.readLine(); //Récupérer réponse serveur
                LOG.info(line);
                if(!line.startsWith("250")) {

                }

                //MAIL FROM
                writer.write("MAIL FROM:" + message.getFrom());
                writer.write(sautLigne);
                writer.flush();
                line = reader.readLine();
                if(!line.startsWith("250")) {

                }
                LOG.info(line);

                //Partie To
                Iterator i = message.getTo().iterator();
                while(i.hasNext()) {
                    writer.write("RCPT TO:");
                    writer.write((char[]) i.next());
                    writer.write(sautLigne);
                    writer.flush();
                    line = reader.readLine();
                    LOG.info(line);
                }

                writer.write(sautLigne);

                //Partie CC
                i = message.getCc().iterator();
                while(i.hasNext()) {
                    writer.write("Cc: " + i.next());
                }

                writer.write(sautLigne);
                writer.flush();

                //Partie body
                LOG.info(message.getBody());
                writer.write(message.getBody());
                writer.write("\r\n");
                writer.write(".");//Fin message
                writer.write("\r\n");
                writer.flush();

                line = reader.readLine();
                LOG.info(line);
                writer.write("QUIT\r<n");
                writer.flush();
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
