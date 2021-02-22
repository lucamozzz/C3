package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestioneCategorieHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;

	InterfaceAdmin amministratoreDiSistema;

	public void creaCategoria(String nome, String descrizione) {
		if(checkDatiInseriti(nome, descrizione)){
			Categoria categoria= amministratoreDiSistema.createCategoria(nome, descrizione);
			repositoryMaster.getCategoriaRepository().save(categoria);
		}
		else
			throw new IllegalArgumentException("Esiste già una categoria con questo nome!");
	}

	public void aggiornaCategoria(Long idCategoria, String nome, String descrizione) {
		if(repositoryMaster.getCategoriaRepository().findById(idCategoria).isEmpty())
			throw new IllegalStateException("La categoria richiesta da modificare non esiste");
		Categoria categoria = repositoryMaster.getCategoriaRepository().findById(idCategoria).get();
		categoria.setNome(nome);
		categoria.setDescrizione(descrizione);
		repositoryMaster.getCategoriaRepository().save(categoria);
	}

	public boolean checkDatiInseriti(String nome, String descrizione) {
		Categoria categoria = new Categoria(nome, descrizione);
		for (Categoria cat : repositoryMaster.getCategoriaRepository().findAll()) {
			if(cat.getNome().equals(categoria.getNome()))
				return false;
		}
		return true;
	}

}