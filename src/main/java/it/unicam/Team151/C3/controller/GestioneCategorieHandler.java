package it.unicam.Team151.C3.controller;


import it.unicam.Team151.C3.articoli.CatalogoArticoli;
import it.unicam.Team151.C3.articoli.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestioneCategorieHandler {

	@Autowired
	CatalogoArticoli catalogoArticoli;

	public void creaCategoria(String nome, String descrizione) {
		if(checkDatiInseriti(nome, descrizione))
			catalogoArticoli.createCategoria(nome, descrizione);
	}

	public void aggiornaCategoria(Long idCategoria, String nome, String descrizione) {
		catalogoArticoli.aggiornaCategoria(idCategoria,nome,descrizione);
	}

	public boolean checkDatiInseriti(String nome, String descrizione) {
		return catalogoArticoli.checkDatiInseriti(nome, descrizione);
	}

	public List<Categoria> getCategorie() {
		return catalogoArticoli.getCategorie();
	}
}