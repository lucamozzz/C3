package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.manager.PaccoManager;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorePrenotazione {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	@Autowired
	PaccoRepository paccoRepository;

	private List<Prenotazione> prenotazioni;

	public Prenotazione getPrenotazione(Long idPrenotazione) {
		return prenotazioneRepository.findById(idPrenotazione).get();
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}


	public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
		Prenotazione prenotazione = new Prenotazione(carrello, puntoConsegna);
		for (Pacco pacco : prenotazione.getPacchi())
			paccoRepository.save(pacco);

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