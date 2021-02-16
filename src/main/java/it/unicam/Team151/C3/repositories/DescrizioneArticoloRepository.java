package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DescrizioneArticoloRepository extends CrudRepository<DescrizioneArticolo, Long> {

    List<DescrizioneArticolo> findAllByCategoria(Categoria categoria);

    List<DescrizioneArticolo> findAllByPuntoVendita(PuntoVendita puntoVendita);
}
