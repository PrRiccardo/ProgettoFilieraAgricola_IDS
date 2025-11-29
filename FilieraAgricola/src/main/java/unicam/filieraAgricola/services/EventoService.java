package unicam.filieraAgricola.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.EventoRepository;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void creaEvento(String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine, String idAnimatore) {

        UtenteLoggato animatore = utenteRepository.findById(idAnimatore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(animatore.getRuolo()!= RuoloUtente.ANIMATORE){
            throw new IllegalArgumentException("Impossibile creare evento");
        }

        Evento evento = new Evento(nome, descrizione, luogo, dataInizio, dataFine,idAnimatore);
        eventoRepository.save(evento);
    }

    public void eliminaEvento(String idEvento, String idAnimatore){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato animatore = utenteRepository.findById(idAnimatore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(animatore.getRuolo()!= RuoloUtente.ANIMATORE){
            throw new IllegalArgumentException("Impossibile eliminare evento");
        }
        eventoRepository.delete(evento);
    }

    public List<Evento> visualizzaEventi(){
        return eventoRepository.findByDataInizioAfterAndDataFineBefore(LocalDateTime.now());
    }

    public Evento cercaEvento(String idEvento){
        return eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
    }

    public void aggiungiIscritto(String idEvento, String idUtente){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato acquirente = utenteRepository.findById(idUtente).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(acquirente.getRuolo() != RuoloUtente.ACQUIRENTE || evento.getUtentiIscritti().contains(acquirente))
            throw new IllegalArgumentException("Impossibile aggiungere iscritto");
        evento.aggiungiIscritto(acquirente);
        eventoRepository.save(evento);
    }

    public void rimuoviIscritto(String idEvento, String idUtente){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato acquirente = utenteRepository.findById(idUtente).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(acquirente.getRuolo() != RuoloUtente.ACQUIRENTE || !evento.getUtentiIscritti().contains(acquirente))
            throw new IllegalArgumentException("Impossibile rimuovere iscritto");
        evento.rimuoviIscritto(acquirente);
        eventoRepository.save(evento);
    }
}
