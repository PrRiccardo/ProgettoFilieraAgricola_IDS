package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Evento;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {

    List<Evento> findByDataInizioAfterAndDataFineBefore(LocalDateTime data);
}
