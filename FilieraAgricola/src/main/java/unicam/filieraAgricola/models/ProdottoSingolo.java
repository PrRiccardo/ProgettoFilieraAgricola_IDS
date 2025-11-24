package unicam.filieraAgricola.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Entity
@DiscriminatorValue("SINGOLO")
public class ProdottoSingolo extends Prodotto {

    private StatoProdotto statoProdotto;

    public ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
        super (nome, descrizione, prezzo, quantita, idVenditore);
        this.statoProdotto = StatoProdotto.IN_ATTESA_APPROVAZIONE;
    }

    public ProdottoSingolo() {}

    public StatoProdotto getStatoProdotto() {
        return statoProdotto;
    }
    public void setStatoProdotto(StatoProdotto statoProdotto) {
        this.statoProdotto = statoProdotto;
    }
}
