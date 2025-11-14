package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Evento;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {

}
