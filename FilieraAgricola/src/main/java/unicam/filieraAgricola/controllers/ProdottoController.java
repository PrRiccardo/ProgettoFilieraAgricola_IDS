package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.ProdottoSingolo;
import unicam.filieraAgricola.services.ProdottoService;

import java.util.List;

@RestController
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/crea")
    public ResponseEntity<String> CreaProdotto(@RequestBody String nome, String descrizione, double prezzo, int quantita, String idVenditore) {
        try{
            prodottoService.CreaProdotto(nome, descrizione, prezzo,  quantita, idVenditore);
            return new ResponseEntity<>("Prodotto creato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Prodotto non creato", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<List<ProdottoSingolo>> VisualizzaListaProdotti() {
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

    @PostMapping("/approva")
    public ResponseEntity<String> ApprovaProdotto(@RequestBody String idProdotto, String idCuratore) {
        try{
            prodottoService.ApprovaProdotto(idProdotto, idCuratore);
            return new ResponseEntity<>("Prodotto approvato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Approvazione non avvenuta correttamente", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rifiuta")
    public ResponseEntity<String> RifiutaProdotto(@RequestBody String idProdotto, String idCuratore, String motivoRifiuto) {
        try{
            prodottoService.RifiutaProdotto(idProdotto, idCuratore, motivoRifiuto);
            return new ResponseEntity<>("Prodotto riutato", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Rifiuto non avvenuto correttamente", HttpStatus.BAD_REQUEST);
        }
    }

}
