package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.repositories.CommercianteRepository;
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestorePuntoVendita {

	@Autowired
	PuntoVenditaRepository puntoVenditaRepository;

	@Autowired
	CommercianteRepository commercianteRepository;

	private List<PuntoVendita> puntiVendita;

	public void createPuntoVendita(Commerciante commerciante, String nome, String ubicazione) {
		PuntoVendita puntoVendita = new PuntoVendita(commerciante, nome, ubicazione);
		puntoVenditaRepository.save(puntoVendita);
	}

	public PuntoVendita get(Long idPuntoVendita){
		if (puntoVenditaRepository.findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		return puntoVenditaRepository.findById(idPuntoVendita).get();
	}

	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		if (commercianteRepository.findById(idCommerciante).isEmpty())
			throw new NoSuchElementException("Nessun commerciante trovato.");
		Commerciante commerciante = commercianteRepository.findById(idCommerciante).get();
//		Iterator<PuntoVendita> it = puntoVenditaRepository.findAllByCommerciante(commerciante).iterator();
//		List<PuntoVendita> puntiVendita = new ArrayList<>();
//		while (it.hasNext())
//			puntiVendita.add(it.next());
		return puntoVenditaRepository.findAllByCommerciante(commerciante);
	}

	public void save(PuntoVendita puntoVendita) {
		puntoVenditaRepository.save(puntoVendita);
	}

	public void delete(PuntoVendita puntoVendita){
		puntoVenditaRepository.delete(puntoVendita);
	}
}