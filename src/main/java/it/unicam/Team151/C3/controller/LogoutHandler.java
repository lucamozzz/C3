package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogoutHandler {

	@Autowired
	LoginHandler loginHandler;

	public void logout(Long id) {
		List<UtenteAutenticato> utenti = loginHandler.getUtenti();
		UtenteAutenticato utente = null;
		for (UtenteAutenticato utenteAutenticato : utenti) {
			if (utenteAutenticato.getId().equals(id))
				utente = utenteAutenticato;
		}
		if (utente != null && utente.getLogged()) {
			utente.setLogged(false);
			loginHandler.getUtenti().remove(utente);
		}
		else throw new IllegalStateException("Nessun utente loggato.");
	}
}