package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.ArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class GestoreArticolo implements IGestore<Articolo> {

    @Autowired
    ArticoloRepository articoloRepository;

    @Override
    public Articolo get(Long id) {
        if (articoloRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessun articolo trovato.");
        return articoloRepository.findById(id).get();
    }

    @Override
    public void save(Articolo articolo) {
        articoloRepository.save(articolo);
    }

    @Override
    public void delete(Articolo articolo) {
        articoloRepository.delete(articolo);
    }

    //TODO - testare predicate
    public List<Articolo> getBy(Predicate<? extends Object> predicate) {
        List<Articolo> articoli = new ArrayList<>();
        articoloRepository.findAll().forEach(articoli::add);
        return articoli.stream()
                .filter((Predicate<? super Articolo>) predicate)
                .collect(Collectors.<Articolo>toList());
    }

    public List<Articolo> getArticoli(Pacco pacco){
        return articoloRepository.findAllByPacco(pacco);
    }
}
