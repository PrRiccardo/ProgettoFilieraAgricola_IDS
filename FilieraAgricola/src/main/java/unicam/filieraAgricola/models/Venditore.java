package unicam.filieraAgricola.models;

public class Venditore extends UtenteLoggato{

    public Venditore(String nome, String cognome, String email, String password, RuoloUtente ruolo) {
        super(nome,cognome,email,password,ruolo);
    }

    public Venditore(){
        super();
    }
}
