package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.filieraAgricola.services.CarrelloService;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @PostMapping("/aggiungiProdotto")
    public ResponseEntity<String> aggiungiProdotto(@RequestParam String idProdotto, @RequestParam int quantita, @RequestParam String idCarrello) {
        try{
            carrelloService.aggiungiProdotto(idProdotto, quantita, idCarrello);
            return new ResponseEntity<>("Prodotto aggiunto", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non aggiunto", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{idCarrello}")
    public ResponseEntity<String> svuotaCarrello(@PathVariable String idCarrello,@RequestParam String idUtente) {
        try{
            carrelloService.svuotaCarrello(idCarrello, idUtente);
            return new ResponseEntity<>("Carrello svuotato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non svuotato", HttpStatus.BAD_REQUEST);
        }
    }
}
