package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticoloCarrelloRepository extends CrudRepository<ArticoloCarrello, Long> {

    /**
     * Metodo che restituisce tutti gli articoli carrello associati al carrello
     */
    List<ArticoloCarrello> findAllByCarrello(Carrello carrello);

    /**
     * Metodo che restituisce un articolo carrello con quella descrizione articolo
     */
    Optional<ArticoloCarrello> findByDescrizioneArticolo(DescrizioneArticolo descrizioneArticolo);

    /**
     * Metodo che restituisce un articolo carrello con quella descrizione articolo e quel carrello
     */
    Optional<ArticoloCarrello> findByCarrelloAndDescrizioneArticolo(Carrello carrello, DescrizioneArticolo descrizioneArticolo);
}