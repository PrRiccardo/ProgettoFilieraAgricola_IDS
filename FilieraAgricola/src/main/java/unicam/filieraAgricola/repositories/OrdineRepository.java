package unicam.filieraAgricola.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unicam.filieraAgricola.models.Curatore;
import unicam.filieraAgricola.models.Ordine;


import java.util.List;

@Repository
public interface OrdineRepository extends MongoRepository<Ordine, String> {

    List<Ordine> findByIdAcquirente(String idAcquirente);
}
