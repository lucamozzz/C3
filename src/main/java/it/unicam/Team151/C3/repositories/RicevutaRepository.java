package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RicevutaRepository extends CrudRepository<Ricevuta, Long> {

    /**
     * Metodo che restituisce una ricevuta associata alla prenotazione
     */
    Optional<Ricevuta> findByPrenotazione(Prenotazione prenotazione);
}
