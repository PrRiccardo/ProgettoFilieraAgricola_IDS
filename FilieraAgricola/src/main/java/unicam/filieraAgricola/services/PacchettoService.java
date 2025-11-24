package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.*;
import unicam.filieraAgricola.repositories.PacchettoRepository;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacchettoService {

    @Autowired
    private PacchettoRepository pacchettoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void creaPacchetto(String nome, String descrizione, int quantita, String idDistributore, List<Prodotto> prodotti) {
        Optional<UtenteLoggato> distributore = utenteRepository.findById(idDistributore);
        if(distributore.get().getRuolo() != RuoloUtente.DISTRIBUTORE)
            throw new IllegalArgumentException("Impossibile creare il pacchetto!");
        for(Prodotto p : prodotti) {
            if(p instanceof ProdottoSingolo prodottoSingolo)
                if(prodottoSingolo.getStatoProdotto() != StatoProdotto.APPROVATO)
                    throw new IllegalArgumentException("Impossibile creare il pacchetto!");
        }
        PacchettoProdotti pacchetto = new PacchettoProdotti(nome, descrizione, quantita, idDistributore, prodotti);
        List<PacchettoProdotti> temp = pacchettoRepository.findByNomeAndPrezzoTotaleAndIdDistributore(nome, pacchetto.getPrezzo(), idDistributore);
        if(!temp.isEmpty())
            if(temp.getFirst().equals(pacchetto))
                throw new IllegalArgumentException("Pacchetto esistente!");
        pacchettoRepository.save(pacchetto);
    }
}
