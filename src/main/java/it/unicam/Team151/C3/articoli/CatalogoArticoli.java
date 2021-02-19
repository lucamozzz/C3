package it.unicam.Team151.C3.articoli;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import antlr.ASTNULLType;
import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.puntoVendita.*;
import it.unicam.Team151.C3.repositories.CategoriaRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoArticoli implements IGestore<DescrizioneArticolo> {

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
		Iterable<Categoria> it= categoriaRepository.findAll();
		for (Categoria categoria : it) {
			categorie.add(categoria);
		}
		return categorie;
	}

	public List<DescrizioneArticolo> getArticoliPerCategoria(Categoria categoria) {
		return descrizioneArticoloRepository.findAllByCategoria(categoria);
	}

	public List<DescrizioneArticolo> getArticoliPerCommerciante(Long idCommerciante) {
		List<DescrizioneArticolo> articoli = new ArrayList<>();
		List<PuntoVendita> puntiVendita = gestorePuntoVendita.getPuntiVendita(idCommerciante);
		for(PuntoVendita pv : puntiVendita)
			articoli.addAll(descrizioneArticoloRepository.findAllByPuntoVendita(pv));
		return articoli;
	}

	public void rimuoviDescArticolo(DescrizioneArticolo articoloDaEliminare) {
		descrizioneArticoloRepository.delete(articoloDaEliminare);
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

	public boolean checkDatiInseriti(String nome, String descrizione) {
		Categoria categoria = new Categoria(nome, descrizione);
		for (Categoria cat : this.getCategorie()) {
			if(cat.getNome().equals(categoria.getNome()))
				throw new IllegalStateException("Esiste già una categoria con questo nome");
		}
		return true;
	}

	public List<DescrizioneArticolo> getArticoliPerPuntoVendita(PuntoVendita puntoVendita) {
		allDescrizioneArticoli.clear();
		allDescrizioneArticoli.addAll(descrizioneArticoloRepository.findAllByPuntoVendita(puntoVendita));
		return allDescrizioneArticoli;
	}

	@Override
	public DescrizioneArticolo get(Long id) {
		if(descrizioneArticoloRepository.findById(id).isEmpty())
			throw new NoSuchElementException("Nessuna descrizione articolo trovata.");
		return descrizioneArticoloRepository.findById(id).get();
	}

	@Override
	public void save(DescrizioneArticolo descrizioneArticolo) {
		descrizioneArticoloRepository.save(descrizioneArticolo);
	}

	@Override
	public void delete(DescrizioneArticolo descrizioneArticolo) {
		descrizioneArticoloRepository.delete(descrizioneArticolo);
	}
}