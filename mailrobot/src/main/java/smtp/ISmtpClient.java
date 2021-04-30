package smtp;


import mail.Message;


public interface ISmtpClient {
    /**
     *
     * @param message
     */
    public void sendMessage(Message message);
}
