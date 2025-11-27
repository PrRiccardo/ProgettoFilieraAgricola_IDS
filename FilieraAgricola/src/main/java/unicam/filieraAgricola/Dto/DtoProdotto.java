package unicam.filieraAgricola.Dto;

import unicam.filieraAgricola.models.Prodotto;

public class DtoProdotto {
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private String idVenditore;

    public DtoProdotto() {}

    public DtoProdotto(String nome, String descrizione, double prezzo, int quantita,String idVenditore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.idVenditore = idVenditore;

    }

    public DtoProdotto(Prodotto prodotto) {
        this.nome = prodotto.getNome();
        this.descrizione = prodotto.getDescrizione();
        this.prezzo = prodotto.getPrezzo();
        this.quantita = prodotto.getQuantita();
        this.idVenditore = prodotto.getIdVenditore();
    }

    public String getNome() {return this.nome;}
    public String getDescrizione() {return this.descrizione;}
    public double getPrezzo() {return this.prezzo;}
    public int getQuantita() {return this.quantita;}
    public String getIdVenditore() {return this.idVenditore;}

}
