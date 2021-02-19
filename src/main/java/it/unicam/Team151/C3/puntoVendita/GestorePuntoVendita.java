package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.repositories.CommercianteRepository;
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.GestoreCommerciante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestorePuntoVendita implements IGestore<PuntoVendita> {

	@Autowired
	PuntoVenditaRepository puntoVenditaRepository;
	@Autowired
	GestoreCommerciante gestoreCommerciante;

	public void createPuntoVendita(Commerciante commerciante, String nome, String ubicazione) {
		PuntoVendita puntoVendita = new PuntoVendita(commerciante, nome, ubicazione);
		puntoVenditaRepository.save(puntoVendita);
	}

	@Override
	public PuntoVendita get(Long idPuntoVendita){
		if (puntoVenditaRepository.findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		return puntoVenditaRepository.findById(idPuntoVendita).get();
	}

	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		Commerciante commerciante = gestoreCommerciante.get(idCommerciante);
		return puntoVenditaRepository.findAllByCommerciante(commerciante);
	}

	public List<PuntoVendita> getPuntiVendita(){
		List<PuntoVendita> puntiVendita = new ArrayList<>();
		puntoVenditaRepository.findAll().forEach(puntiVendita::add);
		return puntiVendita;
	}

	@Override
	public void save(PuntoVendita puntoVendita) {
		puntoVenditaRepository.save(puntoVendita);
	}

	@Override
	public void delete(PuntoVendita puntoVendita){
		puntoVenditaRepository.delete(puntoVendita);
	}
}