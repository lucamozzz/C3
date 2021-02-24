package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Corriere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaccoRepository extends CrudRepository<Pacco, Long> {

    /**
     * Metodo che restituisce tutti i pacchi associati alla prenotazione
     */
    List<Pacco> findAllByPrenotazione(Prenotazione prenotazione);

    /**
     * Metodo che restituisce tutti i pacchi associati al punto vendita
     */
    List<Pacco> findAllByPuntoVendita(PuntoVendita puntoVendita);
}
