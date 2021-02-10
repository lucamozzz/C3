package it.unicam.Team151.C3.prenotazione;


import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;

import java.util.List;

public class GestorePrenotazione {

	private List<Prenotazione> prenotazioni;


	public Prenotazione getPrenotazione(Long idPrenotazione) {
		// TODO - implement GestorePrenotazione.getPrenotazione
		throw new UnsupportedOperationException();
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}

	/**
	 *  @param carrello
	 * @param puntoConsegna
	 * @return
	 */
	public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
		return new Prenotazione(carrello, puntoConsegna);
	}

	/**
	 * 
	 * @param idCorriere
	 */
	public void getPacchi(Long idCorriere) {
		// TODO - implement GestorePrenotazione.getPacchi
		throw new UnsupportedOperationException();
	}

}