package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.manager.PrenotazioneManager;
import it.unicam.Team151.C3.prenotazione.GestoreRicevuta;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
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
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	CatalogoArticoli catalogoArticoli;
	@Autowired
	PrenotazioneManager prenotazioneManager;
	@Autowired
	GestoreArticoloCarrello gestoreArticoloCarrello;
	@Autowired
	GestoreRicevuta gestoreRicevuta;

	public Ricevuta confermaPrenotazione(Long idPuntoConsegna, Long idCliente) {
		Carrello carrello = gestoreCarrello.get(idCliente);
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		if (this.checkDisponibilitaArticoli(carrello.getArticoliCarrello())){
			Prenotazione prenotazione = prenotazioneManager.createPrenotazione(carrello, puntoConsegna);
			this.updateCatalogo(carrello);
			for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello())
				gestoreArticoloCarrello.delete(articoloCarrello);
			carrello.svuota();
			gestoreCarrello.save(carrello);
			prenotazione.createRicevuta();
			gestoreRicevuta.save(prenotazione.getRicevuta());
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
			DescrizioneArticolo descrizioneArticolo = catalogoArticoli.get(articoloCarrello.getDescrizioneArticolo().getId());
			descrizioneArticolo.setQuantita(descrizioneArticolo.getQuantita() - articoloCarrello.getQuantita());
			catalogoArticoli.save(descrizioneArticolo);
		}
	}

	public List<PuntoConsegna> elaboraPrenotazione() {
		return gestorePuntoConsegna.getPuntiConsegna();
	}
}