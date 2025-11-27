package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Carrello;
import unicam.filieraAgricola.models.Curatore;

@Repository
public interface CarrelloRepository extends MongoRepository<Carrello, String> {
}
