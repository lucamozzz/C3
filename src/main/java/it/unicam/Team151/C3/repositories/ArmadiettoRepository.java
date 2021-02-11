package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmadiettoRepository extends CrudRepository<Armadietto, Long> {
}