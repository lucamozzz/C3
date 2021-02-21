package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.utenti.InterfaceCorriere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {

    List<Prenotazione> findAllByCorriere(InterfaceCorriere corriere);
}
