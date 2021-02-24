package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta il caso d'uso 'Ricerca Articoli'
 */
@Service
public class RicercaArticoliHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	ILoginChecker loginChecker;

	/**
	 * Metodo che restituisce tutte le descrizioni articolo associate a quella categoria
	 */
	public List<DescrizioneArticolo> scegliCategoria(Long idCliente, Long idCategoria) {
		loginChecker.checkCliente(idCliente);
		if(repositoryMaster.getCategoriaRepository().findById(idCategoria).isEmpty())
			throw new NullPointerException("la categoria non esiste");
		Categoria categoria = repositoryMaster.getCategoriaRepository().findById(idCategoria).get();
		return repositoryMaster.getDescrizioneArticoloRepository().findAllByCategoria(categoria);
	}

	/**
	 * Metodo che restituisce tutti i punti vendita presenti in C3
	 */
	public List<PuntoVendita> getPuntiVendita(Long idCliente) {
		loginChecker.checkCliente(idCliente);
		List<PuntoVendita> puntiVendita = new ArrayList<>();
		repositoryMaster.getPuntoVenditaRepository().findAll().forEach(puntiVendita::add);
		return puntiVendita;
	}

	/**
	 * Metodo che restituisce tutte le descrizioni articolo associate a quel punto vendita
	 */
	public List<DescrizioneArticolo> scegliPuntoVendita(Long idCliente, Long idPuntoVendita) {
		loginChecker.checkCliente(idCliente);
		if(repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).isEmpty())
			throw new NullPointerException("il punto vendita non esiste.");
		PuntoVendita puntoVendita = repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).get();
		repositoryMaster.getDescrizioneArticoloRepository().findAllByPuntoVendita(puntoVendita);
		return new ArrayList<>(repositoryMaster.getDescrizioneArticoloRepository().findAllByPuntoVendita(puntoVendita));
	}

}