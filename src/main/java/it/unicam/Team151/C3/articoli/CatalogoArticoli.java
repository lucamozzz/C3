package it.unicam.Team151.C3.articoli;

import java.util.ArrayList;
import java.util.List;
import it.unicam.Team151.C3.puntoVendita.*;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoArticoli {

	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;

	private static CatalogoArticoli instance;
	private List<Categoria> categorie;
	private List<DescrizioneArticolo> allDescrizioneArticoli;

	private CatalogoArticoli() {
		this.categorie = new ArrayList<>();
	}

	public static CatalogoArticoli getInstance(){
		if (instance == null)
			instance = new CatalogoArticoli();
		return instance;
	}

	public List<Categoria> getCategorie() {
		return this.categorie;
	}

	/**
	 * 
	 * @param categoria
	 */
	public Articolo getArticoli(Categoria categoria) {
		// TODO - implement CatalogoArticoli.getArticoli
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param datiArticolo
	 */
	public DescrizioneArticolo createDescrizioneArticolo(List<String> datiArticolo) {
		// TODO - implement CatalogoArticoli.createDescrizioneArticolo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCommerciante
	 */
	public List<DescrizioneArticolo> getArticoli(Long idCommerciante) {
		// TODO - implement CatalogoArticoli.getArticoli
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param articoloDaEliminare
	 */
	public void rimuoviDescArticolo(DescrizioneArticolo articoloDaEliminare) {
		// TODO - implement CatalogoArticoli.rimuoviDescArticolo
		throw new UnsupportedOperationException();
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

	/**
	 * 
	 * @param puntoVendita
	 */
	public List<DescrizioneArticolo> getArticoli(PuntoVendita puntoVendita) {
		// TODO - implement CatalogoArticoli.getArticoli
		throw new UnsupportedOperationException();
	}
}