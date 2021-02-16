package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RitiraPrenotazioneHandler {

	@Autowired
	GestorePuntoConsegna gestorePuntoConsegna;

	public void ritiraPrenotazione(int idArmadietto) {
		// TODO - implement RitiraPrenotazioneHandler.ritiraPrenotazione
		throw new UnsupportedOperationException();
	}

	public void avvisoMancatoRitiro() {
		// TODO - implement RitiraPrenotazioneHandler.avvisoMancatoRitiro
		throw new UnsupportedOperationException();
	}

	public void mancatoRitiro() {
		// TODO - implement RitiraPrenotazioneHandler.mancatoRitiro
		throw new UnsupportedOperationException();
	}

	public void generateMessage(String message) {
		// TODO - implement RitiraPrenotazioneHandler.generateMessage
		throw new UnsupportedOperationException();
	}

    public Armadietto checkCodice(Long idPuntoConsegna, int codice) {
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		return puntoConsegna.checkCodice(codice);
    }
}