package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.CatalogoArticoli;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestioneArticoliHandler {

	@Autowired
	CatalogoArticoli catalogoArticoli;
	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;


	public DescrizioneArticolo inserimentoDatiArticoloDaAggiungere(String nome, String descrizione, double prezzo,
													int quantita, PuntoVendita puntoVendita, Categoria categoria) {
		if(checkDatiInseriti(nome, descrizione, prezzo, quantita, puntoVendita, categoria)) {
			return catalogoArticoli.createDescrizioneArticolo(nome, descrizione, prezzo, quantita, puntoVendita, categoria);
		}
		else
			throw new IllegalArgumentException("not ok");

	}

	public boolean checkDatiInseriti(String nome, String descrizione, double prezzo,
									 int quantita, PuntoVendita puntoVendita, Categoria categoria) {
		if(nome == null || descrizione == null || prezzo < 0  || quantita <= 0 || puntoVendita == null || categoria == null)
			throw new NullPointerException("not ok");
		//todo - aggiungere altri controlli
		return true;
	}

	public void scegliArticolo(Long idArticolo) {
		descrizioneArticoloRepository.findById(idArticolo).get();
	}

	public void inserimentoDatiArticoloDaModificare(String nome, String descrizione, double prezzo,
													int quantita, PuntoVendita puntoVendita, Categoria categoria) {
		if(this.checkDatiInseriti(nome, descrizione, prezzo, quantita, puntoVendita, categoria))
			this.modificaArticolo(nome, descrizione, prezzo, quantita, puntoVendita, categoria);
	}

	public void modificaArticolo(String nome, String descrizione, double prezzo, int quantita, PuntoVendita puntoVendita, Categoria categoria) {
		//todo
	}

	public List<DescrizioneArticolo> getArticoli(Long idCommerciante) {
		return catalogoArticoli.getArticoliPerCommerciante(idCommerciante);
	}

	/**
	 * 
	 * @param idDescArticolo
	 */
	public void rimozioneArticolo(Long idDescArticolo) {
		DescrizioneArticolo articoloDaEliminare = descrizioneArticoloRepository.findById(idDescArticolo).get();
		catalogoArticoli.rimuoviDescArticolo(articoloDaEliminare);
	}
}