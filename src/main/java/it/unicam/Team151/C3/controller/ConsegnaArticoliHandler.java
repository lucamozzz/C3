package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.GestorePrenotazione;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.GestoreArmadietto;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.GestoreCorriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsegnaArticoliHandler {

	@Autowired
	GestoreCorriere gestoreCorriere;

	@Autowired
	GestorePrenotazione gestorePrenotazione;

	@Autowired
	GestoreArmadietto gestoreArmadietto;

	public void consegnaPrenotazione(Long idPrenotazione) {
		Prenotazione prenotazione = gestorePrenotazione.get(idPrenotazione);
		if(!prenotazione.getStato().equals(Stato.Ritirato))
			throw new IllegalStateException("Errore di stato: la prenotazione non è in stato di ritirato");
		prenotazione.setStato(Stato.Consegnato);
		gestorePrenotazione.save(prenotazione);
		Armadietto armadietto = gestoreArmadietto.get(prenotazione.getRicevuta().getCodice());
		armadietto.setPrenotazione(prenotazione);
		gestoreArmadietto.save(armadietto);
	}

	public List<Prenotazione> getPrenotazioni(Long idCorriere) {
		Corriere corriere = gestoreCorriere.get(idCorriere);
		return gestorePrenotazione.getAll(corriere);
	}
}