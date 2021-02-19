package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.CommercianteRepository;
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
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

	//TODO - rimuovere codice ripetuto
	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		if (commercianteRepository.findById(idCommerciante).isEmpty())
			throw new NoSuchElementException("Nessun commerciante trovato.");
		Commerciante commerciante = commercianteRepository.findById(idCommerciante).get();
		return new ArrayList<>(puntoVenditaRepository.findAllByCommerciante(commerciante));
	}

	//TODO - rimuovere codice ripetuto
	public void aggiungiPuntoVendita(Long idCommerciante, String nome, String ubicazione) {
		if (nome == null || ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (nome.length() > 25 || ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
		if (commercianteRepository.findById(idCommerciante).isEmpty())
			throw new NoSuchElementException("Nessun commerciante trovato");
		Commerciante commerciante = commercianteRepository.findById(idCommerciante).get();
		puntoVenditaRepository.save(commerciante.createPuntoVendita(nome, ubicazione));
	}

	//TODO - rimuovere codice ripetuto
	public void modificaPuntoVendita(Long idPuntoVendita, String nome, String ubicazione) {
		if (nome == null || ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (nome.length() > 25 || ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
		if (puntoVenditaRepository.findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		PuntoVendita puntoVendita = puntoVenditaRepository.findById(idPuntoVendita).get();
		if (!nome.isEmpty())
			puntoVendita.setNome(nome);
		if (!ubicazione.isEmpty())
			puntoVendita.setUbicazione(ubicazione);
		puntoVenditaRepository.save(puntoVendita);
	}

	//TODO - rimuovere codice ripetuto
	public void rimuoviPuntoVendita(Long idPuntoVendita) {
		if (puntoVenditaRepository.findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		puntoVenditaRepository.delete(puntoVenditaRepository.findById(idPuntoVendita).get());
	}
}