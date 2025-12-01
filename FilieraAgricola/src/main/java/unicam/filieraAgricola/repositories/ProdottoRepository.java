package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Prodotto;

import java.util.Optional;

@Repository
public interface ProdottoRepository extends MongoRepository<Prodotto, String> {

    Optional<Prodotto> findByNomeAndIdVenditore(String nome, String idVenditore);

}
