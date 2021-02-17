package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.ArticoloRepository;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class GestorePrenotazione {

	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	@Autowired
	PaccoRepository paccoRepository;
	@Autowired
	ArticoloRepository articoloRepository;

	public Prenotazione getPrenotazione(Long idPrenotazione) {
		if (prenotazioneRepository.findById(idPrenotazione).isEmpty())
			throw new NoSuchElementException("Nessuna prenotazione trovata.");
		return prenotazioneRepository.findById(idPrenotazione).get();
	}

	public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
		Prenotazione prenotazione = new Prenotazione(carrello, puntoConsegna);
		for (Pacco pacco : prenotazione.getPacchi()){
			paccoRepository.save(pacco);
			for (Articolo articolo : pacco.getArticoli())
				articoloRepository.save(articolo);
		}
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