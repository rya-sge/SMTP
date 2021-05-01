package config;

import mail.Message;
import mail.Person;

import java.util.List;

public interface IConfigManager {
    public List<Person> getVictims();
    public List<Message> getMessages();
    public List<Person> getWitnesses();
    public int getNumberOfGroups();
    public String getSmtpServerAddress();
    public int getSmtpServerPort();
}
