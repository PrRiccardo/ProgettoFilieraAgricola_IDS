package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.*;
import unicam.filieraAgricola.repositories.ProdottoRepository;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void CreaProdotto(String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
        Optional<UtenteLoggato> venditore = utenteRepository.findById(idVenditore);
        //if(venditore.get().getRuolo() != RuoloUtente.VENDITORE)
        //    throw new IllegalArgumentException("Impossibile caricare il prodotto!");
        if(!prodottoRepository.findByNomeAndPrezzoAndIdVenditore(nome, prezzo, idVenditore).isEmpty())
            throw new IllegalArgumentException("Prodotto esistente!");

        ProdottoSingolo prodotto = new ProdottoSingolo(nome, descrizione, prezzo, quantita, idVenditore);
        prodottoRepository.save(prodotto);
    }

    public void EliminaProdotto(String idProdotto, String idVenditore) {
        Optional<ProdottoSingolo> prodotto = prodottoRepository.findById(idProdotto);
        if(prodotto.isEmpty())
            throw new IllegalArgumentException("Prodotto non trovato!");
        Optional<UtenteLoggato> venditore = utenteRepository.findById(idVenditore);
        if(venditore.isEmpty())
            throw new IllegalArgumentException("Venditore non trovato!");
        if(venditore.get().getRuolo() != RuoloUtente.VENDITORE)
            throw new IllegalArgumentException("Impossibile eliminare il prodotto!");

        prodottoRepository.delete(prodotto.get());
    }

    public List<ProdottoSingolo> VisualizzaListaProdotti() {
        List<ProdottoSingolo> prodotti = prodottoRepository.findByStatoProdotto(StatoProdotto.APPROVATO);
        if(prodotti.isEmpty())
            throw new IllegalArgumentException("Prodotti vuoti!");
        return prodotti;
    }

    public ProdottoSingolo CercaProdotto(String idProdotto) {
        Optional<ProdottoSingolo> prodotto = prodottoRepository.findById(idProdotto);
        if(prodotto.isEmpty())
            throw new IllegalArgumentException("Prodotto non trovato!");
        return prodotto.get();
    }

    public void ApprovaProdotto(String idProdotto, String idCuratore) {
        Optional<UtenteLoggato> curatore = utenteRepository.findById(idCuratore);
        if(curatore.get().getRuolo() != RuoloUtente.CURATORE)
            throw new IllegalArgumentException("Impossibile verificare il prodotto!");
        if(!utenteRepository.existsById(idProdotto))
            throw new IllegalArgumentException("Prodotto non esistente!");

        ProdottoSingolo prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setStatoProdotto(StatoProdotto.APPROVATO);
        prodottoRepository.save(prodotto);
    }

    public void RifiutaProdotto(String idProdotto, String idCuratore, String motivoRifiuto) {
        Optional<UtenteLoggato> curatore = utenteRepository.findById(idCuratore);
        if(curatore.get().getRuolo() != RuoloUtente.CURATORE)
        throw new IllegalArgumentException("Impossibile verificare il prodotto!");
        if(!utenteRepository.existsById(idProdotto))
            throw new IllegalArgumentException("Prodotto non esistente!");

        ProdottoSingolo prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setStatoProdotto(StatoProdotto.RIFIUTATO);
        prodotto.setMotivoRifiuto(motivoRifiuto);
        prodottoRepository.save(prodotto);
    }
}
