package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogoutHandler {

	@Autowired
	LoginHandler loginHandler;
	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	InterfaceAdmin admin;

	public void logout(Long id) {
		if (id == -1)
			admin.setLogged(false);
		else {
			List<UtenteAutenticato> utenti = loginHandler.getUtenti();
			UtenteAutenticato utente = null;
			for (UtenteAutenticato utenteAutenticato : utenti) {
				if (utenteAutenticato.getId().equals(id))
					utente = utenteAutenticato;
			}
			if (utente != null && utente.getLogged()) {
				utente.setLogged(false);
				save(utente);
				loginHandler.getUtenti().remove(utente);
			} else throw new IllegalStateException("Nessun utente loggato.");
		}
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