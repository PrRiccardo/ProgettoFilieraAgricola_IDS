package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Carrello;
import unicam.filieraAgricola.models.Ordine;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.CarrelloRepository;
import unicam.filieraAgricola.repositories.OrdineRepository;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private CarrelloService carrelloService;

    public void creaOrdine(String idCarrello, String idAcquirente, String indirizzo, String metodoPagamento) {
        Carrello carrello = carrelloRepository.findById(idCarrello).orElseThrow(() -> new IllegalArgumentException("Carrello non esistente"));
        if(carrello.getCarrello().isEmpty())
            throw new IllegalArgumentException("Carrello vuoto");

        if(utenteRepository.findById(idAcquirente).isEmpty())
            throw new IllegalArgumentException("Utente non trovato");

        Ordine ordine = new Ordine(carrello, idAcquirente, carrello.getPrezzoTotale(), indirizzo, metodoPagamento);
        carrelloService.svuotaCarrello(carrello.getId(), idAcquirente);
        ordineRepository.save(ordine);
    }

    public void eliminaOrdine(String idUtente, String idOrdine) {
        UtenteLoggato utente = utenteRepository.findById(idUtente).orElseThrow(() -> new IllegalArgumentException("Utente non esistente"));
        Ordine ordine = ordineRepository.findById(idOrdine).orElseThrow(() -> new IllegalArgumentException("Ordine non esistente"));
        if(!utente.getIdUtente().equals(ordine.getAquirente()))
            throw new IllegalArgumentException("Impossibile eliminare l'ordine");

        ordineRepository.delete(ordine);
    }

    public List<Ordine> visualizzaOrdiniAcquierente(String idUtente) {
        utenteRepository.findById(idUtente).orElseThrow(() -> new IllegalArgumentException("Utente non esistente"));
        return ordineRepository.findByIdAcquirente(idUtente);
    }

}
