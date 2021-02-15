package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.prenotazione.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ICorriere implements IUtenteAutenticato{

	@Autowired
	private GestorePrenotazione gestorePrenotazione;

	@Autowired
	LogoutHandler logoutHandler;

	public void consegnaArticoli() {
		// TODO - implement ICorriere.consegnaArticoli
		throw new UnsupportedOperationException();
	}

	public void prelievoArticoli() {
		// TODO - implement ICorriere.prelievoArticoli
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idPacco
	 */
	public void prelievoArticoli(int idPacco) {
		// TODO - implement ICorriere.prelievoArticoli
		throw new UnsupportedOperationException();
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}