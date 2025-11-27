package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Prodotto;
import org.springframework.data.repository.CrudRepository;
import unicam.filieraAgricola.models.ProdottoSingolo;
import unicam.filieraAgricola.models.StatoProdotto;

import java.util.List;

@Repository
public interface ProdottoRepository extends MongoRepository<ProdottoSingolo, String> {

    List<ProdottoSingolo> findByNomeAndPrezzoAndIdVenditore(String nome, double  prezzo, String idVenditore);
    List<ProdottoSingolo> findByStatoProdotto(StatoProdotto statoProdotto);

}
