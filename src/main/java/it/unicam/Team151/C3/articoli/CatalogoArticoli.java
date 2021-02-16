package it.unicam.Team151.C3.articoli;

import java.util.ArrayList;
import java.util.List;
import it.unicam.Team151.C3.puntoVendita.*;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoArticoli {

	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	GestorePuntoVendita gestorePuntoVendita;

	private static CatalogoArticoli instance;
	private List<Categoria> categorie;
	private List<DescrizioneArticolo> allDescrizioneArticoli;

	private CatalogoArticoli() {
		this.categorie = new ArrayList<>();
		allDescrizioneArticoli = new ArrayList<>();
	}

	public static CatalogoArticoli getInstance(){
		if (instance == null)
			instance = new CatalogoArticoli();
		return instance;
	}

	public List<Categoria> getCategorie() {
		return this.categorie;
	}

	//TODO - un po de refactoring dai. questo e il metodo getArticoliPerPuntoVendita
	public List<DescrizioneArticolo> getArticoliPerCategoria(Long idCategoria) {
		allDescrizioneArticoli.clear();
		Categoria categoriaScelta = categoriaRepository.findById(idCategoria).get();
		allDescrizioneArticoli.addAll(descrizioneArticoloRepository.findAllByCategoria(categoriaScelta));
		return allDescrizioneArticoli;
	}


	public DescrizioneArticolo createDescrizioneArticolo(String nome, String descrizione, double prezzo,
														 int quantita, PuntoVendita puntoVendita, Categoria categoria) {
		DescrizioneArticolo descrizioneArticolo = new DescrizioneArticolo(nome, descrizione, prezzo, quantita, puntoVendita, categoria);
		descrizioneArticoloRepository.save(descrizioneArticolo);
		return descrizioneArticolo;
	}


	public List<DescrizioneArticolo> getArticoliPerCommerciante(Long idCommerciante) {
		List<DescrizioneArticolo> articoli = new ArrayList<>();
		List<PuntoVendita> puntiVendita = gestorePuntoVendita.getPuntiVendita(idCommerciante);
		for(PuntoVendita pv : puntiVendita){
			articoli.addAll(descrizioneArticoloRepository.findAllByPuntoVendita(pv));
		}
		return articoli;
	}

	/**
	 * 
	 * @param articoloDaEliminare
	 */
	public void rimuoviDescArticolo(DescrizioneArticolo articoloDaEliminare) {
		descrizioneArticoloRepository.delete(articoloDaEliminare);
	}

	public void modificaQuantitaArticolo(Long idDescrizioneArticolo, int quantita){
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescrizioneArticolo).get();
		descrizioneArticolo.setQuantita(descrizioneArticolo.getQuantita() + quantita);
		descrizioneArticoloRepository.save(descrizioneArticolo);
	}

	/**
	 * 
	 * @param datiCategoria
	 */
	public void createCategoria(List<String> datiCategoria) {
		// TODO - implement CatalogoArticoli.createCategoria
		throw new UnsupportedOperationException();
	}

	//TODO - un po de refactoring dai.
	public List<DescrizioneArticolo> getArticoliPerPuntoVendita(Long idPuntoVendita) {
		allDescrizioneArticoli.clear();
		PuntoVendita puntoVenditaScelto = gestorePuntoVendita.get(idPuntoVendita);
		allDescrizioneArticoli.addAll(descrizioneArticoloRepository.findAllByPuntoVendita(puntoVenditaScelto));
		return allDescrizioneArticoli;
	}
}