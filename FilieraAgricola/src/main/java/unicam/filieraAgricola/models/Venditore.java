package unicam.filieraAgricola.models;

public class Venditore extends UtenteLoggato{

    public Venditore(String nome, String cognome, String email, String password) {
        super(nome,cognome,email,password);
    }

    public Venditore(){
        super();
    }
}
