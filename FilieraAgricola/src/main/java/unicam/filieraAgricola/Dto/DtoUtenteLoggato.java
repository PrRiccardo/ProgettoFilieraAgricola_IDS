package unicam.filieraAgricola.Dto;

import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.UtenteLoggato;

public class DtoUtenteLoggato {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String telefono;

    private RuoloUtente ruolo;

    public DtoUtenteLoggato() {}

    public DtoUtenteLoggato(String nome, String cognome, String email,String password, String telefono, RuoloUtente ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.ruolo = ruolo;

    }

    public DtoUtenteLoggato (UtenteLoggato utenteLoggato){
        this.nome = utenteLoggato.getNome();
        this.cognome = utenteLoggato.getCognome();
        this.email = utenteLoggato.getEmail();
        this.password = utenteLoggato.getPassword();
        this.telefono = utenteLoggato.getTelefono();
        this.ruolo = utenteLoggato.getRuolo();

    }

}
