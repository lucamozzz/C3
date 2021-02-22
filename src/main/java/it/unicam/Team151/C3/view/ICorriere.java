package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.ConsegnaArticoliHandler;
import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.controller.PrelievoArticoliHandler;
import it.unicam.Team151.C3.controller.VisualizzaPrenotazioniHandler;
import it.unicam.Team151.C3.prenotazione.*;
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
	ConsegnaArticoliHandler consegnaArticoliHandler;

	@Autowired
	PrelievoArticoliHandler prelievoArticoliHandler;

	@Autowired
	LogoutHandler logoutHandler;

	@Autowired
	VisualizzaPrenotazioniHandler visualizzaPrenotazioniHandler;

	@PostMapping("getPrenotazioni")
	public List<Prenotazione> consegnaArticoli(@RequestParam Long idCorriere) {
		return visualizzaPrenotazioniHandler.getPrenotazioni(idCorriere);
	}

	@PostMapping("consegnaPrenotazione")
	public void consegnaPrenotazione(@RequestParam Long idPrenotazione) {
		consegnaArticoliHandler.consegnaPrenotazione(idPrenotazione);
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