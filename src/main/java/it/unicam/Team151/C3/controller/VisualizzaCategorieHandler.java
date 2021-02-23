package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisualizzaCategorieHandler {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ILoginChecker loginChecker;
    @Autowired
    InterfaceAdmin admin;

    public List<Categoria> getCategorie(Long id){
        if (id == -1 && admin.getLogged() || loginChecker.checkCliente(id) != null) {
            List<Categoria> categorie = new ArrayList<>();
            categoriaRepository.findAll().forEach(categorie::add);
            return categorie;
        }
        return null;
    }
}
