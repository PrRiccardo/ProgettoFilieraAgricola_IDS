package unicam.filieraAgricola.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class ProdottoSingolo extends Prodotto{



    public ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
     super (nome, descrizione, prezzo, quantita, idVenditore);
    }

    public ProdottoSingolo() {}


}
