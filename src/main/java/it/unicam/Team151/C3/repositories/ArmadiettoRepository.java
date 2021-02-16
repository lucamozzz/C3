package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmadiettoRepository extends CrudRepository<Armadietto, Long> {

    List<Armadietto> findAllByPuntoConsegna(PuntoConsegna puntoConsegna);

}