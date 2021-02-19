package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisualizzaCategorieHandler {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorie(){
        List<Categoria> categorie = new ArrayList<>();
        categoriaRepository.findAll().forEach(categorie::add);
        return categorie;
    }
}
