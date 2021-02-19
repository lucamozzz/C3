package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class GestionePuntiConsegnaHandler {

	@Autowired
	GestorePuntoConsegna gestorePuntoConsegna;
	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;

	//TODO - da vedere insieme chi crea un Armadietto
	public void aggiungiPuntoConsegna(String ubicazione, int numeroArmadietti) {
		if (ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (ubicazione.length() > 40 || numeroArmadietti < 1)
			throw new IllegalArgumentException("Dati inseriti non validi.");
		gestorePuntoConsegna.createPuntoConsegna(ubicazione, numeroArmadietti);
	}

	//TODO - eliminare codice ripetuto
	public void modificaPuntoConsegna(Long idPuntoConsegna, String ubicazione) {
		if (ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (ubicazione.length() > 40)
			throw new IllegalArgumentException("Dati inseriti non validi.");
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(idPuntoConsegna).get();
		if (!ubicazione.isEmpty())
			puntoConsegna.setUbicazione(ubicazione);
		puntoConsegnaRepository.save(puntoConsegna);
	}

	//TODO - eliminare codice ripetuto
	public void rimuoviPuntoConsegna(Long idPuntoConsegna) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		puntoConsegnaRepository.delete(puntoConsegnaRepository.findById(idPuntoConsegna).get());
	}
}