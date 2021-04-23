package smtp;


import mail.Message;

public interface iSmtpClient {
    public void sendMessage(Message message);
}
