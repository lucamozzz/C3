package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorePrenotazione {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;

	private List<Prenotazione> prenotazioni;

	public Prenotazione getPrenotazione(Long idPrenotazione) {
		return prenotazioneRepository.findById(idPrenotazione).get();
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}

	//in questo metodo ritornavamo la nuova prenotazione. Ok ma ci siamo dimenticati di salvarla nel DB!
	//ho messo la nuova prenotazione in una variabile cosi che poi ho potuto aggiungerla nel DB.
	//per fare questo ho aggiunto alla classe la variabile prenotazioneRepository.
	//funziona tutto ma chiarisco quello che ho fatto per correttezza.
	public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
		Prenotazione prenotazione = new Prenotazione(carrello, puntoConsegna);
		prenotazioneRepository.save(prenotazione);
		return prenotazione;
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