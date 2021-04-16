package mail;

public class Person{
    String nom;
    String prenom;
    String email;


    public Person(String nom, String email, String prenom) {
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

}
