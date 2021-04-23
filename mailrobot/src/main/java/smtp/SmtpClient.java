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

                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                String line = reader.readLine();
                LOG.info(line);

                //Prendre contact avec serveur smtp
                writer.write("EHLO localhost\r\n");
                line = reader.readLine(); //Récupérer réponse serveur
                LOG.info(line);
                if(!line.startsWith("250")) {
                    throw new IOException("SMTP error: " + line);
                }
                //Récupérer info envoyé par le serveur
                while(line.startsWith("250-")){
                    line = reader.readLine();
                    LOG.info(line);
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
                    writer.write("RCPT TO:" + i.next());
                    writer.write(sautLigne);
                    writer.flush();
                    line = reader.readLine();
                    LOG.info(line);
                }

                writer.write(sautLigne);

                //Partie CC
                i = message.getCc().iterator();
                while(i.hasNext()) {
                    writer.write("RCPT TO:" + i.next());
                    writer.write(sautLigne);
                    writer.flush();
                    line = reader.readLine();
                    LOG.info(line);
                }

                writer.write(sautLigne);
                writer.flush();

                //partie BCC
                i = message.getBcc().iterator();
                while(i.hasNext()) {
                    writer.write("RCPT TO:" + i.next());
                    writer.write(sautLigne);
                    writer.flush();
                    line = reader.readLine();
                    LOG.info(line);
                }
                //writer.write(sautLigne);
               // writer.flush();
                writer.write("DATA");
                writer.write(sautLigne);
                writer.flush();
                line = reader.readLine();
                LOG.info(line);
                writer.write("Content-type: text/plain; charset=\"utf-8\"\r\n");
                writer.write("From : " + message.getFrom() + sautLigne);

                //writer.write("to: " + message.getTo());
                i = message.getTo().iterator();

                //Première ligne sans la virgule
                writer.write("To: " + message.getTo());
                while(i.hasNext()) {
                    writer.write(", " + "To: "  + message.getTo());
                }
                writer.write(sautLigne);

                //Première ligne sans la virgule
                writer.write("Cc: " + message.getCc());
                while(i.hasNext()) {
                    writer.write(", " + "Cc: "  + message.getTo());
                }
                writer.write(sautLigne);

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
