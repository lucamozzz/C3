package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.InterfaceCommerciante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuntoVenditaRepository extends CrudRepository<PuntoVendita, Long> {

    List<PuntoVendita> findAllByCommerciante(InterfaceCommerciante commerciante);
}
