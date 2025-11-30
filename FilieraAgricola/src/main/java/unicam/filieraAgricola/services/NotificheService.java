package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.List;

@Service
public class NotificheService implements ObserverService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public void notificaNuovoEvento(Evento evento) {

        List<UtenteLoggato> acquirenti = utenteRepository.findByRuolo(RuoloUtente.ACQUIRENTE);

        for (UtenteLoggato utente : acquirenti) {
            //Notifica acquirenti;
        }

    }
}