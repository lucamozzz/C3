package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.manager.ArticoloCarrelloManager;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
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
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;

	//TODO ottimizzare
	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		ArticoloCarrello newArticoloCarrello = ArticoloCarrelloManager.getInstance().createArticoloCarrello(descrizioneArticolo, quantita, carrello);
		ArticoloCarrello articoloCarrello = carrello.aggiungiArticoloCarrello(newArticoloCarrello, quantita);
		ArticoloCarrelloManager.getInstance().saveArticoloCarrello(articoloCarrello);
	}

	//TODO ottimizzare
	public void rimuoviArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		ArticoloCarrello artCar = null;
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello()) {
			if (articoloCarrello.getDescrizioneArticolo().equals(descrizioneArticolo))
				artCar = articoloCarrello;
		}
		carrello.rimuoviArticoloCarrello(artCar, quantita);
		if (artCar.getDescrizioneArticolo().getQuantita() == quantita)
			articoloCarrelloRepository.delete(artCar);
		else ArticoloCarrelloManager.getInstance().saveArticoloCarrello(artCar);
	}

	public List<ArticoloCarrello> mostraArticoliCarrello(Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		return carrello.getArticoliCarrello();
	}
}