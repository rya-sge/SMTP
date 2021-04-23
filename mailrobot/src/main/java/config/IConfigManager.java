package config;

import mail.Person;

import java.util.List;

public interface IConfigManager {
    public abstract List<Person> getVictims();
    public abstract List<String> getMessages();
}
