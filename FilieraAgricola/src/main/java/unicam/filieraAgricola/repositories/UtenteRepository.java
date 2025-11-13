package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.UtenteLoggato;

import java.util.List;

@Repository
public interface UtenteRepository extends MongoRepository<UtenteLoggato, String> {

    List<UtenteLoggato> findByRole(RuoloUtente ruolo);

}
