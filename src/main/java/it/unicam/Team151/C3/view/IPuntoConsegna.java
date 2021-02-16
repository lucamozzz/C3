package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.RitiraPrenotazioneHandler;
import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("puntoConsegna")
public class IPuntoConsegna {

	@Autowired
	RitiraPrenotazioneHandler ritiraPrenotazioneHandler;

	@PostMapping("inserimentoCodice")
	public Armadietto inserimentoCodice(Long idPuntoConsegna, int codice) {
		return ritiraPrenotazioneHandler.checkCodice(idPuntoConsegna, codice);
	}
}