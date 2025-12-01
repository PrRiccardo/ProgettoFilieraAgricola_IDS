package unicam.filieraAgricola.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.EventoRepository;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.ArrayList;
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

        if(!eventoRepository.findByLuogoAndDataFineGreaterThanAndDataInizioLessThan(luogo, dataFine, dataInizio).isEmpty())
            throw new IllegalArgumentException("Luogo già occupato in questo periodo");

        Evento evento = new Evento(nome, descrizione, luogo, dataInizio, dataFine, idAnimatore);
        eventoRepository.save(evento);
        notifyObservers(evento);
    }

    public void eliminaEvento(String idEvento, String idAnimatore){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato animatore = utenteRepository.findById(idAnimatore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(animatore.getRuolo()!= RuoloUtente.ANIMATORE){
            throw new IllegalArgumentException("Impossibile eliminare evento");
        }
        eventoRepository.delete(evento);
    }

    public void modificaEvento(String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine, String idAnimatore,String idEvento ){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato animatore = utenteRepository.findById(idAnimatore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(animatore.getRuolo()!= RuoloUtente.ANIMATORE){
            throw new IllegalArgumentException("Impossibile modificare l'evento");
        }

        evento.setNome(nome);
        evento.setDescrizione(descrizione);
        evento.setLuogo(luogo);
        evento.setDataInizio(dataInizio);
        evento.setDataFine(dataFine);

        eventoRepository.save(evento);

    }

    public List<Evento> visualizzaEventi(){
        return eventoRepository.findByDataInizioAfter(LocalDateTime.now());
    }

    public Evento cercaEvento(String idEvento){
        return eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
    }

    public void aggiungiIscritto(String idEvento, String idUtente){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato acquirente = utenteRepository.findById(idUtente).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        if(acquirente.getRuolo() != RuoloUtente.ACQUIRENTE)
            throw new IllegalArgumentException("Impossibile aggiungere iscritto");

        for(UtenteLoggato u : evento.getUtentiIscritti())
            if(u.getIdUtente().equals(acquirente.getIdUtente()))
                throw new IllegalArgumentException("Utente già iscritto");

        evento.aggiungiIscritto(acquirente);
        eventoRepository.save(evento);
    }

    public void rimuoviIscritto(String idEvento, String idUtente){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        UtenteLoggato acquirente = utenteRepository.findById(idUtente).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        for(UtenteLoggato u : evento.getUtentiIscritti())
            if(u.getIdUtente().equals(acquirente.getIdUtente())) {
                evento.rimuoviIscritto(acquirente);
                eventoRepository.save(evento);
                return;
            }

        throw new IllegalArgumentException("Utente già iscritto");
    }


    private final List<ObserverService> observers = new ArrayList<ObserverService>();

    public void nuovoObserver(ObserverService observer) {
        this.observers.add(observer);
    }

    private void notifyObservers(Evento evento) {
        for (ObserverService observer : observers) {
            observer.notificaNuovoEvento(evento);
        }
    }

}
