package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.manager.ArmadiettoManager;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsegnaArticoliHandler {

	@Autowired
	PrenotazioneRepository prenotazioneRepository;

	@Autowired
	ArmadiettoManager armadiettoManager;

	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;

	public void consegnaArticolo(Long idPrenotazione) {
		Prenotazione prenotazione = prenotazioneRepository.findById(idPrenotazione).get();
		if(!prenotazione.getStato().equals(Stato.Ritirato))
			throw new IllegalStateException("Errore di stato: la prenotazione non è in stato di ritirato");
		else {
			prenotazione.setStato(Stato.Consegnato);
			prenotazioneRepository.save(prenotazione);
			PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(prenotazione.getPuntoConsegna().getId()).get();
			List<Armadietto> armadietti = armadiettoManager.getArmadietti(puntoConsegna);
			Armadietto armadietto = puntoConsegna.assegnaArmadietto(prenotazione, armadietti);
			armadiettoManager.save(armadietto);
		}

	}

}