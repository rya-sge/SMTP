package config;


import mail.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ConfigManager  implements IConfigManager{
    private String smtpServerAddress;
    private int stmpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOfGroups;
    private List<Person> witnesses;

    public ConfigManager() throws IOException {
        victims = loadPersonsFromFile("./config/victims.json");
        messages = loadMessagesFromFile("./config/messages.json");
        loadConfig("./config/config.json");
    }

    /**
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private List<Person> loadPersonsFromFile(String fileName) throws IOException {
        List<Person> result = new ArrayList<>();
    //JSON parser object pour lire le fichier
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("../../config/" +fileName)) {

        // lecture du fichier
        Object obj = jsonParser.parse(reader);
        JSONArray victimes = (JSONArray)obj;

        JSONArray personne = (JSONArray) victimes;

        // parcours du tableau de personnes
        personne.forEach(pers->
        {
            result.add(parsePersonneObject((JSONObject)pers));
        });
    }
        catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        catch (IOException e) {
        e.printStackTrace();
    }
        catch (ParseException e) {
        e.printStackTrace();
    }

    return result;
}

    /**
     *
     * @param fileName
     * @return
     */
    private List<String> loadMessagesFromFile(String fileName)
{
    List<String> result = new ArrayList<>();
    //JSON parser object pour lire le fichier
    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("../../config/" +fileName)) {

        // lecture du fichier
        Object obj = jsonParser.parse(reader);

        JSONArray message = (JSONArray) obj;

        // parcours du tableau de personnes
        message.forEach(mess->
        {
            result.add(parseMessageObject((JSONObject)mess));
        });
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    catch (ParseException e) {
        e.printStackTrace();
    }

    return result;
}

    /**
     *
     * @param mess
     * @return
     */
    private static String parseMessageObject(JSONObject mess) {

        // Obtenir l'objet personne dans la liste
        JSONObject messObject = (JSONObject) mess.get("message");

        // obtenir les détails ...
        String text    = (String) messObject.get("text");

        // afficher le contenu
        return text;
    }

    /**
     *
     * @param pers
     * @return
     */
    private static Person parsePersonneObject(JSONObject pers) {

        // Obtenir l'objet personne dans la liste
        JSONObject persObject = (JSONObject) pers.get("personne");

        // obtenir les détails ...
        String nom    = (String) persObject.get("nom");
        String prenom = (String) persObject.get("prenom");
        String email  = (String) persObject.get("email");

        // afficher le contenu
        return new Person(nom, email, prenom);
    }

    /**
     *
     * @param fileName
     */
    private void loadConfig(String fileName){
        List<String> result = new ArrayList<>();
        //JSON parser object pour lire le fichier
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("../../config/" +fileName)) {

            // lecture du fichier
            Object obj = jsonParser.parse(reader);
            JSONObject config = (JSONObject) obj;
            this.smtpServerAddress = (String) config.get("smtpServerAddress");
            this.stmpServerPort = (int) config.get("smtpServerPort");
            this.numberOfGroups = (int) config.get("numberOfGroups");
            this.witnesses = new ArrayList<>();
            String witnesses = (String) config.get("witnessesToCC");
            String[] witnessesAdresses = witnesses.split(",");
            for(String adresse : witnessesAdresses)
            {
                this.witnesses.add(new Person(adresse));
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Person> getVictims()
    {
        return victims;
    }

    @Override
    public List<String> getMessages()
    {
        return messages;
    }

    @Override
    public List<Person> getWitnesses()
    {
        return witnesses;
    }

    @Override
    public int getNumberOfGroups()
    {
        return numberOfGroups;
    }


}
