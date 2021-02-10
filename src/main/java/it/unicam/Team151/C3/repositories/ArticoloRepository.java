package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.Articolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticoloRepository extends CrudRepository<Articolo, Long> {
}