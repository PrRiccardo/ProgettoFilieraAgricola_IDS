package unicam.filieraAgricola.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

public class Ordine {

    @Id
    private String idOrdine;

    @DBRef
    private final Carrello carrello;

    private String idAcquirente;

    private double prezzoTotale;
    private String Indirizzo;
    private String metodoPagamento;

    @CreatedDate
    private LocalDateTime dataPagamento;

    public Ordine(Carrello carrello, String idAcquirente, double prezzoTotale, String indirizzo, String metodoPagamento, LocalDateTime dataPagamento) {
        this.carrello = carrello;
        this.idAcquirente = idAcquirente;
        this.prezzoTotale = prezzoTotale;
        this.Indirizzo = indirizzo;
        this.metodoPagamento = metodoPagamento;
        this.dataPagamento = dataPagamento;
    }

    public Ordine() {
        this.carrello = new Carrello();
    }

    public String getIdOrdine() {
        return idOrdine;
    }
    public void setIdOrdine(String idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public String getAquirente() {
        return idAcquirente;
    }
    public void setAquirente(String idAcquirente) {
        this.idAcquirente = idAcquirente;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }
    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
}
