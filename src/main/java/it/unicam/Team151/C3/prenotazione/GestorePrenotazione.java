package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorePrenotazione {

	private List<Prenotazione> prenotazioni;

	public Prenotazione getPrenotazione(Long idPrenotazione) {
		// TODO - implement GestorePrenotazione.getPrenotazione
		throw new UnsupportedOperationException();
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}

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