package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoloRepository extends CrudRepository<Articolo, Long> {

    /**
     * Metodo che restituisce tutti gli articoli associati al pacco
     */
    List<Articolo> findAllByPacco(Pacco pacco);
}