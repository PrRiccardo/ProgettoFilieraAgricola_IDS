package unicam.filieraAgricola.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Entity
@DiscriminatorValue("PACCHETTO")
public class PacchettoProdotti extends Prodotto {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pacchettoProdotti",
            joinColumns = @JoinColumn(name = "idPacchetto"),
            inverseJoinColumns = @JoinColumn(name = "idProdotto")
    )
    private List<Prodotto> prodotti;

    public PacchettoProdotti(String nome, String descrizione, int quantita, String idVenditore, List<Prodotto> prodotti) {
        super (nome, descrizione, 0, quantita, idVenditore);
        this.prodotti = prodotti;
        this.setPrezzo(calcolaPrezzoTotale());
    }

    public PacchettoProdotti() {}

    public double calcolaPrezzoTotale(){
        double temp = 0;
        for(Prodotto prodotto : prodotti){
            temp+=prodotto.getPrezzo();
        }
        return temp;
    }

}
