package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.repositories.*;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe che rappresenta il caso d'uso 'Gestione Carrello'
 */
@Service
public class GestioneCarrelloHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;

	@Autowired
	ILoginChecker loginChecker;

	/**
	 * Metodo che permette al cliente di aggiungere un articolo al carrello
	 */
	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Cliente cliente = getCliente(idCliente);
		Carrello carrello = getCarrello(cliente);
		DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(idDescArticolo);
		if(quantita > descrizioneArticolo.getQuantita())
			throw new IllegalStateException("Articolo non disponibile in questa quantità.");
		ArticoloCarrello articoloCarrello;
		if(repositoryMaster.getArticoloCarrelloRepository().findByCarrelloAndDescrizioneArticolo(carrello, descrizioneArticolo).isPresent()){
			articoloCarrello = repositoryMaster.getArticoloCarrelloRepository().findByCarrelloAndDescrizioneArticolo(carrello, descrizioneArticolo).get();
			articoloCarrello.setQuantita(articoloCarrello.getQuantita() + quantita);
		}
		else {
			articoloCarrello = carrello.createArticoloCarrello(descrizioneArticolo, quantita);
			carrello.getArticoliCarrello().add(articoloCarrello);
		}
		repositoryMaster.getArticoloCarrelloRepository().save(articoloCarrello);
	}

	/**
	 * Metodo che permette al cliente di rimuovere un articolo dal carrello
	 */
	public void rimuoviArticoloCarrello(Long idArticoloCarrello, int quantita, Long idCliente) {
		Cliente cliente = this.getCliente(idCliente);
		Carrello carrello = this.getCarrello(cliente);
		ArticoloCarrello articoloCarrello = getArticoloCarrello(idArticoloCarrello);
		if (articoloCarrello.getQuantita() < quantita)
			throw new IllegalArgumentException("La quantità che vuoi rimuovere è troppo elevata.");
		if (articoloCarrello.getQuantita() == quantita){
			carrello.getArticoliCarrello().remove(articoloCarrello);
			repositoryMaster.getArticoloCarrelloRepository().delete(articoloCarrello);
		} else {
			articoloCarrello.setQuantita(articoloCarrello.getQuantita() - quantita);
			repositoryMaster.getArticoloCarrelloRepository().save(articoloCarrello);
		}
	}

	/**
	 * Metodo che permette al cliente di vedere il suo carrello
	 */
	public List<ArticoloCarrello> mostraArticoliCarrello(Long idCliente) {
		Cliente cliente = getCliente(idCliente);
		Carrello carrello = getCarrello(cliente);
		return carrello.getArticoliCarrello();
	}

	private DescrizioneArticolo getDescrizioneArticolo(Long idDescArticolo) {
		if (repositoryMaster.getDescrizioneArticoloRepository().findById(idDescArticolo).isEmpty())
			throw new NoSuchElementException("Nessun articolo trovato.");
		return repositoryMaster.getDescrizioneArticoloRepository().findById(idDescArticolo).get();
	}

	private Carrello getCarrello(Cliente cliente) {
		if (repositoryMaster.getCarrelloRepository().findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = repositoryMaster.getCarrelloRepository().findByCliente(cliente).get();
		repositoryMaster.getArticoloCarrelloRepository().findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		return carrello;
	}

	private Cliente getCliente(Long idCliente) {
		return loginChecker.checkCliente(idCliente);
	}

	private ArticoloCarrello getArticoloCarrello(Long idArticoloCarrello) {
		if (repositoryMaster.getArticoloCarrelloRepository().findById(idArticoloCarrello).isEmpty())
			throw new NoSuchElementException("Nessun articolo carrello trovato.");
		return repositoryMaster.getArticoloCarrelloRepository().findById(idArticoloCarrello).get();
	}
}