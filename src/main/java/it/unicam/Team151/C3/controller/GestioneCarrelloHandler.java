package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.manager.ArticoloCarrelloManager;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestioneCarrelloHandler {

	@Autowired
	GestoreCarrello gestoreCarrello;
	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;

	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		ArticoloCarrello articoloCarrello = ArticoloCarrelloManager.getInstance().createArticoloCarrello(descrizioneArticolo, quantita, carrello);
		carrello.aggiungiArticoloCarrello(articoloCarrello);
		ArticoloCarrelloManager.getInstance().saveArticoloCarrello(articoloCarrello);
	}

	public List<ArticoloCarrello> rimuoviArticoloCarrello(Long idCliente) {
		// TODO - implement GestioneCarrelloHandler.rimuoviArticoloCarrello
		throw new UnsupportedOperationException();
	}

	public void rimuoviArticoloCarrello(Long idDescArticolo, int quantita) {
		// TODO - implement GestioneCarrelloHandler.rimuoviArticoloCarrello
		throw new UnsupportedOperationException();
	}
}