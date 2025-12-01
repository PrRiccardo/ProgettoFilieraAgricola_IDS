package unicam.filieraAgricola.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

    @Id
    private String id;

    private String idUtente;

    private List<ComponenteCarrello> carrello = new ArrayList<>();

    public Carrello(String idUtente) {
        this.idUtente = idUtente;
    }
    public Carrello() {}

    public double getPrezzoTotale() {
        double prezzoTotale = 0;
        for(ComponenteCarrello cc : carrello) {
            prezzoTotale += cc.getProdotto().getPrezzo() * cc.getQuantita();
        }
        return prezzoTotale;
    }

    public int getQuantitaCarrello() {
        int quantitaTotale = 0;
        for(ComponenteCarrello cc : carrello) {
            quantitaTotale +=  cc.getQuantita();
        }
        return quantitaTotale;

    }

    public void svoutaCarrello(){
        this.carrello.clear();
    }

    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
        ComponenteCarrello componente = new ComponenteCarrello(prodotto, quantita);
        carrello.add(componente);
    }

    public void rimuoviProdotto(String idProdotto)
    {
        carrello.removeIf(c -> c.getProdotto().getId().equals(idProdotto));
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getIdUtente() {return idUtente;}
    public void setIdUtente(String idUtente) {this.idUtente = idUtente;}

    public List<ComponenteCarrello> getCarrello() {return carrello;}
    public void setCarrello(List<ComponenteCarrello> carrello) {this.carrello = carrello;}

    public static class ComponenteCarrello{

        private Prodotto prodotto;
        private int quantita;


        public ComponenteCarrello(Prodotto prodotto, int quantita ) {
            this.prodotto = prodotto;
            this.quantita = quantita;

        }

        public ComponenteCarrello() {}


        public Prodotto getProdotto() {return prodotto;}
        public void setProdotto(Prodotto prodotto) {this.prodotto = prodotto;}

        public int getQuantita() {return quantita;}
        public void setQuantita(int quantita) {this.quantita = quantita;}


    }
}
