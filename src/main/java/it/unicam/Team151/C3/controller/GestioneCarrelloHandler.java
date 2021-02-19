package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.manager.ArticoloCarrelloManager;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
import it.unicam.Team151.C3.repositories.CarrelloRepository;
import it.unicam.Team151.C3.repositories.ClienteRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestioneCarrelloHandler {

	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;
	@Autowired
	ArticoloCarrelloManager articoloCarrelloManager;
	@Autowired
	CarrelloRepository carrelloRepository;
	@Autowired
	ClienteRepository clienteRepository;

	//TODO - eliminare codice ripetuto
	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		if (clienteRepository.findById(idCliente).isEmpty())
			throw new NoSuchElementException("Nessun cliente trovato.");
		Cliente cliente = clienteRepository.findById(idCliente).get();
		if (carrelloRepository.findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = carrelloRepository.findByCliente(cliente).get();
		articoloCarrelloRepository.findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		if (descrizioneArticoloRepository.findById(idDescArticolo).isEmpty())
			throw new NoSuchElementException("Nessun articolo trovato.");
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		if(quantita > descrizioneArticolo.getQuantita())
			throw new IllegalStateException("Articolo non disponibile in questa quantità.");
		//TODO - cambiare in findByCarrelloAndDescrizioneArticolo
		if(articoloCarrelloRepository.findByDescrizioneArticolo(descrizioneArticolo).isPresent()){
			ArticoloCarrello articoloCarrello = articoloCarrelloRepository.findByDescrizioneArticolo(descrizioneArticolo).get();
			articoloCarrello.setQuantita(articoloCarrello.getQuantita() + quantita);
			articoloCarrelloRepository.save(articoloCarrello);
		}
		else {
			//TODO - newArticoloCarrello viene creato dalla giusta classe(?)
			ArticoloCarrello articoloCarrello = articoloCarrelloManager.createArticoloCarrello(descrizioneArticolo, quantita, carrello);
			//TODO - rimuovere metodo aggiungiArticoloCarrello e aggiungere l'articolo al carrello da qui(?)
			// carrello.getArticoliCarrello().add(newArticoloCarrello)
			carrello.aggiungiArticoloCarrello(articoloCarrello, quantita);
			articoloCarrelloManager.saveArticoloCarrello(articoloCarrello);
		}
	}

	//TODO - eliminare codice ripetuto
	public void rimuoviArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		if (clienteRepository.findById(idCliente).isEmpty())
			throw new NoSuchElementException("Nessun cliente trovato.");
		Cliente cliente = clienteRepository.findById(idCliente).get();
		if (carrelloRepository.findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = carrelloRepository.findByCliente(cliente).get();
		articoloCarrelloRepository.findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		if (descrizioneArticoloRepository.findById(idDescArticolo).isEmpty())
			throw new NoSuchElementException("Nessun articolo trovato.");
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		//TODO - metodo da definire
		if (articoloCarrelloRepository.findByCarrelloAndDescrizioneArticolo(carrello, descrizioneArticolo).isEmpty())
			throw new NoSuchElementException("L'articolo non è stato trovato nel carrello.");
		ArticoloCarrello articoloCarrello = articoloCarrelloRepository.findByCarrelloAndDescrizioneArticolo(carrello, descrizioneArticolo).get();
		if (articoloCarrello.getQuantita() < quantita)
			throw new IllegalArgumentException("La quantità che vuoi rimuovere è troppo elevata.");
		if (articoloCarrello.getQuantita() == quantita){
			//TODO - rimuovere metodo rimuoviArticoloCarrello e rimuovere l'articolo dal  carrello da qui(?)
			// carrello.getArticoliCarrello().remove(articoloCarrello)
			carrello.rimuoviArticoloCarrello(articoloCarrello, quantita);
			articoloCarrelloRepository.delete(articoloCarrello);
		} else {
			articoloCarrello.setQuantita(articoloCarrello.getQuantita() - quantita);
			articoloCarrelloRepository.save(articoloCarrello);
		}
	}

	//TODO - eliminare codice ripetuto
	public List<ArticoloCarrello> mostraArticoliCarrello(Long idCliente) {
		if (clienteRepository.findById(idCliente).isEmpty())
			throw new NoSuchElementException("Nessun cliente trovato.");
		Cliente cliente = clienteRepository.findById(idCliente).get();
		if (carrelloRepository.findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = carrelloRepository.findByCliente(cliente).get();
		articoloCarrelloRepository.findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		return carrello.getArticoliCarrello();
	}
}