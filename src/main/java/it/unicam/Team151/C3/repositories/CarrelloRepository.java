package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelloRepository extends CrudRepository<Carrello, Long> {

    /**
     * Metodo che restituisce un carrello associato al cliente
     */
    Optional<Carrello> findByCliente(Cliente cliente);
}
