package it.unicam.Team151.C3.controller;


import it.unicam.Team151.C3.manager.PaccoManager;
import it.unicam.Team151.C3.prenotazione.GestorePrenotazione;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.CorriereRepository;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.GestoreCorriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrelievoArticoliHandler {

	@Autowired
	GestoreCorriere gestoreCorriere;
	@Autowired
	GestorePrenotazione gestorePrenotazione;

	@Autowired
	CorriereRepository corriereRepository;

	@Autowired
	PaccoManager paccoManager;

	@Autowired
	PrenotazioneRepository prenotazioneRepository;

	@Autowired
	PaccoRepository paccoRepository;

	public void prelievoArticoli(Long idPacco) {
		Pacco pacco = paccoRepository.findById(idPacco).get();
		pacco.setStato(Stato.Ritirato);
		Prenotazione prenotazione=prenotazioneRepository.findById(pacco.getPrenotazione().getID()).get();
		paccoManager.savePacco(pacco);
		checkStatoPrenotazione(prenotazione);
	}

	public List<Prenotazione> getPrenotazioni(Long idCorriere) {
		Corriere corriere = gestoreCorriere.get(idCorriere);
		return gestorePrenotazione.getAll(corriere);
	}

	//TODO da ridevere (cosuccia in più)
	private void checkStatoPrenotazione(Prenotazione prenotazione){
		boolean flag = true;
		List<Pacco> pacchiPrenotazione= new ArrayList<>();
		pacchiPrenotazione.addAll(paccoRepository.findAllByPrenotazione(prenotazione));
		for (Pacco paccoPren : pacchiPrenotazione) {
			if(!paccoPren.getStato().equals(prenotazione.getStato())){
				flag=false;
				break;
			}
		}
		if (flag==false && pacchiPrenotazione.get(pacchiPrenotazione.size()-1).getStato().equals(prenotazione.getStato()))
			flag=true;
		if (flag==true){
			prenotazione.setStato(pacchiPrenotazione.get(0).getStato());
			prenotazioneRepository.save(prenotazione);
		}
	}

}