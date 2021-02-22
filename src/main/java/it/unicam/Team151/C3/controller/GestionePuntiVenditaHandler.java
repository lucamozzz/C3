package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.CommercianteRepository;
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestionePuntiVenditaHandler {

	@Autowired
	CommercianteRepository commercianteRepository;
	@Autowired
	PuntoVenditaRepository puntoVenditaRepository;
	@Autowired
	ILoginChecker<Commerciante> loginChecker;

	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		Commerciante commerciante = getCommerciante(idCommerciante);
		return new ArrayList<>(puntoVenditaRepository.findAllByCommerciante(commerciante));
	}

	public void aggiungiPuntoVendita(Long idCommerciante, String nome, String ubicazione) {
		Commerciante commerciante = getCommerciante(idCommerciante);
		if (!commerciante.getLogged())
			throw new IllegalStateException("Devi effettuare il login.");
		checkDati(nome, ubicazione);
		puntoVenditaRepository.save(commerciante.createPuntoVendita(nome, ubicazione));
	}

	public void modificaPuntoVendita(Long idCommerciante, Long idPuntoVendita, String nome, String ubicazione) {
		loginChecker.check(idCommerciante);
		checkDati(nome, ubicazione);
		PuntoVendita puntoVendita = getPuntoVendita(idPuntoVendita);
		if (!nome.isEmpty())
			puntoVendita.setNome(nome);
		if (!ubicazione.isEmpty())
			puntoVendita.setUbicazione(ubicazione);
		puntoVenditaRepository.save(puntoVendita);
	}

	public void rimuoviPuntoVendita(Long idCommerciante, Long idPuntoVendita) {
		loginChecker.check(idCommerciante);
		puntoVenditaRepository.delete(this.getPuntoVendita(idPuntoVendita));
	}

	private Commerciante getCommerciante(Long idCommerciante) {
		return loginChecker.check(idCommerciante);
	}

	private PuntoVendita getPuntoVendita(Long idPuntoVendita) {
		if (puntoVenditaRepository.findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		return puntoVenditaRepository.findById(idPuntoVendita).get();
	}

	private void checkDati(String nome, String ubicazione) {
		if (nome == null || ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (nome.length() > 25 || ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
	}
}