package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unicam.filieraAgricola.models.Evento;
import unicam.filieraAgricola.services.EventoService;

import java.time.LocalDateTime;

@RestController

public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/pubblicaEvento")
    public ResponseEntity<String> pubblicaEvento(@RequestBody String nome, String descrizione, String luogo, LocalDateTime dataInizio, LocalDateTime dataFine,  String idAnimatore){
        try {
            eventoService.creaEvento(nome,descrizione,luogo,dataInizio,dataFine,idAnimatore);
            return new ResponseEntity<>("Prodotto creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non creato", HttpStatus.BAD_REQUEST);
        }
    }
}
