package unicam.filieraAgricola.Dto;

import unicam.filieraAgricola.models.PacchettoProdotti;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.ProdottoSingolo;

import java.util.List;

public class DtoPacchettoProdotti extends  DtoProdotto {

    private List<Prodotto> prodotti;

    public DtoPacchettoProdotti() {}

    public DtoPacchettoProdotti(String nome,String descrizione, double prezzo, int quantita, String idVenditore) {
        super(nome, descrizione, prezzo, quantita,idVenditore);
        this.prodotti = prodotti;
    }
        public DtoPacchettoProdotti(PacchettoProdotti pacchettoProdotti) {
        super(pacchettoProdotti);
        this.prodotti = pacchettoProdotti.getProdotti();
        }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }
}
