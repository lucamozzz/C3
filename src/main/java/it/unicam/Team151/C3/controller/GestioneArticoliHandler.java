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
import it.unicam.Team151.C3.repositories.PuntoVenditaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestioneArticoliHandler {

	@Autowired
	CatalogoArticoli catalogoArticoli;
	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	PuntoVenditaRepository puntoVenditaRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	GestorePuntoVendita gestorePuntoVendita;
	@Autowired
	GestoreCategoria gestoreCategoria;
	@Autowired
	DescrizioneArticoloManager descrizioneArticoloManager;


	public DescrizioneArticolo aggiungiArticolo(String nome, String descrizione, double prezzo,
												int quantita, Long idPuntoVendita, Long idCategoria) {
		PuntoVendita puntoVendita = gestorePuntoVendita.get(idPuntoVendita);
		Categoria categoria = gestoreCategoria.get(idCategoria);
		if(nome == null || descrizione == null || prezzo < 0  || quantita <= 0 || puntoVendita == null || categoria == null)
			throw new NullPointerException("not ok");
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloManager.create(nome, descrizione, prezzo, quantita, puntoVendita, categoria);
		catalogoArticoli.save(descrizioneArticolo);
		return descrizioneArticolo;
	}

	public void inserimentoDatiArticoloDaModificare(Long idDescrizioneArticolo, String nome, String descrizione, double prezzo, int quantita, Categoria categoria) {
		DescrizioneArticolo descrizioneArticolo = catalogoArticoli.get(idDescrizioneArticolo);
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
		catalogoArticoli.save(descrizioneArticolo);
	}

	public List<DescrizioneArticolo> getArticoli(Long idCommerciante) {
		return catalogoArticoli.getArticoliPerCommerciante(idCommerciante);
	}

	/**
	 * 
	 * @param idDescArticolo
	 */
	public void rimozioneArticolo(Long idDescArticolo) {
		DescrizioneArticolo articoloDaEliminare = catalogoArticoli.get(idDescArticolo);
		catalogoArticoli.rimuoviDescArticolo(articoloDaEliminare);
	}
}