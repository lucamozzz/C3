package it.unicam.Team151.C3.articoli;

import java.util.ArrayList;
import java.util.List;
import it.unicam.Team151.C3.puntoVendita.*;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoArticoli {

	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	CategoriaRepository categoriaRepository;

	private static CatalogoArticoli instance;
	private List<Categoria> categorie;
	private List<DescrizioneArticolo> allDescrizioneArticoli;

	private CatalogoArticoli() {
		this.categorie = new ArrayList<>();
		this.allDescrizioneArticoli = new ArrayList<>();
	}

	public static CatalogoArticoli getInstance(){
		if (instance == null)
			instance = new CatalogoArticoli();
		return instance;
	}

	public List<Categoria> getCategorie() {
		Iterable<Categoria> it= categoriaRepository.findAll();
		for (Categoria categoria : it) {
			categorie.add(categoria);
		}
		return categorie;
	}

	public List<DescrizioneArticolo> getArticoliPerCategoria(Long idCategoria) {
		allDescrizioneArticoli.clear();
		for(DescrizioneArticolo d : descrizioneArticoloRepository.findAllByCategoria(idCategoria))
			allDescrizioneArticoli.add(d);
		return allDescrizioneArticoli;
	}

	/**
	 * 
	 * @param datiArticolo
	 */
	public DescrizioneArticolo createDescrizioneArticolo(List<String> datiArticolo) {
		// TODO - implement CatalogoArticoli.createDescrizioneArticolo
		throw new UnsupportedOperationException();
	}


	public List<DescrizioneArticolo> getArticoliPerCommerciante(Long idCommerciante) {
		// TODO - implement CatalogoArticoli.getArticoli
		throw new UnsupportedOperationException();
	}

	public void rimuoviDescArticolo(DescrizioneArticolo articoloDaEliminare) {
		// TODO - implement CatalogoArticoli.rimuoviDescArticolo
		throw new UnsupportedOperationException();
	}

	public void modificaQuantitaArticolo(Long idDescrizioneArticolo, int quantita){
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescrizioneArticolo).get();
		descrizioneArticolo.setQuantita(descrizioneArticolo.getQuantita() + quantita);
		descrizioneArticoloRepository.save(descrizioneArticolo);
	}

	public void createCategoria(String nome, String descrizione) {
		Categoria categoria = new Categoria(nome, descrizione);
		categoriaRepository.save(categoria);
	}

	public void aggiornaCategoria(Long idCategoria, String nome, String descrizione){
		if(!categoriaRepository.findById(idCategoria).isPresent())
			throw new IllegalStateException("La categoria richiesta da modificare non esiste");
		Categoria categoria=categoriaRepository.findById(idCategoria).get();
		categoria.setNome(nome);
		categoria.setDescrizione(descrizione);
		categoriaRepository.save(categoria);
	}

	public List<DescrizioneArticolo> getArticoliPerPuntoVendita(Long idPuntoVendita) {
		allDescrizioneArticoli.clear();
		allDescrizioneArticoli.addAll(descrizioneArticoloRepository.findAllByPuntoVendita(idPuntoVendita));
		return allDescrizioneArticoli;
	}

	public boolean checkDatiInseriti(String nome, String descrizione) {
		Categoria categoria = new Categoria(nome, descrizione);
		for (Categoria cat : this.getCategorie()) {
			if(cat.getNome().equals(categoria.getNome()))
				throw new IllegalStateException("Esiste già una categoria con questo nome");
		}
		return true;
	}
}