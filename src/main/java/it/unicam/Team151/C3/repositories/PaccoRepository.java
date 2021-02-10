package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.puntoVendita.Pacco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaccoRepository extends CrudRepository<Pacco, Long> {
}
