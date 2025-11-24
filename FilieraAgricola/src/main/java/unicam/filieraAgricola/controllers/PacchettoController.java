package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unicam.filieraAgricola.models.PacchettoProdotti;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.services.PacchettoService;
import unicam.filieraAgricola.services.ProdottoService;

import java.util.List;

@RestController
public class PacchettoController {

    @Autowired
    private PacchettoService pacchettoService;

    @PostMapping("/creaPacchetto")
    public ResponseEntity<String> creaPacchetto(@RequestBody String nome, String descrizione, int quantita, String idVenditore, List<Prodotto> prodotti) {
        try {
            pacchettoService.creaPacchetto(nome, descrizione, quantita, idVenditore, prodotti);
            return new ResponseEntity<>("Pacchetto creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Pacchetto non creato", HttpStatus.BAD_REQUEST);
        }
    }
}
