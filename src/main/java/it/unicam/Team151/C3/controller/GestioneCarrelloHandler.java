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
	@Autowired
	ArticoloCarrelloManager articoloCarrelloManager;


	//TODO ottimizzare - ma funziona
	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		if(articoloCarrelloRepository.findByDescrizioneArticolo(descrizioneArticolo).isPresent()){
			ArticoloCarrello articoloCarrelloDB = articoloCarrelloRepository.findByDescrizioneArticolo(descrizioneArticolo).get();
			if(quantita > descrizioneArticolo.getQuantita() || articoloCarrelloDB.getQuantita() + quantita > descrizioneArticolo.getQuantita())
				throw new IllegalStateException("quantita errata");
			articoloCarrelloDB.setQuantita(articoloCarrelloDB.getQuantita() + quantita);
			articoloCarrelloManager.saveArticoloCarrello(articoloCarrelloDB);
		}
		else {
			ArticoloCarrello newArticoloCarrello = articoloCarrelloManager.createArticoloCarrello(descrizioneArticolo, quantita, carrello);
			ArticoloCarrello articoloCarrello = carrello.aggiungiArticoloCarrello(newArticoloCarrello, quantita);
			articoloCarrelloManager.saveArticoloCarrello(articoloCarrello);
		}
	}

	//TODO ottimizzare - ma funziona
	public void rimuoviArticoloCarrello(Long idDescArticolo, int quantita, Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		ArticoloCarrello artCar = null;
		DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(idDescArticolo).get();
		for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello()) {
			if (articoloCarrello.getDescrizioneArticolo().equals(descrizioneArticolo))
				artCar = articoloCarrello;
		}
		carrello.rimuoviArticoloCarrello(artCar, quantita);
		if (artCar.getQuantita() == quantita)
			articoloCarrelloRepository.delete(artCar);
		else articoloCarrelloManager.saveArticoloCarrello(artCar);
	}

	public List<ArticoloCarrello> mostraArticoliCarrello(Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		return carrello.getArticoliCarrello();
	}
}