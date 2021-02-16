package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoVendita.GestorePuntoVendita;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.CommercianteRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestionePuntiVenditaHandler {

	@Autowired
	GestorePuntoVendita gestorePuntoVendita;

	@Autowired
	CommercianteRepository commercianteRepository;

	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		return gestorePuntoVendita.getPuntiVendita(idCommerciante);
	}

	public void aggiungiPuntoVendita(Long idCommerciante, String nome, String ubicazione) {
		if (nome == null || ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (nome.length() > 25 || ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
		if (commercianteRepository.findById(idCommerciante).isEmpty())
			throw new NoSuchElementException("Nessun commerciante trovato");
		Commerciante commerciante = commercianteRepository.findById(idCommerciante).get();
		gestorePuntoVendita.createPuntoVendita(commerciante, nome, ubicazione);
	}

	public void modificaPuntoVendita(Long idPuntoVendita, String nome, String ubicazione) {
		if (nome == null || ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (nome.length() > 25 || ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
		PuntoVendita puntoVendita = gestorePuntoVendita.get(idPuntoVendita);
		if (!nome.isEmpty())
			puntoVendita.setNome(nome);
		if (!ubicazione.isEmpty())
			puntoVendita.setUbicazione(ubicazione);
		gestorePuntoVendita.save(puntoVendita);
	}

	public void rimuoviPuntoVendita(Long idPuntoVendita) {
		PuntoVendita puntoVendita = gestorePuntoVendita.get(idPuntoVendita);
		gestorePuntoVendita.delete(puntoVendita);
	}
}