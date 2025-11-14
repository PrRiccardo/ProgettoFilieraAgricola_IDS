package unicam.filieraAgricola.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class PacchettoProdotti {

    @Id
    private String idPacchetto;

    private String nome;
    private String descrizione;
    private double prezzoTotale;
    private int quantita;

    private String idDistributore;

    @DBRef
    private List<Prodotto> prodotti;

    public PacchettoProdotti(String nome, String descrizione, String idDistributore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.idDistributore = idDistributore;
    }

    public PacchettoProdotti() {}

    public void calcolaPrezzoTotale(){
        double temp = 0;
        for(Prodotto prodotto : prodotti){
            temp+=prodotto.getPrezzo();
        }
        this.prezzoTotale = temp;
    }

    public String getIdPacchetto() {
        return idPacchetto;
    }
    public void setIdPacchetto(String idPacchetto) {
        this.idPacchetto = idPacchetto;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }
    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getIdDistributore() {
        return idDistributore;
    }
    public void setIdDistributore(String idDistributore) {
        this.idDistributore = idDistributore;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }
    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
        calcolaPrezzoTotale();
    }
}
