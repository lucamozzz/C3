package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RicercaArticoliHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	ILoginChecker<Cliente> loginChecker;

	public List<DescrizioneArticolo> scegliCategoria(Long idCliente, Long idCategoria) {
		loginChecker.check(idCliente);
		if(repositoryMaster.getCategoriaRepository().findById(idCategoria).isEmpty())
			throw new NullPointerException("la categoria non esiste");
		Categoria categoria = repositoryMaster.getCategoriaRepository().findById(idCategoria).get();
		return repositoryMaster.getDescrizioneArticoloRepository().findAllByCategoria(categoria);
	}

	public List<PuntoVendita> ricercaArticoliPuntoVendita(Long idCliente) {
		loginChecker.check(idCliente);
		List<PuntoVendita> puntiVendita = new ArrayList<>();
		repositoryMaster.getPuntoVenditaRepository().findAll().forEach(puntiVendita::add);
		return puntiVendita;
	}

	public List<DescrizioneArticolo> scegliPuntoVendita(Long idCliente, Long idPuntoVendita) {
		loginChecker.check(idCliente);
		if(repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).isEmpty())
			throw new NullPointerException("il punto vendita non esiste.");
		PuntoVendita puntoVendita = repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).get();
		repositoryMaster.getDescrizioneArticoloRepository().findAllByPuntoVendita(puntoVendita);
		return new ArrayList<>(repositoryMaster.getDescrizioneArticoloRepository().findAllByPuntoVendita(puntoVendita));
	}

}