package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import it.unicam.Team151.C3.servizioClienti.ServizioClienti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class RitiraPrenotazioneHandler {

	@Autowired
	ServizioClienti servizioClienti;
	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;
	@Autowired
	ArmadiettoRepository armadiettoRepository;

	public void ritiraPrenotazione(Long idPuntoConsegna, Long idArmadietto) {
		PuntoConsegna puntoConsegna = getPuntoConsegna(idPuntoConsegna);
		if (armadiettoRepository.findById(idArmadietto).isEmpty())
			throw new NoSuchElementException("Nessun armadietto trovato.");
		Armadietto armadietto = armadiettoRepository.findById(idArmadietto).get();
		puntoConsegna.liberaArmadietto(armadietto);
		servizioClienti.richiestaFeedback();
	}

	public Armadietto checkCodice(Long idPuntoConsegna, int codice) {
		PuntoConsegna puntoConsegna = getPuntoConsegna(idPuntoConsegna);
		Armadietto armadietto = puntoConsegna.checkCodice(codice);
		if (armadietto == null)
			throw new IllegalArgumentException("Codice errato.");
		return armadietto;
    }

	private PuntoConsegna getPuntoConsegna(Long idPuntoConsegna) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consgena trovato.");
		return puntoConsegnaRepository.findById(idPuntoConsegna).get();
	}
}