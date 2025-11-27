package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.PacchettoProdotti;
import unicam.filieraAgricola.models.Prodotto;
import unicam.filieraAgricola.models.ProdottoSingolo;
import unicam.filieraAgricola.models.StatoProdotto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoRepository extends MongoRepository<Prodotto, String> {

    Optional<ProdottoSingolo> findProdottoById(String idProdotto);
    List<ProdottoSingolo> findProdottoByNomeAndPrezzoAndIdVenditore(String nome, double  prezzo, String idVenditore);

    Optional<PacchettoProdotti> findPacchettoById(String idPacchetto);
    List<PacchettoProdotti> findPacchettoByNomeAndPrezzoAndIdVenditore(String nome, double prezzo, String idVenditore);
}
