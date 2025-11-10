package unicam.filieraAgricola.models;

public class Animatore extends UtenteLoggato{

    public Animatore(String nome, String cognome, String email, String password) {
        super(nome,cognome,email,password);
    }

    public Animatore(){
        super();
    }
}
