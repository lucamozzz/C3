package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoVenditaRepository extends CrudRepository<PuntoVendita, Long> {
}
