package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.*;
import unicam.filieraAgricola.repositories.ProdottoRepository;
import unicam.filieraAgricola.repositories.UtenteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Prodotto> duplicato = prodottoRepository.findByNomeAndIdVenditore(nome, idVenditore);
        if(duplicato.isPresent())
            throw new IllegalArgumentException("Prodotto esistente!");

        ProdottoSingolo prodotto = new ProdottoSingolo(nome, descrizione, prezzo, quantita, idVenditore);
        prodottoRepository.save(prodotto);
    }

    public void CreaPacchetto(String nome, String descrizione, int quantita, String idVenditore, List<String> idProdotti) {
        UtenteLoggato distributore = utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(distributore.getRuolo() != RuoloUtente.DISTRIBUTORE)
            throw new IllegalArgumentException("Impossibile creare il pacchetto!");

        List<Prodotto> prodotti = new ArrayList<>();
        for (String id : idProdotti) {
            Prodotto p = prodottoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Prodotto componente non trovato con ID: " + id));
            prodotti.add(p);
        }

        for(Prodotto p : prodotti) {
            if(p instanceof ProdottoSingolo prodottoSingolo)
                if(prodottoSingolo.getStatoProdotto() != StatoProdotto.APPROVATO)
                    throw new IllegalArgumentException("Impossibile creare il pacchetto!");
        }

        PacchettoProdotti pacchetto = new PacchettoProdotti(nome, descrizione, quantita, idVenditore, prodotti);


        Optional<Prodotto> duplicato = prodottoRepository.findByNomeAndIdVenditore(nome, idVenditore);
        if(duplicato.isPresent())
            throw new IllegalArgumentException("Prodotto esistente!");

        prodottoRepository.save(pacchetto);
    }

    public void ModificaProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, String idVenditore,String idProdotto) {
        ProdottoSingolo prodottoSingolo = findProdottoSingoloById(idProdotto);
        utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(!prodottoSingolo.getIdVenditore().equals(idVenditore))
            throw new IllegalArgumentException("Impossibile modificare il prodotto!");

        prodottoSingolo.setNome(nome);
        prodottoSingolo.setDescrizione(descrizione);
        prodottoSingolo.setPrezzo(prezzo);
        prodottoSingolo.setQuantita(quantita);

        prodottoRepository.save(prodottoSingolo);

    }
    public void ModificaPacchetto(String nome, String descrizione,int quantita, String idVenditore, String idProdotto, List<String> idProdotti) {
        PacchettoProdotti pacchetto = findPacchettoById(idProdotto);
        utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(!pacchetto.getIdVenditore().equals(idVenditore))
            throw new IllegalArgumentException("Impossibile modificare il pacchetto!");

        List<Prodotto> prodotti = new ArrayList<>();
        for (String id : idProdotti) {
            Prodotto p = prodottoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Prodotto componente non trovato con ID: " + id));
            prodotti.add(p);
        }

        pacchetto.setNome(nome);
        pacchetto.setDescrizione(descrizione);
        pacchetto.setQuantita(quantita);
        pacchetto.setProdotti(prodotti);

        prodottoRepository.save(pacchetto);

    }

    public void EliminaProdotto(String idProdotto, String idVenditore) {
        Prodotto prodotto = prodottoRepository.findById(idProdotto).orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        utenteRepository.findById(idVenditore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(!prodotto.getIdVenditore().equals(idVenditore))
            throw new IllegalArgumentException("Impossibile eliminare il prodotto!");
        prodottoRepository.delete(prodotto);
    }

    public List<Prodotto> VisualizzaListaProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        List<Prodotto> prodottiFiltrati = new ArrayList<Prodotto>();
        for(Prodotto p : prodotti) {
            if(p instanceof ProdottoSingolo prodottoSingolo) {
                if(prodottoSingolo.getStatoProdotto() == StatoProdotto.APPROVATO)
                    prodottiFiltrati.add(prodottoSingolo);
            }
            else
                prodottiFiltrati.add(p);
        }
        return prodottiFiltrati;
    }

    public Prodotto CercaProdotto(String idProdotto) {
        return prodottoRepository.findById(idProdotto).
                orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
    }

    public void ApprovaProdotto(String idProdotto, String idCuratore) {
        UtenteLoggato curatore = utenteRepository.findById(idCuratore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(curatore.getRuolo() != RuoloUtente.CURATORE)
            throw new IllegalArgumentException("Impossibile verificare il prodotto!");

        ProdottoSingolo prodotto = findProdottoSingoloById(idProdotto);
        if(prodotto.getStatoProdotto() != StatoProdotto.IN_ATTESA_APPROVAZIONE)
            throw new IllegalArgumentException("Prodotto non in attesa di approvazione");

        prodotto.setStatoProdotto(StatoProdotto.APPROVATO);
        prodottoRepository.save(prodotto);
    }

    public void RifiutaProdotto(String idProdotto, String idCuratore, String motivoRifiuto) {
        UtenteLoggato curatore = utenteRepository.findById(idCuratore).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        if(curatore.getRuolo() != RuoloUtente.CURATORE)
            throw new IllegalArgumentException("Impossibile verificare il prodotto!");

        ProdottoSingolo prodotto = findProdottoSingoloById(idProdotto);
        if(prodotto.getStatoProdotto() != StatoProdotto.IN_ATTESA_APPROVAZIONE)
            throw new IllegalArgumentException("Prodotto non in attesa di approvazione");

        prodotto.setStatoProdotto(StatoProdotto.RIFIUTATO);
        prodotto.setMotivoRifiuto(motivoRifiuto);
        prodottoRepository.save(prodotto);
    }

    public ProdottoSingolo findProdottoSingoloById(String idProdotto) {
        Prodotto prodotto = prodottoRepository.findById(idProdotto).orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
        if(prodotto instanceof ProdottoSingolo prodottoSingolo)
            return prodottoSingolo;
        throw new IllegalArgumentException("Impossibile verificare il prodotto!");
    }

    public PacchettoProdotti findPacchettoById(String idProdotto) {
        Prodotto prodotto = prodottoRepository.findById(idProdotto).orElseThrow(() -> new IllegalArgumentException("Pacchetto non trovato"));
        if(prodotto instanceof PacchettoProdotti pacchettoProdotti)
            return pacchettoProdotti;
        throw new IllegalArgumentException("Impossibile verificare il pacchetto!");
    }

}
