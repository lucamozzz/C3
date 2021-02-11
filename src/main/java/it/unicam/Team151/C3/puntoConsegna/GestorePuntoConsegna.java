package it.unicam.Team151.C3.puntoConsegna;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.GestoreCarrello;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestorePuntoConsegna {

	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;

	private List<PuntoConsegna> puntiConsegna;
	private static GestorePuntoConsegna instance;

	private GestorePuntoConsegna() {
		this.puntiConsegna = new ArrayList<>();
	}

	public static GestorePuntoConsegna getInstance() {
		if (instance == null)
			instance = new GestorePuntoConsegna();
		return instance;
	}

	public List<PuntoConsegna> getPuntiConsegna() {
		for (PuntoConsegna puntoConsegna : puntoConsegnaRepository.findAll())
			puntiConsegna.add(puntoConsegna);
		return puntiConsegna;
	}

	//ALESSANDRO TESTA
	//ho implementato questo metodo per poter far funzionare il confermaPrenotazione.
	public PuntoConsegna getPuntoConsegna(Long idPuntoConsegna) {
		return puntoConsegnaRepository.findById(idPuntoConsegna).get();
	}

	/**
	 * 
	 * @param puntoConsegna
	 */
	public void rimuoviPuntoConsegna(PuntoConsegna puntoConsegna) {
		// TODO - implement GestorePuntoConsegna.rimuoviPuntoConsegna
		throw new UnsupportedOperationException();
	}

	//TODO createPuntoConsegna()

}