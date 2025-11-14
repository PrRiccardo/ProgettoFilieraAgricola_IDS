package unicam.filieraAgricola.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.EventoRepository;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void creaEvento(String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine, String idAnimatore) {

        Optional<UtenteLoggato> animatore = utenteRepository.findById(idAnimatore);
        if(animatore.get().getRuolo()!= RuoloUtente.ANIMATORE){
            throw new IllegalArgumentException("Impossibile creare evento");
        }

        Evento evento = new Evento(nome, descrizione, luogo, dataInizio, dataFine);
        eventoRepository.save(evento);
    }


}
