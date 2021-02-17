package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.prenotazione.GestorePrenotazione;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
import it.unicam.Team151.C3.repositories.DescrizioneArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfermaPrenotazioneHandler {

	@Autowired
	GestoreCarrello gestoreCarrello;
	@Autowired
	GestorePuntoConsegna gestorePuntoConsegna;
	@Autowired
	GestorePrenotazione gestorePrenotazione;
	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	CatalogoArticoli catalogoArticoli;
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;

	public Ricevuta confermaPrenotazione(Long idPuntoConsegna, Long idCliente) {
		Carrello carrello = gestoreCarrello.getCarrello(idCliente);
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		if (this.checkDisponibilitaArticoli(carrello.getArticoliCarrello())){
			Prenotazione prenotazione = gestorePrenotazione.createPrenotazione(carrello, puntoConsegna);
			this.updateCatalogo(carrello);
			for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello())
				articoloCarrelloRepository.delete(articoloCarrello);
			carrello.svuota();
			gestoreCarrello.saveCarrello(carrello);
			return prenotazione.getRicevuta();
		} else throw new IllegalStateException("Uno o più articoli non disponibili.");
	}

	public boolean checkDisponibilitaArticoli(List<ArticoloCarrello> articoli) {
		for (ArticoloCarrello articoloCarrello : articoli) {
			DescrizioneArticolo descrizioneArticolo = descrizioneArticoloRepository.findById(articoloCarrello.getDescrizioneArticolo().getId()).get();
			if (articoloCarrello.getQuantita() > descrizioneArticolo.getQuantita())
				return false;
		}
		return true;
	}

	public void updateCatalogo(Carrello carrello) {
		for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello()){
			catalogoArticoli.modificaQuantitaArticolo(articoloCarrello.getDescrizioneArticolo().getId(), -articoloCarrello.getQuantita());
		}
	}

}