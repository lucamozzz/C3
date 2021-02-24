package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    /**
     * Metodo che restituisce un cliente con quell'email
     */
    Optional<Cliente> findByEmail(String email);
}
