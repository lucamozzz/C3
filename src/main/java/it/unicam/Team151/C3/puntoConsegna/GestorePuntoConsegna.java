package it.unicam.Team151.C3.puntoConsegna;

import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestorePuntoConsegna {

	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;

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
		PuntoConsegna puntoConsegna = new PuntoConsegna(ubicazione, numeroArmadietti);
		puntoConsegnaRepository.save(puntoConsegna);
	}

	public List<PuntoConsegna> getPuntiConsegna() {
		List<PuntoConsegna> puntiConsegna = new ArrayList<>();
		puntoConsegnaRepository.findAll().forEach(puntiConsegna::add);
		return puntiConsegna;
	}

	public PuntoConsegna get(Long idPuntoConsegna) {
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		return puntoConsegnaRepository.findById(idPuntoConsegna).get();
	}

	public void save(PuntoConsegna puntoConsegna) {
		puntoConsegnaRepository.save(puntoConsegna);
	}

	public void delete(PuntoConsegna puntoConsegna) {
		puntoConsegnaRepository.delete(puntoConsegna);
	}
}