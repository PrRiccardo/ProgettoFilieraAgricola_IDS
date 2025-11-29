package unicam.filieraAgricola.models;

public class Animatore extends UtenteLoggato{

    public Animatore(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public Animatore(){
        super();
    }
}
