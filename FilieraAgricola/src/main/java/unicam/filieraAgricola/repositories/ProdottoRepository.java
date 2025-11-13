package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Prodotto;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProdottoRepository extends MongoRepository<Prodotto, String> {

}
