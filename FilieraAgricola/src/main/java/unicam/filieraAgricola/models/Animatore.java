package unicam.filieraAgricola.models;

public class Animatore extends UtenteLoggato{

    public Animatore(String nome, String cognome, String email, String password, RuoloUtente ruolo) {
        super(nome,cognome,email,password,ruolo);
    }

    public Animatore(){
        super();
    }
}
