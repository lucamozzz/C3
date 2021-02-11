package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.prenotazione.Ricevuta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RicevutaRepository extends CrudRepository<Ricevuta, Long> {
}
