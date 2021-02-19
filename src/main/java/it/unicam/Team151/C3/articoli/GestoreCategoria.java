package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class GestoreCategoria implements IGestore<Categoria> {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria get(Long id) {
        if (categoriaRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessuna categoria trovata.");
        return categoriaRepository.findById(id).get();
    }

    @Override
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
