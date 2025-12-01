package unicam.filieraAgricola.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {


     @Id
    private String id;

    private String nome;
    private String descrizione;
    private String luogo;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String idAnimatore;

    @DBRef
    private List<UtenteLoggato> utentiIscritti;


    public Evento(String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine,String idAnimatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.idAnimatore = idAnimatore;
        this.utentiIscritti = new ArrayList<>();
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

    public String getAnimatore() {return idAnimatore;}
    public void setAnimatore(String animatore) {this.idAnimatore = animatore;}

    public List<UtenteLoggato> getUtentiIscritti() {return utentiIscritti;}

    public void aggiungiIscritto(UtenteLoggato acquirente) {
        this.utentiIscritti.add(acquirente);
    }

    public void rimuoviIscritto(UtenteLoggato acquirente) {
        for(int i = 0; i < utentiIscritti.size(); i++)
            if(utentiIscritti.get(i).getIdUtente().equals(acquirente.getIdUtente())) {
                utentiIscritti.remove(i);
                return;
            }
    }


}
