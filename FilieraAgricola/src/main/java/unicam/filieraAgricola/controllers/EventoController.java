package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.filieraAgricola.Dto.DtoEvento;
import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.services.EventoService;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/pubblicaEvento")
    public ResponseEntity<String> pubblicaEvento(@RequestBody DtoEvento dtoEvento){
        try {
            eventoService.creaEvento(dtoEvento.getNome(), dtoEvento.getDescrizione(), dtoEvento.getLuogo(), dtoEvento.getDataInizio(),dtoEvento.getDataFine(),dtoEvento.getAnimatore());
            return new ResponseEntity<>("Prodotto creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non creato", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{idEvento}")
    public ResponseEntity<String> eliminaEvento(@PathVariable  String idEvento, @RequestParam String idAnimatore){
        try{
            eventoService.eliminaEvento(idEvento,idAnimatore);
            return new ResponseEntity<>("Prodotto eliminato con successo", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Evento non eliminato correttamente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/visualizzaEventi")
    public ResponseEntity<List<Evento>> visualizzaEventi(){
        try{
            return new ResponseEntity<>(eventoService.visualizzaEventi(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cercaEvento")
    public ResponseEntity<Evento> cercaEvento(@RequestParam String idEvento){
        try{
            return new ResponseEntity<>(eventoService.cercaEvento(idEvento), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/aggiungiIscritto")
    public ResponseEntity<String> aggiungiIscritto(@RequestBody String idEvento, @RequestParam String idAcquirente){
        try{
            eventoService.aggiungiIscritto(idEvento,idAcquirente);
            return new ResponseEntity<>("Utente iscritto con successo", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Utente non iscritto", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/rimuoviIscritto")
    public ResponseEntity<String> rimuoviIscritto(@RequestBody String idEvento, @RequestParam String idAcquirente){
        try{
            eventoService.rimuoviIscritto(idEvento,idAcquirente);
            return new ResponseEntity<>("Utente disiscritto con successo", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Utente non disiscritto", HttpStatus.BAD_REQUEST);
        }
    }
}
