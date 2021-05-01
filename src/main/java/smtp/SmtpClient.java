package smtp;

import mail.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private final String smtpServerAddrese;
    private int smtpServerPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private final String sautLigne = "\r\n";

    /**
     * @param smtpServerAdresse
     * @param port
     */
    public SmtpClient(String smtpServerAdresse, int port) {
        this.smtpServerAddrese = smtpServerAdresse;
        this.smtpServerPort = port;
    }

    /**
     * @param message
     */
    @Override
    public void sendMessage(Message message) {
        LOG.info("Sending message via SMTP");

        try {
            socket = new Socket(smtpServerAddrese, smtpServerPort);

            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String line = reader.readLine();
            LOG.info(line);

            //Prendre contact avec serveur smtp
            writer.write("EHLO localhost" + sautLigne);
            writer.flush();
            line = reader.readLine(); //Récupérer réponse serveur
            LOG.info(line);
            if (!line.startsWith("250-")) {
                throw new IOException("SMTP error: " + line);
            }
            //Récupérer info envoyé par le serveur
            while (line.startsWith("250-")) {
                line = reader.readLine();
                LOG.info(line);
            }


            //MAIL FROM
            writer.write("MAIL FROM:" + message.getFrom());
            writer.write(sautLigne);
            writer.flush();
            line = reader.readLine();
            if (!line.startsWith("250")) {
                throw new IOException("SMTP error: " + line);
            }
            LOG.info(line);

            //Partie To
            Iterator i = message.getTo().iterator();
            while (i.hasNext()) {
                writer.write("RCPT TO:" + i.next());
                writer.write(sautLigne);
                writer.flush();
                line = reader.readLine();
                if (!line.startsWith("250")) {
                    throw new IOException("SMTP error: " + line);
                }
                LOG.info(line);
            }

            //Partie CC
            i = message.getCc().iterator();
            while (i.hasNext()) {
                writer.write("RCPT TO:" + i.next());
                writer.write(sautLigne);
                writer.flush();
                line = reader.readLine();
                if (!line.startsWith("250")) {
                    throw new IOException("SMTP error: " + line);
                }
                LOG.info(line);
            }

            //partie BCC
            i = message.getBcc().iterator();
            while (i.hasNext()) {
                writer.write("RCPT TO:" + i.next());
                writer.write(sautLigne);
                writer.flush();
                line = reader.readLine();
                if (!line.startsWith("250")) {
                    throw new IOException("SMTP error: " + line);
                }
                LOG.info(line);
            }

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
            writer.write("To: " + i.next());

            while (i.hasNext()) {
                writer.write(", " + i.next());
            }
            //Première ligne sans la virgule
            i = message.getCc().iterator();
            writer.write(sautLigne);
            writer.write("Cc: " + i.next());
            while (i.hasNext()) {
                writer.write(", " + i.next());
            }
            writer.write(sautLigne);

            //Partie body
            writer.flush();
            writer.write("Subject: " + message.getSubject() + "\n\n");
            LOG.info(message.getSubject());
            writer.write(message.getBody());
            LOG.info(message.getBody());
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
