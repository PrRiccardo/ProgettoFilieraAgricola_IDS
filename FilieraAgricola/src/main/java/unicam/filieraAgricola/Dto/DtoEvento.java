package unicam.filieraAgricola.Dto;

import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.models.UtenteLoggato;

import java.time.LocalDateTime;

public class DtoEvento {


    private String nome;
    private String descrizione;
    private String luogo;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String idAnimatore;

    public DtoEvento() {}

    public DtoEvento(String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine, String idAnimatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.idAnimatore = idAnimatore;

    }

    public DtoEvento(Evento evento) {
        this.nome = evento.getNome();
        this.descrizione = evento.getDescrizione();
        this.luogo = evento.getLuogo();
        this.dataInizio = evento.getDataInizio();
        this.dataFine = evento.getDataFine();
        this.idAnimatore = evento.getAnimatore();
    }

    public String getNome() {return this.nome;}
    public String getDescrizione() {return this.descrizione;}
    public String getLuogo() {return this.luogo;}
    public LocalDateTime getDataInizio() {return this.dataInizio;}
    public LocalDateTime getDataFine() {return this.dataFine;}
    public String getIdAnimatore() {return this.idAnimatore;}



}
