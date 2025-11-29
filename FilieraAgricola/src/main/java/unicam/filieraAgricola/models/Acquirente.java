package unicam.filieraAgricola.models;

public class Acquirente extends UtenteLoggato {

    public Acquirente(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public Acquirente(){
        super();
    }
}
