package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.filieraAgricola.models.Ordine;
import unicam.filieraAgricola.services.OrdineService;

import java.util.List;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/creaOrdine")
    public ResponseEntity<String> CreaOrdine(@RequestParam String idCarrello, @RequestParam String idUtente, @RequestParam String indirizzo, @RequestParam String metodoDiPagamento) {
        try{
            ordineService.creaOrdine(idCarrello, idUtente, indirizzo, metodoDiPagamento);
            return new ResponseEntity<>("Ordine creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Ordine non creato", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{idOrdine}")
    public ResponseEntity<String> EliminaOrdine(@PathVariable String idOrdine, @RequestParam String idUtente) {
        try{
            ordineService.eliminaOrdine(idOrdine, idUtente);
            return new ResponseEntity<>("Ordine eliminato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Ordine non eliminato", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("listaOrdiniAcquirente")
    public ResponseEntity<List<Ordine>> visualizzaOrdiniAcquirente(@RequestParam String idUtente) {
        try{
            return new ResponseEntity<>(ordineService.visualizzaOrdiniAcquierente(idUtente), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
