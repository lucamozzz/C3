package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.RitiraPrenotazioneHandler;
import it.unicam.Team151.C3.prenotazione.Armadietto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe che ha la responsabilità di controller dell'oggetto PuntoConsegna
 */
@RestController
@RequestMapping("puntoConsegna")
public class IPuntoConsegna {

	@Autowired
	RitiraPrenotazioneHandler ritiraPrenotazioneHandler;

	/**
	 * Metodo che da il via al caso d'uso 'Ritira Prenotazione'
	 */
	@PostMapping("inserimentoCodice")
	public Armadietto inserimentoCodice(@RequestParam Long idPuntoConsegna, @RequestParam int codice) {
		return ritiraPrenotazioneHandler.checkCodice(idPuntoConsegna, codice);
	}
}