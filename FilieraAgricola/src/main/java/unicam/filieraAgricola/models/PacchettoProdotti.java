package unicam.filieraAgricola.models;

import java.util.List;

public class PacchettoProdotti extends Prodotto {

    private List<Prodotto> prodotti;

    public PacchettoProdotti(String nome, String descrizione, int quantita, String idVenditore, List<Prodotto> prodotti) {
        super (nome, descrizione, 0, quantita, idVenditore);
        this.prodotti = prodotti;
        this.setPrezzo(calcolaPrezzoTotale());
    }

    public PacchettoProdotti() {}

    public double calcolaPrezzoTotale(){
        double temp = 0;
        for(Prodotto prodotto : prodotti){
            temp+=prodotto.getPrezzo();
        }
        return temp;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }
    public void  setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
        this.setPrezzo(calcolaPrezzoTotale());
    }

}
