package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {
}
