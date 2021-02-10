package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.utenti.Commerciante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommercianteRepository extends CrudRepository<Commerciante, Long> {

    Optional<Commerciante> findByEmail(String email);
}