package unicam.filieraAgricola.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Prodotto {

    @Id
    private String idProdotto;

    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;

    private StatoProdotto statoProdotto;

    @DBRef
    private String idVenditore;

    private String motivoRifiuto;

    public Prodotto(String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.idVenditore = idVenditore;
        this.statoProdotto = StatoProdotto.IN_ATTESA_APPROVAZIONE;
        this.motivoRifiuto = null;
    }

    public Prodotto() {}

    public String getId() {
        return idProdotto;
    }
    public void setId(String idProdotto) {
        this.idProdotto = idProdotto;
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

    public double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getIdVenditore() {
        return idVenditore;
    }
    public void setIdVenditore(String idVenditore) {
        this.idVenditore = idVenditore;
    }

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
