package mail;

public class Person{
    String nom;
    String prenom;
    String email;

    /**
     *
     * @param nom
     * @param email
     * @param prenom
     */
    public Person(String nom, String email, String prenom) {
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

}
