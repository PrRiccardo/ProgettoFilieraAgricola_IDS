package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Prodotto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ProdottoRepository extends MongoRepository<Prodotto, String> {

    List<Prodotto> findByNomeAndPrezzoAndIdVenditore(String nome, double  prezzo, String idVenditore);

}
