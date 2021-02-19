package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.puntoVendita.GestorePacco;
import it.unicam.Team151.C3.repositories.ArticoloRepository;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import it.unicam.Team151.C3.utenti.Corriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestorePrenotazione implements IGestore<Prenotazione> {

	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	@Autowired
	PaccoRepository paccoRepository;
	@Autowired
	ArticoloRepository articoloRepository;
	@Autowired
	GestoreRicevuta gestoreRicevuta;
	@Autowired
	GestorePacco gestorePacco;

	/**
	 * 
	 * @param idCorriere
	 */
	public void getPacchi(Long idCorriere) {
		// TODO - implement GestorePrenotazione.getPacchi
		throw new UnsupportedOperationException();
	}

	@Override
	public Prenotazione get(Long id) {
		if (prenotazioneRepository.findById(id).isEmpty())
			throw new NoSuchElementException("Nessuna prenotazione trovata.");
		Prenotazione prenotazione = prenotazioneRepository.findById(id).get();
		prenotazione.setRicevuta(gestoreRicevuta.getBy(prenotazione));
		gestorePacco.getAll(prenotazione).forEach(prenotazione.getPacchi()::add);
		return prenotazione;
	}

	@Override
	public void save(Prenotazione prenotazione) {
		prenotazioneRepository.save(prenotazione);
	}

	@Override
	public void delete(Prenotazione prenotazione) {
		prenotazioneRepository.delete(prenotazione);
	}

	public List<Prenotazione> getAll(Corriere corriere){
		List<Prenotazione> prenotazioni = new ArrayList<>();
		for (Prenotazione prenotazione : prenotazioneRepository.findAllByCorriere(corriere))
			prenotazioni.add(this.get(prenotazione.getID()));
		return prenotazioni;
	}
}