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

	//TODO - eliminare codice ripetuto
	public void ritiraPrenotazione(Long idPuntoConsegna, Long idArmadietto) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consgena trovato.");
		PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(idPuntoConsegna).get();
		if (armadiettoRepository.findById(idArmadietto).isEmpty())
			throw new NoSuchElementException("Nessun armadietto trovato.");
		Armadietto armadietto = armadiettoRepository.findById(idArmadietto).get();
		puntoConsegna.liberaArmadietto(armadietto);
		servizioClienti.richiestaFeedback();
	}

	//TODO - eliminare codice ripetuto
	public Armadietto checkCodice(Long idPuntoConsegna, int codice) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consgena trovato.");
		PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(idPuntoConsegna).get();
		Armadietto armadietto = puntoConsegna.checkCodice(codice);
		if (armadietto == null)
			throw new IllegalArgumentException("Codice errato.");
		return armadietto;
    }
}