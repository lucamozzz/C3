package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DescrizioneArticoloRepository extends CrudRepository<DescrizioneArticolo, Long> {

    List<DescrizioneArticolo> findAllByCategoria(Long idCategoria);

    List<DescrizioneArticolo> findAllByPuntoVendita(Long idCategoria);
}
