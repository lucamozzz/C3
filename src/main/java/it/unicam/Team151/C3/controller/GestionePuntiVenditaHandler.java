package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.CommercianteRepository;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Classe che rappresenta il caso d'uso 'Gestione Punti Vendita'
 */
@Service
public class GestionePuntiVenditaHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	ILoginChecker loginChecker;

	/**
	 * Metodo che permette al commerciante di visualizzare i punti vendita ad esso associati
	 */
	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		Commerciante commerciante = getCommerciante(idCommerciante);
		return new ArrayList<>(repositoryMaster.getPuntoVenditaRepository().findAllByCommerciante(commerciante));
	}

	/**
	 * Metodo che permette al commerciante di aggiungere un punto vendita
	 */
	public void aggiungiPuntoVendita(Long idCommerciante, String nome, String ubicazione) {
		Commerciante commerciante = getCommerciante(idCommerciante);
		if (!commerciante.getLogged())
			throw new IllegalStateException("Devi effettuare il login.");
		checkDati(nome, ubicazione);
		repositoryMaster.getPuntoVenditaRepository().save(commerciante.createPuntoVendita(nome, ubicazione));
	}

	/**
	 * Metodo che permette al commerciante di modificare un punto vendita
	 */
	public void modificaPuntoVendita(Long idCommerciante, Long idPuntoVendita, String nome, String ubicazione) {
		loginChecker.checkCommerciante(idCommerciante);
		checkDati(nome, ubicazione);
		PuntoVendita puntoVendita = getPuntoVendita(idPuntoVendita);
		if (!nome.isEmpty())
			puntoVendita.setNome(nome);
		if (!ubicazione.isEmpty())
			puntoVendita.setUbicazione(ubicazione);
		repositoryMaster.getPuntoVenditaRepository().save(puntoVendita);
	}

	/**
	 * Metodo che permette al commerciante di rimuovere un punto vendita
	 */
	public void rimuoviPuntoVendita(Long idCommerciante, Long idPuntoVendita) {
		loginChecker.checkCommerciante(idCommerciante);
		repositoryMaster.getPuntoVenditaRepository().delete(this.getPuntoVendita(idPuntoVendita));
	}

	private Commerciante getCommerciante(Long idCommerciante) {
		return loginChecker.checkCommerciante(idCommerciante);
	}

	private PuntoVendita getPuntoVendita(Long idPuntoVendita) {
		if (repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		return repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).get();
	}

	private void checkDati(String nome, String ubicazione) {
		if (nome == null || ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (nome.length() > 25 || ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
	}
}