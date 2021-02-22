package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
import it.unicam.Team151.C3.repositories.CarrelloRepository;
import it.unicam.Team151.C3.repositories.ClienteRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

//TODO - utilizzare repository master

@Service
public class GestioneCarrelloHandler {

	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;
	@Autowired
	CarrelloRepository carrelloRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ILoginChecker loginChecker;

	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Cliente cliente = getCliente(idCliente);
		Carrello carrello = getCarrello(cliente);
		DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(idDescArticolo);
		if(quantita > descrizioneArticolo.getQuantita())
			throw new IllegalStateException("Articolo non disponibile in questa quantità.");
		ArticoloCarrello articoloCarrello;
		if(articoloCarrelloRepository.findByCarrelloAndDescrizioneArticolo(carrello, descrizioneArticolo).isPresent()){
			articoloCarrello = articoloCarrelloRepository.findByCarrelloAndDescrizioneArticolo(carrello, descrizioneArticolo).get();
			articoloCarrello.setQuantita(articoloCarrello.getQuantita() + quantita);
		}
		else {
			articoloCarrello = carrello.createArticoloCarrello(descrizioneArticolo, quantita);
			carrello.getArticoliCarrello().add(articoloCarrello);
		}
		articoloCarrelloRepository.save(articoloCarrello);
	}

	public void rimuoviArticoloCarrello(Long idArticoloCarrello, int quantita, Long idCliente) {
		Cliente cliente = this.getCliente(idCliente);
		Carrello carrello = this.getCarrello(cliente);
		ArticoloCarrello articoloCarrello = getArticoloCarrello(idArticoloCarrello);
		if (articoloCarrello.getQuantita() < quantita)
			throw new IllegalArgumentException("La quantità che vuoi rimuovere è troppo elevata.");
		if (articoloCarrello.getQuantita() == quantita){
			carrello.getArticoliCarrello().remove(articoloCarrello);
			articoloCarrelloRepository.delete(articoloCarrello);
		} else {
			articoloCarrello.setQuantita(articoloCarrello.getQuantita() - quantita);
			articoloCarrelloRepository.save(articoloCarrello);
		}
	}

	public List<ArticoloCarrello> mostraArticoliCarrello(Long idCliente) {
		Cliente cliente = getCliente(idCliente);
		Carrello carrello = getCarrello(cliente);
		return carrello.getArticoliCarrello();
	}

	private DescrizioneArticolo getDescrizioneArticolo(Long idDescArticolo) {
		if (descrizioneArticoloRepository.findById(idDescArticolo).isEmpty())
			throw new NoSuchElementException("Nessun articolo trovato.");
		return descrizioneArticoloRepository.findById(idDescArticolo).get();
	}

	private Carrello getCarrello(Cliente cliente) {
		if (carrelloRepository.findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = carrelloRepository.findByCliente(cliente).get();
		articoloCarrelloRepository.findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		return carrello;
	}

	private Cliente getCliente(Long idCliente) {
		return loginChecker.checkCliente(idCliente);
	}

	private ArticoloCarrello getArticoloCarrello(Long idArticoloCarrello) {
		if (articoloCarrelloRepository.findById(idArticoloCarrello).isEmpty())
			throw new NoSuchElementException("Nessun articolo carrello trovato.");
		return articoloCarrelloRepository.findById(idArticoloCarrello).get();
	}
}