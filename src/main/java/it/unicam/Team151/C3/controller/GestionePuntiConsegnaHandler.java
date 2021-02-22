package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class GestionePuntiConsegnaHandler {

	@Autowired
	InterfaceAdmin admin;
	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;
	@Autowired
	ArmadiettoRepository armadiettoRepository;

	public void aggiungiPuntoConsegna(String ubicazione, int numeroArmadietti) {
		checkDati(ubicazione, ubicazione.length() > 40 || numeroArmadietti < 1);
		PuntoConsegna puntoConsegna = admin.createPuntoConsegna(ubicazione, numeroArmadietti);
		puntoConsegnaRepository.save(puntoConsegna);
		for (Armadietto armadietto : puntoConsegna.getArmadietti())
			armadiettoRepository.save(armadietto);
	}

	public void modificaPuntoConsegna(Long idPuntoConsegna, String ubicazione) {
		checkDati(ubicazione, ubicazione.length() > 40);
		PuntoConsegna puntoConsegna = getPuntoConsegna(idPuntoConsegna);
		if (!ubicazione.isEmpty())
			puntoConsegna.setUbicazione(ubicazione);
		puntoConsegnaRepository.save(puntoConsegna);
	}

	public void rimuoviPuntoConsegna(Long idPuntoConsegna) {
		puntoConsegnaRepository.delete(this.getPuntoConsegna(idPuntoConsegna));
	}

	private PuntoConsegna getPuntoConsegna(Long idPuntoConsegna) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		return puntoConsegnaRepository.findById(idPuntoConsegna).get();
	}

	private void checkDati(String ubicazione, boolean condizione) {
		if (ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (condizione)
			throw new IllegalArgumentException("Dati inseriti non validi.");
	}
}