package it.unicam.Team151.C3.utenti;


import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.repositories.CommercianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GestoreCommerciante implements IGestore<Commerciante> {

    @Autowired
    CommercianteRepository commercianteRepository;

    @Override
    public Commerciante get(Long id) {
        if (commercianteRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessun commerciante trovato.");
        else return commercianteRepository.findById(id).get();
    }

    @Override
    public void save(Commerciante commerciante) {
        commercianteRepository.save(commerciante);
    }

    @Override
    public void delete(Commerciante commerciante) {
        commercianteRepository.delete(commerciante);
    }
}