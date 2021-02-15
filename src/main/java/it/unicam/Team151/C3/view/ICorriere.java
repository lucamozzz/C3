package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.controller.PrelievoArticoliHandler;
import it.unicam.Team151.C3.prenotazione.*;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("corriere")
public class ICorriere implements IUtenteAutenticato{

	@Autowired
	PrelievoArticoliHandler prelievoArticoliHandler;

	@Autowired
	GestorePrenotazione gestorePrenotazione;

	@Autowired
	LogoutHandler logoutHandler;

	public void consegnaArticoli() {
		// TODO - implement ICorriere.consegnaArticoli
		throw new UnsupportedOperationException();
	}

	@PostMapping("mostraPacchi")
	public List<Pacco> mostraPacchiCorriere(@RequestParam Long idCorriere) {
		return prelievoArticoliHandler.getPacchi(idCorriere);
	}

	@PostMapping("prelievoArticoli")
	public void prelievoArticoli(@RequestParam Long idPacco) {
		prelievoArticoliHandler.prelievoArticoli(idPacco);
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}