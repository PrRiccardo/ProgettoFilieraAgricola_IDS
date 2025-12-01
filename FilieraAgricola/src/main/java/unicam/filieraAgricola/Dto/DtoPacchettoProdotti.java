package unicam.filieraAgricola.Dto;

import unicam.filieraAgricola.models.PacchettoProdotti;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.ProdottoSingolo;

import java.util.ArrayList;
import java.util.List;

public class DtoPacchettoProdotti extends  DtoProdotto {

    private List<String> prodotti;

    public DtoPacchettoProdotti() {
        this.prodotti = new ArrayList<String>();
    }

    public DtoPacchettoProdotti(String nome, String descrizione, int quantita, String idVenditore, List<String> prodotti) {
        super(nome, descrizione, 0, quantita,idVenditore);
        this.prodotti = prodotti;
    }

    public List<String> getIdProdotti() {
        return prodotti;
    }
}
