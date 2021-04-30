package smtp;


import mail.Message;

public interface ISmtpClient {
    public void sendMessage(Message message);
}
