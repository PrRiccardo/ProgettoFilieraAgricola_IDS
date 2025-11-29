package unicam.filieraAgricola.models;

public class Trasformatore extends Venditore{

    public Trasformatore(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public Trasformatore(){
        super();
    }
}
