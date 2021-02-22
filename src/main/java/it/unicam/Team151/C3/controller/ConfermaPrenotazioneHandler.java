package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.manager.PrenotazioneManager;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.*;
import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

//TODO - master repository

@Service
public class ConfermaPrenotazioneHandler {

	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	PrenotazioneManager prenotazioneManager;
	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;
	@Autowired
	RicevutaRepository ricevutaRepository;
	@Autowired
	CarrelloRepository carrelloRepository;
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public Ricevuta confermaPrenotazione(Long idPuntoConsegna, Long idCliente) {
		if (clienteRepository.findById(idCliente).isEmpty())
			throw new NoSuchElementException("Nessun cliente trovato.");
		Cliente cliente = clienteRepository.findById(idCliente).get();
		if (carrelloRepository.findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = carrelloRepository.findByCliente(cliente).get();
		articoloCarrelloRepository.findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		if (puntoConsegnaRepository.findById(idPuntoConsegna).isEmpty())
			throw new NoSuchElementException("Nessun punto consegna trovato.");
		PuntoConsegna puntoConsegna = puntoConsegnaRepository.findById(idPuntoConsegna).get();
		if (this.checkDisponibilitaArticoli(carrello.getArticoliCarrello())){
			Prenotazione prenotazione = prenotazioneManager.createPrenotazione(carrello, puntoConsegna);
			this.updateCatalogo(carrello);
			for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello())
				articoloCarrelloRepository.delete(articoloCarrello);
			carrello.svuota();
			prenotazione.createRicevuta();
			ricevutaRepository.save(prenotazione.getRicevuta());
			return prenotazione.getRicevuta();
		} else throw new IllegalStateException("Uno o più articoli non disponibili.");
	}

	public boolean checkDisponibilitaArticoli(List<ArticoloCarrello> articoli) {
		for (ArticoloCarrello articoloCarrello : articoli) {
			DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(articoloCarrello);
			if (articoloCarrello.getQuantita() > descrizioneArticolo.getQuantita())
				return false;
		}
		return true;
	}

	public void updateCatalogo(Carrello carrello) {
		for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello()){
			DescrizioneArticolo descrizioneArticolo = getDescrizioneArticolo(articoloCarrello);
			descrizioneArticolo.setQuantita(descrizioneArticolo.getQuantita() - articoloCarrello.getQuantita());
			descrizioneArticoloRepository.save(descrizioneArticolo);
		}
	}

	private DescrizioneArticolo getDescrizioneArticolo(ArticoloCarrello articoloCarrello) {
		if (descrizioneArticoloRepository.findById(articoloCarrello.getDescrizioneArticolo().getId()).isEmpty())
			throw new NoSuchElementException("Nessuna descrizone articolo trovata.");
		return descrizioneArticoloRepository.findById(articoloCarrello.getDescrizioneArticolo().getId()).get();
	}
}