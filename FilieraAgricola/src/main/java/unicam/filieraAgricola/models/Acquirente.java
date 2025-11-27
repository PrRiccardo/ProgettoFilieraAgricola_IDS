package unicam.filieraAgricola.models;

public class Acquirente extends UtenteLoggato {

    public Acquirente(String nome, String cognome, String email, String password, RuoloUtente ruolo) {
        super(nome,cognome,email,password,ruolo);
    }

    public Acquirente(){
        super();
    }
}
