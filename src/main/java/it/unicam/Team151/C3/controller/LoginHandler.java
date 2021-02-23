package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.exceptions.NotExistingUserException;
import it.unicam.Team151.C3.exceptions.WrongPasswordException;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.*;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginHandler {

	private List<UtenteAutenticato> utentiLoggati = new ArrayList<>();
	private UtenteAutenticato utente = null;

	@Autowired
	InterfaceAdmin admin;
	@Autowired
	private IRepositoryMaster repositoryMaster;

	public UtenteAutenticato autenticazione(String email, String pwd, String ruolo) throws NotExistingUserException, WrongPasswordException {
		if (email.equals("system@admin") && pwd.equals("admin"))
			admin.setLogged(true);
		else if (this.checkCredenziali(email, pwd, ruolo)){
			for (UtenteAutenticato utenteAutenticato : utentiLoggati) {
				if(utenteAutenticato.getEmail().equals(utente.getEmail()))
					throw new IllegalStateException("Utente già loggato.");
			}
			utente.setLogged(true);
			this.save(utente);
			utentiLoggati.add(utente);
			return utente;
		}
		return null;
	}

	public boolean checkCredenziali(String email, String pwd, String ruolo) throws NotExistingUserException, WrongPasswordException {
		switch (ruolo) {
			case "Cliente":
				check(pwd, repositoryMaster.getClienteRepository().findByEmail(email));
				break;
			case "Commerciante":
				check(pwd, repositoryMaster.getCommercianteRepository().findByEmail(email));
				break;
			case "Corriere":
				check(pwd, repositoryMaster.getCorriereRepository().findByEmail(email));
				break;
		}
		return true;
	}

	private void check(String pwd, Optional<? extends UtenteAutenticato> user) throws NotExistingUserException, WrongPasswordException {
		if (user.isEmpty())
			throw new NotExistingUserException();
		if(user.get().getPassword().equals(pwd))
			utente = user.get();
		else throw new WrongPasswordException();
	}

	public List<UtenteAutenticato> getUtenti() {
		return utentiLoggati;
	}

	private void save(UtenteAutenticato utente) {
		switch (utente.getRuolo()){
			case "Cliente" : repositoryMaster.getClienteRepository().save((Cliente) utente);
				break;
			case "Commerciante" : repositoryMaster.getCommercianteRepository().save((Commerciante) utente);
				break;
			case "Corriere" : repositoryMaster.getCorriereRepository().save((Corriere) utente);
				break;
		}
	}
}