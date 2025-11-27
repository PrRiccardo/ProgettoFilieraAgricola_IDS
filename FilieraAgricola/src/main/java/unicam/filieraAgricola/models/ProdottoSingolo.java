package unicam.filieraAgricola.models;

public class ProdottoSingolo extends Prodotto {

    private StatoProdotto statoProdotto;

    private String motivoRifiuto;

    public ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
        super (nome, descrizione, prezzo, quantita, idVenditore);
        this.statoProdotto = StatoProdotto.IN_ATTESA_APPROVAZIONE;
        this.motivoRifiuto = null;
    }

    public ProdottoSingolo() {}

    public StatoProdotto getStatoProdotto() {
        return statoProdotto;
    }
    public void setStatoProdotto(StatoProdotto statoProdotto) {
        this.statoProdotto = statoProdotto;
    }

    public String getMotivoRifiuto() {
        return motivoRifiuto;
    }
    public void setMotivoRifiuto(String motivoRifiuto) {
        this.motivoRifiuto = motivoRifiuto;
    }
}
