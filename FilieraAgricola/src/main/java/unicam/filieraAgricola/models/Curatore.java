package unicam.filieraAgricola.models;

public class Curatore extends UtenteLoggato{

    public Curatore(String nome, String cognome, String email, String password, RuoloUtente ruolo) {
        super(nome,cognome,email,password,ruolo);
    }

    public Curatore(){
        super();
    }
}
