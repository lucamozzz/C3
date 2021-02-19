package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.exceptions.AlreadyExistingUserException;
import it.unicam.Team151.C3.manager.UtenteManager;
import it.unicam.Team151.C3.repositories.RepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrazioneHandler {

	@Autowired
	UtenteManager utenteManager;

	@Autowired
	private RepositoryMaster repositoryMaster;

	//TODO un po' de refactoring
	public void compilaForm(String nome, String cognome, String indirizzo, String ruolo, String email, String password) throws AlreadyExistingUserException {
		List<String> form = new ArrayList<>();
		form.add(nome);
		form.add(cognome);
		form.add(indirizzo);
		form.add(ruolo);
		form.add(email);
		form.add(password);
		if (this.checkDatiInseriti(form)) {
			UtenteAutenticato newUser = utenteManager.createUtente(form);
			switch (form.get(3)) {
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

	//TODO migliorare controlli
	private boolean checkDatiInseriti(List<String> form) throws AlreadyExistingUserException {
		for (String s : form) {
			if (s.trim().equals(""))
				throw new NullPointerException("Uno o più campi null");
		}
		if (repositoryMaster.getClienteRepository().findByEmail(form.get(4)).isPresent() ||
			repositoryMaster.getCommercianteRepository().findByEmail(form.get(4)).isPresent() ||
			repositoryMaster.getCorriereRepository().findByEmail(form.get(4)).isPresent())
				throw new AlreadyExistingUserException();
		return true;
	}
}