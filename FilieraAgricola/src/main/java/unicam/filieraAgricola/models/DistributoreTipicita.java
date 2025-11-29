package unicam.filieraAgricola.models;

public class DistributoreTipicita extends Venditore{

    public DistributoreTipicita(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        super(nome,cognome,email,password,telefono,ruolo);
    }

    public DistributoreTipicita(){
        super();
    }
}

