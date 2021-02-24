package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.ConsegnaArticoliHandler;
import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.controller.PrelievoArticoliHandler;
import it.unicam.Team151.C3.controller.VisualizzaPrenotazioniHandler;
import it.unicam.Team151.C3.prenotazione.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Classe che ha la responsabilità di controller dell'oggetto Corriere
 */
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

	/**
	 * Metodo che da il via al caso d'uso 'Visualizza Prenotazioni'
	 */
	@GetMapping("getPrenotazioni")
	public List<Prenotazione> getPrenotazioni(@RequestParam Long idCorriere) {
		return visualizzaPrenotazioniHandler.getPrenotazioni(idCorriere);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Consegna Articoli'
	 */
	@PostMapping("consegnaPrenotazione")
	public void consegnaPrenotazione(@RequestParam Long idCorriere, @RequestParam Long idPrenotazione) {
		consegnaArticoliHandler.consegnaPrenotazione(idCorriere, idPrenotazione);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Prelievo Articoli'
	 */
	@PostMapping("prelievoArticoli")
	public void prelievoArticoli(@RequestParam Long idCorriere, @RequestParam Long idPacco) {
		prelievoArticoliHandler.prelievoArticoli(idCorriere, idPacco);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Logout'
	 */
	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}