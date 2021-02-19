package it.unicam.Team151.C3.utenti;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.repositories.CorriereRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class GestoreCorriere implements IGestore<Corriere> {

	@Autowired
	CorriereRepository corriereRepository;
	@Autowired
	PrenotazioneRepository prenotazioneRepository;

	@Override
	public Corriere get(Long id) {
		if (corriereRepository.findById(id).isEmpty())
			throw new NoSuchElementException("Nessun corriere trovato.");
		else return corriereRepository.findById(id).get();
	}

	@Override
	public void save(Corriere corriere) {
		corriereRepository.save(corriere);
	}

	@Override
	public void delete(Corriere corriere) {
		corriereRepository.delete(corriere);
	}

	//TODO aggiungere all'interfaccia
	public List<Corriere> getAll(){
		List<Corriere> corrieri = new ArrayList<>();
		corriereRepository.findAll().forEach(corrieri::add);
		return corrieri;
	}
}