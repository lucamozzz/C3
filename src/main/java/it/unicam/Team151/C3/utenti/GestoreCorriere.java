package it.unicam.Team151.C3.utenti;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.repositories.CorriereRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GestoreCorriere {

	@Autowired
	CorriereRepository corriereRepository;
	@Autowired
	PrenotazioneRepository prenotazioneRepository;

	public void assegnaCorriere(Prenotazione prenotazione) {
		List<Long> idCorrieri = new ArrayList<>();
		Random rand = new Random();
		for(Corriere corriere : corriereRepository.findAll())
			idCorrieri.add(corriere.getId());
		Long randomElement = idCorrieri.get(rand.nextInt(idCorrieri.size()));
//		for(Prenotazione p : prenotazioneRepository.findAll())
//			if(p.getCorriere().getId().equals(idCorrieri.get(Math.toIntExact(randomElement))))
//				return false;
		Corriere corriereDaAssegnare = corriereRepository.findById(randomElement).get();
		prenotazione.setCorriere(corriereDaAssegnare);
		prenotazioneRepository.save(prenotazione);
	}
}