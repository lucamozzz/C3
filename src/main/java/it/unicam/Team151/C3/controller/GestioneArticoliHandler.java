package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.CatalogoArticoli;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.articoli.GestoreCategoria;
import it.unicam.Team151.C3.manager.DescrizioneArticoloManager;
import it.unicam.Team151.C3.puntoVendita.GestorePuntoVendita;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestioneArticoliHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;


	//todo - eliminare codice ripetuto
	public DescrizioneArticolo aggiungiArticolo(String nome, String descrizione, double prezzo,
												int quantita, Long idPuntoVendita, Long idCategoria) {
		if (repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).isEmpty())
			throw new NoSuchElementException("Nessun punto vendita trovato.");
		PuntoVendita puntoVendita = repositoryMaster.getPuntoVenditaRepository().findById(idPuntoVendita).get();
		if (repositoryMaster.getCategoriaRepository().findById(idCategoria).isEmpty())
			throw new NoSuchElementException("Nessuna categoria trovata.");
		Categoria categoria = repositoryMaster.getCategoriaRepository().findById(idCategoria).get();
		if(nome == null || descrizione == null || prezzo < 0 || quantita <= 0)
			throw new NullPointerException("not ok");
		DescrizioneArticolo descrizioneArticolo = puntoVendita.getCommerciante().createDescrizioneArticolo(nome, descrizione, prezzo, quantita, puntoVendita, categoria);
		repositoryMaster.getDescrizioneArticoloRepository().save(descrizioneArticolo);
		return descrizioneArticolo;
	}

	//todo - eliminare codice ripetuto
	public void modificaArticolo(Long idDescrizioneArticolo, String nome, String descrizione, double prezzo, int quantita, Categoria categoria) {
		if(repositoryMaster.getDescrizioneArticoloRepository().findById(idDescrizioneArticolo).isEmpty())
			throw new NoSuchElementException("Nessuna descrizione articolo trovata.");
		DescrizioneArticolo descrizioneArticolo = repositoryMaster.getDescrizioneArticoloRepository().findById(idDescrizioneArticolo).get();
		if(nome == null || descrizione == null || prezzo < 0  || quantita < 0 || categoria == null)
			throw new NullPointerException("not ok");
		if (!nome.isEmpty())
			descrizioneArticolo.setNome(nome);
		if (!descrizione.isEmpty())
			descrizioneArticolo.setDescrizione(descrizione);
		if (descrizioneArticolo.getPrezzo() != prezzo)
			descrizioneArticolo.setPrezzo(prezzo);
		if (descrizioneArticolo.getQuantita() != quantita)
			descrizioneArticolo.setQuantita(quantita);
		if (!descrizioneArticolo.getCategoria().equals(categoria))
			descrizioneArticolo.setCategoria(categoria);
		//codice ripetuto
		repositoryMaster.getDescrizioneArticoloRepository().save(descrizioneArticolo);
	}

	//todo eliminare codice ripetuto
	public void rimuoviArticolo(Long idDescArticolo) {
		if(repositoryMaster.getDescrizioneArticoloRepository().findById(idDescArticolo).isEmpty())
			throw new NoSuchElementException("Nessuna descrizione articolo trovata.");
		DescrizioneArticolo articoloDaEliminare = repositoryMaster.getDescrizioneArticoloRepository().findById(idDescArticolo).get();
		repositoryMaster.getDescrizioneArticoloRepository().delete(articoloDaEliminare);
	}

	public List<DescrizioneArticolo> getArticoliOf(Long idCommerciante) {
		List<DescrizioneArticolo> articoliCommerciante = new ArrayList<>();
		if (repositoryMaster.getCommercianteRepository().findById(idCommerciante).isEmpty())
			throw new NoSuchElementException("Nessun commerciante trovato.");
		Commerciante commerciante = repositoryMaster.getCommercianteRepository().findById(idCommerciante).get();
		List<PuntoVendita> puntiVenditaCommerciante = repositoryMaster.getPuntoVenditaRepository().findAllByCommerciante(commerciante);
		for(PuntoVendita pv : puntiVenditaCommerciante)
			articoliCommerciante.addAll(repositoryMaster.getDescrizioneArticoloRepository().findAllByPuntoVendita(pv));
		return articoliCommerciante;
	}
}