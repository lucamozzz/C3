package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.manager.PrenotazioneManager;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.*;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe che rappresenta il caso d'uso 'Conferma Prenotazione'
 */
@Service
public class ConfermaPrenotazioneHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	PrenotazioneManager prenotazioneManager;
	@Autowired
	ILoginChecker loginChecker;

	/**
	 * Metodo che conferma la prenotazine del carrello di un cliente
	 */
	public Ricevuta confermaPrenotazione(Long idPuntoConsegna, Long idCliente) {
		Cliente cliente = loginChecker.checkCliente(idCliente);
		if (repositoryMaster.getCarrelloRepository().findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = repositoryMaster.getCarrelloRepository().findByCliente(cliente).get();
		repositoryMaster.getArticoloCarrelloRepository().findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		if (repositoryMaster.getPuntoConsegnaRepository().findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		PuntoConsegna puntoConsegna = repositoryMaster.getPuntoConsegnaRepository().findById(idPuntoConsegna).get();
		if (this.checkDisponibilitaArticoli(carrello.getArticoliCarrello())){
			Prenotazione prenotazione = prenotazioneManager.createPrenotazione(carrello, puntoConsegna);
			this.updateCatalogo(carrello);
			for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello())
				repositoryMaster.getArticoloCarrelloRepository().delete(articoloCarrello);
			carrello.svuota();
			prenotazione.createRicevuta();
			repositoryMaster.getRicevutaRepository().save(prenotazione.getRicevuta());
			return prenotazione.getRicevuta();
		} else throw new IllegalStateException("Uno o più articoli non disponibili.");
	}

	/**
	 * Metodo che controlla se gli articoli nel carrello siano ancora disponibili
	 */
	public boolean checkDisponibilitaArticoli(List<ArticoloCarrello> articoli) {
		for (ArticoloCarrello articoloCarrello : articoli) {
			DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(articoloCarrello);
			if (articoloCarrello.getQuantita() > descrizioneArticolo.getQuantita())
				return false;
		}
		return true;
	}

	/**
	 * Metodo che aggiorna le descrizioni articolo degli articoli compresi nella prenotazione effettuata
	 */
	public void updateCatalogo(Carrello carrello) {
		for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello()){
			DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(articoloCarrello);
			descrizioneArticolo.setQuantita(descrizioneArticolo.getQuantita() - articoloCarrello.getQuantita());
			repositoryMaster.getDescrizioneArticoloRepository().save(descrizioneArticolo);
		}
	}

	private DescrizioneArticolo getDescrizioneArticolo(ArticoloCarrello articoloCarrello) {
		if (repositoryMaster.getDescrizioneArticoloRepository().findById(articoloCarrello.getDescrizioneArticolo().getId()).isEmpty())
			throw new NoSuchElementException("Nessuna descrizone articolo trovata.");
		return repositoryMaster.getDescrizioneArticoloRepository().findById(articoloCarrello.getDescrizioneArticolo().getId()).get();
	}
}