package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticoloCarrelloRepository extends CrudRepository<ArticoloCarrello, Long> {

    Iterable<ArticoloCarrello> findAllByCarrello(Carrello idCarrello);
}