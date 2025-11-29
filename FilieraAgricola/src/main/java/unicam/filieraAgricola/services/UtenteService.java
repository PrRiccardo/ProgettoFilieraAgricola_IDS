package unicam.filieraAgricola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.UtenteLoggato;
import unicam.filieraAgricola.repositories.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public void Registrazione(String nome, String cognome, String email, String password, String telefono, RuoloUtente ruolo) {
        UtenteLoggato utente = new UtenteLoggato(nome, cognome, email, password, telefono, ruolo);
        if(!utenteRepository.findByEmail(email).isEmpty())
            throw new IllegalArgumentException("Utente già esistente");

        utenteRepository.save(utente);
    }

    public void Login(String email, String password) {
        utenteRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
    }

}
