package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.StatoProdotto;
import unicam.filieraAgricola.models.UtenteLoggato;
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
        if(venditore.get().getRuolo() != RuoloUtente.VENDITORE)
            throw new IllegalArgumentException("Impossibile caricare il prodotto!");
        if(!prodottoRepository.findByNomeAndPrezzoAndIdVenditore(nome, prezzo, idVenditore).isEmpty())
            throw new IllegalArgumentException("Prodotto esistente!");

        Prodotto prodotto = new Prodotto(nome, descrizione, prezzo, quantita, idVenditore);
        prodottoRepository.save(prodotto);
    }

    public void ApprovaProdotto(String idProdotto, String idCuratore) {
        Optional<UtenteLoggato> curatore = utenteRepository.findById(idCuratore);
        if(curatore.get().getRuolo() != RuoloUtente.CURATORE)
            throw new IllegalArgumentException("Impossibile verificare il prodotto!");
        if(!utenteRepository.existsById(idProdotto))
            throw new IllegalArgumentException("Prodotto non esistente!");

        Prodotto prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setStatoProdotto(StatoProdotto.APPROVATO);
        prodottoRepository.save(prodotto);
    }

    public void RifiutaProdotto(String idProdotto, String idCuratore, String motivoRifiuto) {
        Optional<UtenteLoggato> curatore = utenteRepository.findById(idCuratore);
        if(curatore.get().getRuolo() != RuoloUtente.CURATORE)
        throw new IllegalArgumentException("Impossibile verificare il prodotto!");
        if(!utenteRepository.existsById(idProdotto))
            throw new IllegalArgumentException("Prodotto non esistente!");

        Prodotto prodotto = prodottoRepository.findById(idProdotto).get();
        prodotto.setStatoProdotto(StatoProdotto.RIFIUTATO);
        prodotto.setMotivoRifiuto(motivoRifiuto);
        prodottoRepository.save(prodotto);
    }
}
