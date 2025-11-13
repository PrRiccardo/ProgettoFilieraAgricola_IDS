package unicam.filieraAgricola.models;

public class DistributoreTipicita extends Venditore{

    public DistributoreTipicita(String nome, String cognome, String email, String password, RuoloUtente ruolo){
        super(nome,cognome,email,password,ruolo);
    }

    public DistributoreTipicita(){
        super();
    }
}

