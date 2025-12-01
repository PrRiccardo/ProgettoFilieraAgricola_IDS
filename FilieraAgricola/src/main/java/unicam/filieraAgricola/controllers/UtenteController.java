package unicam.filieraAgricola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.filieraAgricola.Dto.DtoUtenteLoggato;
import unicam.filieraAgricola.services.UtenteService;


@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registraUtente")
    public ResponseEntity<String> registraUtente(@RequestBody DtoUtenteLoggato dtoUtenteLoggato){
        try{
            utenteService.Registrazione(dtoUtenteLoggato.getNome(), dtoUtenteLoggato.getCognome(), dtoUtenteLoggato.getEmail(), dtoUtenteLoggato.getPassword(), dtoUtenteLoggato.getTelefono(), dtoUtenteLoggato.getRuolo());
            return new ResponseEntity<>("Registrazione avvenuta con successo", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Registrazione non avvenuta", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        try{
            utenteService.Login(email, password);
            return new ResponseEntity<>("Login avvenuto con successo", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Login non avvenuto", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminaProfilo")
    public ResponseEntity<String> eliminaProfilo(@RequestParam String idUtente, @RequestParam String password){
        try{
            utenteService.eliminaProfilo(idUtente, password);
            return new ResponseEntity<>("Profilo eliminato", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Profilo non eliminato", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/modificaProfilo")
    public ResponseEntity<String> modificaProfilo(@RequestBody DtoUtenteLoggato dtoUtenteLoggato){
        try{
            utenteService.ModificaProfilo(dtoUtenteLoggato.getNome(), dtoUtenteLoggato.getCognome(), dtoUtenteLoggato.getEmail(), dtoUtenteLoggato.getPassword(), dtoUtenteLoggato.getTelefono(), dtoUtenteLoggato.getRuolo());
            return new ResponseEntity<>("Modifica avvenuta con successo", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Modifica non avvenuta", HttpStatus.BAD_REQUEST);
        }
    }

}
