package unicam.filieraAgricola.models;

public class Produttore extends Venditore{

    public Produttore(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public Produttore(){
        super();
    }
}
