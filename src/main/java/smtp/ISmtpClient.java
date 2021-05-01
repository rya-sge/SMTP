package smtp;


import mail.Message;


public interface ISmtpClient {
    /**
     * @param message
     */
    void sendMessage(Message message);
}
