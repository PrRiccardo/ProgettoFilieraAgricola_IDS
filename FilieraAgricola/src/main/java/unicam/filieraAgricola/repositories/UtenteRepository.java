package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.RuoloUtente;
import unicam.filieraAgricola.models.UtenteLoggato;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends MongoRepository<UtenteLoggato, String> {

    List<UtenteLoggato> findByRuolo(RuoloUtente ruolo);
    Optional<UtenteLoggato> findByEmailAndPassword(String email, String password);
    Optional<UtenteLoggato> findByEmail(String email);

}
