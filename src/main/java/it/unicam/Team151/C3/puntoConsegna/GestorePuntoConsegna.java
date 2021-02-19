package it.unicam.Team151.C3.puntoConsegna;

import it.unicam.Team151.C3.manager.ArmadiettoManager;
import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestorePuntoConsegna implements IGestore<PuntoConsegna> {

	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;
	@Autowired
	ArmadiettoRepository armadiettoRepository;
	@Autowired
	ArmadiettoManager armadiettoManager;

	private List<PuntoConsegna> puntiConsegna;
	private static GestorePuntoConsegna instance;

	private GestorePuntoConsegna() {
		this.puntiConsegna = new ArrayList<>();
	}

	public static GestorePuntoConsegna getInstance() {
		if (instance == null)
			instance = new GestorePuntoConsegna();
		return instance;
	}

	public void createPuntoConsegna(String ubicazione, int numeroArmadietti) {
		PuntoConsegna puntoConsegna = new PuntoConsegna(ubicazione);
		for (int i = 0; i < numeroArmadietti; i++)
			armadiettoManager.create(puntoConsegna);
		puntoConsegnaRepository.save(puntoConsegna);
	}

	public List<PuntoConsegna> getPuntiConsegna() {
		List<PuntoConsegna> puntiConsegna = new ArrayList<>();
		for (PuntoConsegna puntoConsegna : puntoConsegnaRepository.findAll())
			puntiConsegna.add(this.get(puntoConsegna.getId()));
		return puntiConsegna;
	}

	@Override
	public PuntoConsegna get(Long idPuntoConsegna) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(idPuntoConsegna).get();
		List<Armadietto> armadietti = armadiettoRepository.findAllByPuntoConsegna(puntoConsegna);
		puntoConsegna.getArmadietti().addAll(armadietti);
		return puntoConsegna;
	}

	@Override
	public void save(PuntoConsegna puntoConsegna) {
		puntoConsegnaRepository.save(puntoConsegna);
	}

	@Override
	public void delete(PuntoConsegna puntoConsegna) {
		puntoConsegnaRepository.delete(puntoConsegna);
	}
}