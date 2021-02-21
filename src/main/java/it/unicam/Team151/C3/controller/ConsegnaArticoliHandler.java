package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.InterfaceCorriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ConsegnaArticoliHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;

	public void consegnaPrenotazione(Long idPrenotazione) {
		if (repositoryMaster.getPrenotazioneRepository().findById(idPrenotazione).isEmpty())
			throw new NoSuchElementException("Nessuna prenotazione trovata.");
		Prenotazione prenotazione = repositoryMaster.getPrenotazioneRepository().findById(idPrenotazione).get();
		prenotazione.setRicevuta(repositoryMaster.getRicevutaRepository().findByPrenotazione(prenotazione).get());
		repositoryMaster.getPaccoRepository().findAllByPrenotazione(prenotazione).forEach(prenotazione.getPacchi()::add);
		if(!prenotazione.getStato().equals(Stato.Ritirato))
			throw new IllegalStateException("Errore di stato: la prenotazione non è in stato di ritirato");
		prenotazione.setStato(Stato.Consegnato);
		repositoryMaster.getPrenotazioneRepository().save(prenotazione);
		if (repositoryMaster.getArmadiettoRepository().findByCodice(prenotazione.getRicevuta().getCodice()).isEmpty())
			throw new NoSuchElementException("Nessun armadietto trovato.");
		Armadietto armadietto = repositoryMaster.getArmadiettoRepository().findByCodice(prenotazione.getRicevuta().getCodice()).get();
		armadietto.setPrenotazione(prenotazione);
		repositoryMaster.getArmadiettoRepository().save(armadietto);
	}

	public List<Prenotazione> getPrenotazioni(Long idCorriere) {
		InterfaceCorriere corriere = repositoryMaster.getCorriereRepository().findById(idCorriere).get();
		return repositoryMaster.getPrenotazioneRepository().findAllByCorriere(corriere);
	}
}