package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.GestorePrenotazione;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.utenti.GestoreCliente;
import it.unicam.Team151.C3.utenti.GestoreCorriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfermaAcquistoHandler {

	@Autowired
	GestorePrenotazione gestorePrenotazione;
	@Autowired
	PaccoRepository paccoRepository;
	@Autowired
	GestoreCorriere gestoreCorriere;

	public List<Pacco> confermaAcquisto(/*Long idCommerciante*/Long idPrenotazione) {
		Prenotazione prenotazione = gestorePrenotazione.getPrenotazione(idPrenotazione);
		return new ArrayList<>(prenotazione.getPacchi());
	}

	public void confermaPagamento(Long idPrenotazione, Long idPacco) {
		boolean flag = false;
		Prenotazione prenotazione = gestorePrenotazione.getPrenotazione(idPrenotazione);
		List<Pacco> pacchi = new ArrayList<>(prenotazione.getPacchi());
		Pacco pacco = paccoRepository.findById(idPacco).get();
		pacco.setStato(Stato.Pronto);
		for(Pacco p : pacchi) {
			flag = p.getStato() == Stato.Pronto;
		}
		if(flag){
			prenotazione.getRicevuta();
			gestoreCorriere.assegnaCorriere(prenotazione);
		}
	}

}