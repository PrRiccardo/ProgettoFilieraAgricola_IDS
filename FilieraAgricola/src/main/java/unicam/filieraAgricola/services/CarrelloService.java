package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Carrello;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.ProdottoSingolo;
import unicam.filieraAgricola.models.StatoProdotto;
import unicam.filieraAgricola.repositories.CarrelloRepository;
import unicam.filieraAgricola.repositories.ProdottoRepository;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    public void aggiungiProdotto(String idProdotto, int quantita, String idCarrello, String idUtente) {
        Carrello carrello = carrelloRepository.findById(idCarrello).orElseThrow(() -> new IllegalArgumentException("Carrello non trovato"));
        Prodotto prodotto = prodottoRepository.findById(idProdotto).orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        if(!carrello.getUtente().getIdUtente().equals(idUtente))
            throw  new IllegalArgumentException("Impossibile aggiungere prodotto");
        if(prodotto instanceof ProdottoSingolo prodottoSingolo)
            if(!prodottoSingolo.getStatoProdotto().equals(StatoProdotto.APPROVATO))
                throw new IllegalArgumentException("Prodotto non approvato");

        carrello.aggiungiProdotto(prodotto, quantita);
        carrelloRepository.save(carrello);
    }

    public void rimuoviProdotto(String idProdotto, String idCarrello, String idUtente) {
        Carrello carrello = carrelloRepository.findById(idCarrello).orElseThrow(() -> new IllegalArgumentException("Carrello non trovato"));
        prodottoRepository.findById(idProdotto).orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        if(!carrello.getUtente().getIdUtente().equals(idUtente))
            throw  new IllegalArgumentException("Impossibile rimuovere prodotto");

        carrello.rimuoviProdotto(idProdotto);
        carrelloRepository.save(carrello);
    }

    public void svuotaCarrello(String idCarrello, String idUtente) {
        Carrello carrello = carrelloRepository.findById(idCarrello).orElseThrow(() -> new IllegalArgumentException("Carrello non trovato"));
        if(!carrello.getUtente().getIdUtente().equals(idUtente))
            throw new IllegalArgumentException("Impossibile svuotare il carrello");

        carrello.svoutaCarrello();
        carrelloRepository.save(carrello);
    }
}
