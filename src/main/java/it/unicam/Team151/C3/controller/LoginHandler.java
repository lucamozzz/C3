package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.exceptions.NotExistingUserException;
import it.unicam.Team151.C3.exceptions.WrongPasswordException;
import it.unicam.Team151.C3.repositories.RepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginHandler {

	private List<UtenteAutenticato> utentiLoggati = new ArrayList<>();
	private UtenteAutenticato utente = null;

	@Autowired
	private RepositoryMaster repositoryMaster;

	/**
	 *  @param email
	 * @param pwd
	 * @param ruolo
	 */
	public UtenteAutenticato autenticazione(String email, String pwd, String ruolo) throws NotExistingUserException, WrongPasswordException {
		if (this.checkCredenziali(email, pwd, ruolo)){
			for (UtenteAutenticato utenteAutenticato : utentiLoggati) {
				if(utenteAutenticato.getEmail().equals(utente.getEmail()))
					//TODO creare un'eccezione
					throw new IllegalStateException("Utente già loggato.");
			}
			utente.setLogged(true);
			utentiLoggati.add(utente);
			return utente;
		}
		else return null;
	}

	//TODO un po' de refactoring
	public boolean checkCredenziali(String email, String pwd, String ruolo) throws NotExistingUserException, WrongPasswordException {
		switch (ruolo) {
			case "Cliente":
				caseCliente(email, pwd);
				break;
			case "Commerciante":
				caseCommerciante(email, pwd);
				break;
			case "Corriere":
				caseCorriere(email, pwd);
				break;
		}
		return true;
	}

	private void caseCliente(String email, String pwd) throws NotExistingUserException, WrongPasswordException {
		Optional<Cliente> cliente = repositoryMaster.getClienteRepository().findByEmail(email);
		if (cliente.isEmpty())
			throw new NotExistingUserException();
		if(cliente.get().getPassword().equals(pwd)) {
			utente = cliente.get();
		}
		else throw new WrongPasswordException();
	}

	private void caseCommerciante(String email, String pwd) throws NotExistingUserException, WrongPasswordException {
		Optional<Commerciante> commerciante = repositoryMaster.getCommercianteRepository().findByEmail(email);
		if (commerciante.isEmpty())
			throw new NotExistingUserException();
		if(commerciante.get().getPassword().equals(pwd))
			utente = commerciante.get();
		else throw new WrongPasswordException();
	}

	private void caseCorriere(String email, String pwd) throws NotExistingUserException, WrongPasswordException {
		Optional<Corriere> corriere = repositoryMaster.getCorriereRepository().findByEmail(email);
		if (corriere.isEmpty())
			throw new NotExistingUserException();
		if(corriere.get().getPassword().equals(pwd))
			utente = corriere.get();
		else throw new WrongPasswordException();
	}

	public List<UtenteAutenticato> getUtenti() {
		return utentiLoggati;
	}
}