package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.filieraAgricola.Dto.DtoPacchettoProdotti;
import unicam.filieraAgricola.Dto.DtoProdotto;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.services.ProdottoService;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/creaProdotto")
    public ResponseEntity<String> CreaProdotto(@RequestBody DtoProdotto dtoProdotto) {
        try{
            prodottoService.CreaProdottoSingolo(dtoProdotto.getNome(), dtoProdotto.getDescrizione(), dtoProdotto.getPrezzo(), dtoProdotto.getQuantita(),dtoProdotto.getIdVenditore());
            return new ResponseEntity<>("Prodotto creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non creato", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/creaPacchetto")
    public ResponseEntity<String> CreaPacchetto(@RequestBody DtoPacchettoProdotti dtoPacchettoProdotti) {
        try {
            prodottoService.CreaPacchetto(dtoPacchettoProdotti.getNome(), dtoPacchettoProdotti.getDescrizione(), dtoPacchettoProdotti.getQuantita(), dtoPacchettoProdotti.getIdVenditore(), dtoPacchettoProdotti.getProdotti());
            return new ResponseEntity<>("Pacchetto creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Pacchetto non creato", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{idProdotto}")
    public ResponseEntity<String> EliminaProdotto(@PathVariable String idProdotto, @RequestParam String idVenditore) {
        try{
            prodottoService.EliminaProdotto(idProdotto, idVenditore);
            return new ResponseEntity<>("Prodotto eliminato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non eliminato", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/visualizza")
    public ResponseEntity<List<Prodotto>> VisualizzaListaProdotti() {
        try {
            return new ResponseEntity<>(prodottoService.VisualizzaListaProdotti(), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cerca")
    public ResponseEntity<Prodotto> CercaProdotto(@RequestParam String idProdotto) {
        try {
            return new ResponseEntity<>(prodottoService.CercaProdotto(idProdotto), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/approvaProdotto")
    public ResponseEntity<String> ApprovaProdotto(@RequestParam String idProdotto, @RequestParam String idCuratore) {
        try{
            prodottoService.ApprovaProdotto(idProdotto, idCuratore);
            return new ResponseEntity<>("Prodotto approvato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Approvazione non avvenuta correttamente", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rifiutaProdotto")
    public ResponseEntity<String> RifiutaProdotto(@RequestParam String idProdotto, @RequestParam String idCuratore, @RequestParam String motivoRifiuto) {
        try{
            prodottoService.RifiutaProdotto(idProdotto, idCuratore, motivoRifiuto);
            return new ResponseEntity<>("Prodotto riutato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Rifiuto non avvenuto correttamente", HttpStatus.BAD_REQUEST);
        }
    }

}
