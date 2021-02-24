package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Classe che rappresenta il caso d'uso 'Gestione Categorie'
 */
@Service
public class GestioneCategorieHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;

	@Autowired
	InterfaceAdmin admin;

	/**
	 * Metodo che permette all'admin di creare una categoria
	 */
	public void creaCategoria(String nome, String descrizione) {
		if (!admin.getLogged())
			throw new IllegalStateException("Non hai i permessi per accedere a questa funziona.");
		if(checkDatiInseriti(nome)){
			Categoria categoria= admin.createCategoria(nome, descrizione);
			repositoryMaster.getCategoriaRepository().save(categoria);
		}
		else
			throw new IllegalArgumentException("Esiste già una categoria con questo nome!");
	}

	/**
	 * Metodo che permette all'admin di aggiornare una categoria
	 */
	public void aggiornaCategoria(Long idCategoria, String nome, String descrizione) {
		if (!admin.getLogged())
			throw new IllegalStateException("Non hai i permessi per accedere a questa funziona.");
		if(repositoryMaster.getCategoriaRepository().findById(idCategoria).isEmpty())
			throw new IllegalStateException("La categoria richiesta da modificare non esiste");
		Categoria categoria = repositoryMaster.getCategoriaRepository().findById(idCategoria).get();
		if (checkDatiInseriti(nome)){
		categoria.setNome(nome);
		categoria.setDescrizione(descrizione);
		repositoryMaster.getCategoriaRepository().save(categoria);
		} else
			throw new IllegalArgumentException("Esiste già una categoria con questo nome.");
	}

	public boolean checkDatiInseriti(String nome) {
		for (Categoria cat : repositoryMaster.getCategoriaRepository().findAll()) {
			if(cat.getNome().equals(nome))
				return false;
		}
		return true;
	}

}