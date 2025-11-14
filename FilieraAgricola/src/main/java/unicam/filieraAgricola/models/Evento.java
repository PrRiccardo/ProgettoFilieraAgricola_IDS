package unicam.filieraAgricola.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

public class Evento {


     @Id
    private String id;
    private String nome;
    private String descrizione;
    private String luogo;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    @DBRef
    private UtenteLoggato animatore;


    public Evento(String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Evento () {}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public String getLuogo() {return luogo;}
    public void setLuogo(String luogo) {this.luogo = luogo;}

    public LocalDateTime getDataInizio() {return dataInizio;}
    public void setDataInizio(LocalDateTime dataInizio) {this.dataInizio = dataInizio;}

    public LocalDateTime getDataFine() {return dataFine;}
    public void setDataFine(LocalDateTime dataFine) {this.dataFine = dataFine;}

    public UtenteLoggato getAnimatore() {return animatore;}
    public void setAnimatore(UtenteLoggato animatore) {this.animatore = animatore;}

}
