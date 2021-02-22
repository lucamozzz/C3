package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestioneArticoliHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	ILoginChecker loginChecker;

	public DescrizioneArticolo aggiungiArticolo(Long idCommerciante, String nome, String descrizione, double prezzo,
                                                int quantita, Long idPuntoVendita, Long idCategoria) {
		loginChecker.checkCommerciante(idCommerciante);
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

	public void modificaArticolo(Long idCommerciante, Long idDescrizioneArticolo, String nome, String descrizione, double prezzo, int quantita, Categoria categoria) {
		loginChecker.checkCommerciante(idCommerciante);
		DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(idDescrizioneArticolo);
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
		repositoryMaster.getDescrizioneArticoloRepository().save(descrizioneArticolo);
	}

	private DescrizioneArticolo getDescrizioneArticolo(Long idDescrizioneArticolo) {
		if (repositoryMaster.getDescrizioneArticoloRepository().findById(idDescrizioneArticolo).isEmpty())
			throw new NoSuchElementException("Nessuna descrizione articolo trovata.");
		return repositoryMaster.getDescrizioneArticoloRepository().findById(idDescrizioneArticolo).get();
	}

	public void rimuoviArticolo(Long idCommerciante, Long idDescArticolo) {
		loginChecker.checkCommerciante(idCommerciante);
		DescrizioneArticolo articoloDaEliminare = getDescrizioneArticolo(idDescArticolo);
		repositoryMaster.getDescrizioneArticoloRepository().delete(articoloDaEliminare);
	}

	public List<DescrizioneArticolo> getArticoliOf(Long idCommerciante) {
		List<DescrizioneArticolo> articoliCommerciante = new ArrayList<>();
		Commerciante commerciante = loginChecker.checkCommerciante(idCommerciante);
		List<PuntoVendita> puntiVenditaCommerciante = repositoryMaster.getPuntoVenditaRepository().findAllByCommerciante(commerciante);
		for(PuntoVendita pv : puntiVenditaCommerciante)
			articoliCommerciante.addAll(repositoryMaster.getDescrizioneArticoloRepository().findAllByPuntoVendita(pv));
		return articoliCommerciante;
	}
}