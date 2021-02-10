package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ElaboraPrenotazioneHandler {

	@Autowired
	GestorePuntoConsegna gestorePuntoConsegna;

	public List<PuntoConsegna> elaboraPrenotazione() {
		return gestorePuntoConsegna.getPuntiConsegna();
	}
}