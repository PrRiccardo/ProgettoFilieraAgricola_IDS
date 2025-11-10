package unicam.filieraAgricola.models;

public class Curatore extends UtenteLoggato{

    public Curatore(String nome, String cognome, String email, String password) {
        super(nome,cognome,email,password);
    }

    public Curatore(){
        super();
    }
}
