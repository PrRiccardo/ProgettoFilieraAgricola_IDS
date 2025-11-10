package unicam.filieraAgricola.models;

public class Produttore extends Venditore{

    public Produttore(String nome, String cognome, String email, String password){
        super(nome,cognome,email,password);
    }

    public Produttore(){
        super();
    }
}
