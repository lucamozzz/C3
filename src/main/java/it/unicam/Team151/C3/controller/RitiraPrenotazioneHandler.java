package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.manager.ArmadiettoManager;
import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RitiraPrenotazioneHandler {

	@Autowired
	GestorePuntoConsegna gestorePuntoConsegna;
	@Autowired
	ArmadiettoManager armadiettoManager;

	public void ritiraPrenotazione(Long idPuntoConsegna, Long idArmadietto) {
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		Armadietto armadietto = armadiettoManager.get(idArmadietto);
		puntoConsegna.liberaArmadietto(armadietto);
	}

    public Armadietto checkCodice(Long idPuntoConsegna, int codice) {
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		return puntoConsegna.checkCodice(codice);
    }
}