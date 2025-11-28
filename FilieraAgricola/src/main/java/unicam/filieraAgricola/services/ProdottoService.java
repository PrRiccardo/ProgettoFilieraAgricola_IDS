package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.*;
import unicam.filieraAgricola.repositories.ProdottoRepository;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void CreaProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
        UtenteLoggato venditore = utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(venditore.getRuolo() != RuoloUtente.VENDITORE)
            throw new IllegalArgumentException("Impossibile creare il prodotto!");
        if(!prodottoRepository.findProdottoByNomeAndPrezzoAndIdVenditore(nome, prezzo, idVenditore).isEmpty())
            throw new IllegalArgumentException("Prodotto esistente!");

        ProdottoSingolo prodotto = new ProdottoSingolo(nome, descrizione, prezzo, quantita, idVenditore);
        prodottoRepository.save(prodotto);
    }

    public void CreaPacchetto(String nome, String descrizione, int quantita, String idVenditore, List<Prodotto> prodotti) {
        UtenteLoggato distributore = utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(distributore.getRuolo() != RuoloUtente.DISTRIBUTORE)
            throw new IllegalArgumentException("Impossibile creare il pacchetto!");

        for(Prodotto p : prodotti) {
            if(p instanceof ProdottoSingolo prodottoSingolo)
                if(prodottoSingolo.getStatoProdotto() != StatoProdotto.APPROVATO)
                    throw new IllegalArgumentException("Impossibile creare il pacchetto!");
        }

        PacchettoProdotti pacchetto = new PacchettoProdotti(nome, descrizione, quantita, idVenditore, prodotti);
        if(!prodottoRepository.findPacchettoByNomeAndPrezzoAndIdVenditore(nome, pacchetto.getPrezzo(), idVenditore).isEmpty())
            throw new IllegalArgumentException("Prodotto esistente!");
        prodottoRepository.save(pacchetto);
    }

    public void EliminaProdotto(String idProdotto, String idVenditore) {
        Prodotto prodotto = prodottoRepository.findById(idProdotto).orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        UtenteLoggato venditore = utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(!prodotto.getIdVenditore().equals(idVenditore))
            throw new IllegalArgumentException("Impossibile eliminare il prodotto!");
        prodottoRepository.delete(prodotto);
    }

    public List<Prodotto> VisualizzaListaProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        for(Prodotto p : prodotti) {
            if(p instanceof ProdottoSingolo prodottoSingolo) {
                if(prodottoSingolo.getStatoProdotto() != StatoProdotto.APPROVATO)
                    prodotti.remove(prodottoSingolo);
            }
        }
        return prodotti;
    }

    public Prodotto CercaProdotto(String idProdotto) {
        return prodottoRepository.findById(idProdotto).
                orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
    }

    public void ApprovaProdotto(String idProdotto, String idCuratore) {
        UtenteLoggato curatore = utenteRepository.findById(idCuratore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(curatore.getRuolo() != RuoloUtente.CURATORE)
            throw new IllegalArgumentException("Impossibile verificare il prodotto!");

        ProdottoSingolo prodotto = prodottoRepository.findProdottoById(idProdotto).
                orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        prodotto.setStatoProdotto(StatoProdotto.APPROVATO);
        prodottoRepository.save(prodotto);
    }

    public void RifiutaProdotto(String idProdotto, String idCuratore, String motivoRifiuto) {
        UtenteLoggato curatore = utenteRepository.findById(idCuratore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(curatore.getRuolo() != RuoloUtente.CURATORE)
            throw new IllegalArgumentException("Impossibile verificare il prodotto!");

        ProdottoSingolo prodotto = prodottoRepository.findProdottoById(idProdotto).
                orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        prodotto.setStatoProdotto(StatoProdotto.RIFIUTATO);
        prodotto.setMotivoRifiuto(motivoRifiuto);
        prodottoRepository.save(prodotto);
    }
}
