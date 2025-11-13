package unicam.filieraAgricola.models;

public class Produttore extends Venditore{

    public Produttore(String nome, String cognome, String email, String password, RuoloUtente ruolo){
        super(nome,cognome,email,password, ruolo);
    }

    public Produttore(){
        super();
    }
}
