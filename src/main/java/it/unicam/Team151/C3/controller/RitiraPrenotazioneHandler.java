package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import it.unicam.Team151.C3.servizioClienti.ServizioClienti;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
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
	@Autowired
	ILoginChecker loginChecker;

	public void ritiraPrenotazione(Long idCliente, Long idPuntoConsegna, Long idArmadietto) {
		loginChecker.checkCliente(idCliente);
		PuntoConsegna puntoConsegna = getPuntoConsegna(idPuntoConsegna);
		if (armadiettoRepository.findById(idArmadietto).isEmpty())
			throw new NoSuchElementException("Nessun armadietto trovato.");
		Armadietto armadietto = armadiettoRepository.findById(idArmadietto).get();
		puntoConsegna.liberaArmadietto(armadietto);
		armadiettoRepository.save(armadietto);
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
		PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(idPuntoConsegna).get();
		puntoConsegna.getArmadietti().addAll(armadiettoRepository.findAllByPuntoConsegna(puntoConsegna));
		return puntoConsegna;
	}
}