package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe che rappresenta il caso d'uso 'Prelievo Articoli'
 */
@Service
public class PrelievoArticoliHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	ILoginChecker loginChecker;

	/**
	 * Metodo che permette al corriere di prelevare un pacco da un punto vendita, cambiandone lo stato in ritirato
	 * (ed enventualmente cambia lo stato anche alla sua prenotazione)
	 */
	public void prelievoArticoli(Long idCorriere, Long idPacco) {
		loginChecker.checkCorriere(idCorriere);
		Pacco pacco = repositoryMaster.getPaccoRepository().findById(idPacco).get();
		if (repositoryMaster.getPaccoRepository().findById(idPacco).isEmpty())
			throw new NoSuchElementException("Nessun pacco trovato.");
		pacco.setStato(Stato.Ritirato);
		if (repositoryMaster.getPrenotazioneRepository().findById(pacco.getPrenotazione().getId()).isEmpty())
			throw new NoSuchElementException("Nessuna prenotazione trovata.");
		Prenotazione prenotazione = repositoryMaster.getPrenotazioneRepository().findById(pacco.getPrenotazione().getId()).get();
		repositoryMaster.getPaccoRepository().save(pacco);
		checkStatoPrenotazione(prenotazione);
	}

	private void checkStatoPrenotazione(Prenotazione prenotazione){
		boolean flag = true;
		List<Pacco> pacchiPrenotazione = new ArrayList<>(repositoryMaster.getPaccoRepository().findAllByPrenotazione(prenotazione));
		for (Pacco paccoPren : pacchiPrenotazione) {
			if(paccoPren.getStato() != Stato.Ritirato){
				flag=false;
				break;
			}
		}
		if (flag){
			prenotazione.setStato(Stato.Ritirato);
			repositoryMaster.getPrenotazioneRepository().save(prenotazione);
		}
	}

}