package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.utenti.InterfaceCorriere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorriereRepository extends CrudRepository<InterfaceCorriere, Long> {
    Optional<InterfaceCorriere> findByEmail(String email);
}
