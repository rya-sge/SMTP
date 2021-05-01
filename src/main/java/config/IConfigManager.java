package config;

import mail.Message;
import mail.Person;

import java.util.List;

public interface IConfigManager {
    List<Person> getVictims();
    List<Message> getMessages();
    List<Person> getWitnesses();
    int getNumberOfGroups();
    String getSmtpServerAddress();
    int getSmtpServerPort();
}
