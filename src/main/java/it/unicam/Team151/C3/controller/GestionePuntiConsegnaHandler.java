package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

/**
 * Classe che rappresenta il caso d'uso 'Gestione Punti Consegna'
 */
@Service
public class GestionePuntiConsegnaHandler {

	@Autowired
	InterfaceAdmin admin;
	@Autowired
	IRepositoryMaster repositoryMaster;

	/**
	 * Metodo che permette all'admin di aggiungere un punto consegna
	 */
	public void aggiungiPuntoConsegna(String ubicazione, int numeroArmadietti) {
		if (!admin.getLogged())
			throw new IllegalStateException("Non hai i permessi per accedere a questa funziona.");
		checkDati(ubicazione, ubicazione.length() > 40 || numeroArmadietti < 1);
		PuntoConsegna puntoConsegna = admin.createPuntoConsegna(ubicazione, numeroArmadietti);
		repositoryMaster.getPuntoConsegnaRepository().save(puntoConsegna);
		for (Armadietto armadietto : puntoConsegna.getArmadietti())
			repositoryMaster.getArmadiettoRepository().save(armadietto);
	}

	/**
	 * Metodo che permette all'admin di modificare un punto consegna
	 */
	public void modificaPuntoConsegna(Long idPuntoConsegna, String ubicazione) {
		if (!admin.getLogged())
			throw new IllegalStateException("Non hai i permessi per accedere a questa funziona.");
		checkDati(ubicazione, ubicazione.length() > 40);
		PuntoConsegna puntoConsegna = getPuntoConsegna(idPuntoConsegna);
		if (!ubicazione.isEmpty())
			puntoConsegna.setUbicazione(ubicazione);
		repositoryMaster.getPuntoConsegnaRepository().save(puntoConsegna);
	}

	/**
	 * Metodo che permette all'admin di rimuovere un punto consegna
	 */
	public void rimuoviPuntoConsegna(Long idPuntoConsegna) {
		if (!admin.getLogged())
			throw new IllegalStateException("Non hai i permessi per accedere a questa funziona.");
		repositoryMaster.getPuntoConsegnaRepository().delete(this.getPuntoConsegna(idPuntoConsegna));
	}

	private PuntoConsegna getPuntoConsegna(Long idPuntoConsegna) {
		if (repositoryMaster.getPuntoConsegnaRepository().findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		return repositoryMaster.getPuntoConsegnaRepository().findById(idPuntoConsegna).get();
	}

	private void checkDati(String ubicazione, boolean condizione) {
		if (ubicazione == null)
			throw new NullPointerException("Dati inseriti non validi.");
		if (condizione)
			throw new IllegalArgumentException("Dati inseriti non validi.");
	}
}