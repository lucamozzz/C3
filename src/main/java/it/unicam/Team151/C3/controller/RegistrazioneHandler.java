package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.exceptions.AlreadyExistingUserException;
import it.unicam.Team151.C3.manager.UtenteManager;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe che rappresenta il caso d'uso 'Registrazione'
 */
@Service
public class RegistrazioneHandler {

	@Autowired
	UtenteManager utenteManager;

	@Autowired
	private IRepositoryMaster repositoryMaster;

	/**
	 * Metodo che permette ad un utente di registrarsi a C3
	 */
	public void compilaForm(String nome, String cognome, String indirizzo, String ruolo, String email, String password) throws AlreadyExistingUserException {
		if (this.checkDatiInseriti(nome, cognome, indirizzo, ruolo, email, password)) {
			UtenteAutenticato newUser = utenteManager.createUtente(nome, cognome, indirizzo, ruolo, email, password);
			switch (ruolo) {
				case "Cliente":
					repositoryMaster.getClienteRepository().save((Cliente) newUser);
					break;
				case "Commerciante":
					repositoryMaster.getCommercianteRepository().save((Commerciante) newUser);
					break;
				case "Corriere":
					repositoryMaster.getCorriereRepository().save((Corriere) newUser);
					break;
			}
		}
	}

	private boolean checkDatiInseriti(String nome, String cognome, String indirizzo, String ruolo, String email, String password) throws AlreadyExistingUserException {
		if (nome.trim().equals("") || cognome.trim().equals("") || indirizzo.trim().equals("") || ruolo.trim().equals("") || email.trim().equals("") || password.trim().equals(""))
			throw new NullPointerException("Uno o più campi null");
		if (repositoryMaster.getClienteRepository().findByEmail(email).isPresent() ||
			repositoryMaster.getCommercianteRepository().findByEmail(email).isPresent() ||
			repositoryMaster.getCorriereRepository().findByEmail(email).isPresent())
				throw new AlreadyExistingUserException();
		return true;
	}
}