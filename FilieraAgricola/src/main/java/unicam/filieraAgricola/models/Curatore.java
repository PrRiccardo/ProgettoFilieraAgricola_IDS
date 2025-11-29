package unicam.filieraAgricola.models;

public class Curatore extends UtenteLoggato{

    public Curatore(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public Curatore(){
        super();
    }
}
