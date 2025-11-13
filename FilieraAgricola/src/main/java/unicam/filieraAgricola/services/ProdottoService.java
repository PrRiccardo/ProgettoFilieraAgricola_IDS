package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.ProdottoRepository;
import unicam.filieraAgricola.repositories.UtenteRepository;

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

        Prodotto prodotto = new Prodotto(nome, descrizione, prezzo, quantita, idVenditore);
        prodottoRepository.save(prodotto);
    }

}
