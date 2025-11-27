package unicam.filieraAgricola.Dto;

import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.StatoProdotto;
import unicam.filieraAgricola.models.ProdottoSingolo;
public class DtoProdottoSingolo extends DtoProdotto {

    private StatoProdotto statoProdotto;
    private String motivoRifiuto;

    public DtoProdottoSingolo(){}

    public DtoProdottoSingolo(String nome,String descrizione, double prezzo, int quantita) {
        super(nome, descrizione, prezzo, quantita);
        this.statoProdotto = statoProdotto;
        this.motivoRifiuto = motivoRifiuto;
    }

    public DtoProdottoSingolo(ProdottoSingolo prodotto){
        super(prodotto);
        this.statoProdotto = prodotto.getStatoProdotto();
        this.motivoRifiuto = prodotto.getMotivoRifiuto();
    }
}
