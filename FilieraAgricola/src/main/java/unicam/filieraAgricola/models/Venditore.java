package unicam.filieraAgricola.models;

public class Venditore extends UtenteLoggato{

    public Venditore(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public Venditore(){
        super();
    }
}
