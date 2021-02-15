package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.articoli.GestoreCarrello;
import it.unicam.Team151.C3.controller.*;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("cliente")
public class ICliente implements IUtenteAutenticato{

	@Autowired
	LogoutHandler logoutHandler;

	@Autowired
	ElaboraPrenotazioneHandler elaboraPrenotazioneHandler;
	@Autowired
	ConfermaPrenotazioneHandler confermaPrenotazioneHandler;
	@Autowired
	GestioneCarrelloHandler gestioneCarrelloHandler;
	@Autowired
	RicercaArticoliHandler ricercaArticoliHandler;

	/**
	 * metodo per l'avvio della procedura di prenotazione.
	 */
	@GetMapping("puntiConsegna")
	public List<PuntoConsegna> elaboraPrenotazione() {
		return elaboraPrenotazioneHandler.elaboraPrenotazione();
	}

	@PostMapping("confermaPrenotazione")
	public void confermaPrenotazione(@RequestParam Long idPuntoConsegna, @RequestParam Long idCliente) {
		confermaPrenotazioneHandler.confermaPrenotazione(idPuntoConsegna, idCliente);
	}

	public void ritiraPrenotazione(Long idArmadietto) {
		// TODO - implement ICliente.ritiraPrenotazione
		throw new UnsupportedOperationException();
	}

	@PostMapping("aggiungiArticolo")
	public void aggiungiArticoloCarrello(@RequestParam Long idDescArticolo, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.aggiungiArticoloCarrello(idDescArticolo, quantita, idCliente);
	}

	@PostMapping("rimuoviArticolo")
	public void rimuoviArticoloCarrello(@RequestParam Long idDescArticolo, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.rimuoviArticoloCarrello(idDescArticolo, quantita, idCliente);
	}

	@PostMapping("getCarrello")
	public List<ArticoloCarrello> mostraArticoliCarrello(@RequestParam Long idCliente){
		return gestioneCarrelloHandler.mostraArticoliCarrello(idCliente);
	}

	@GetMapping("categorie")
	public List<Categoria> ricercaArticoliCategoria() {
		return ricercaArticoliHandler.ricercaArticoliCategoria();
	}

	@PostMapping("scegliCategoria")
	public void scegliCategoria(@RequestParam Long idCategoria) {
		ricercaArticoliHandler.scegliCategoria(idCategoria);
	}

	@GetMapping("puntiVendita")
	public List<PuntoVendita> ricercaArticoliPuntoVendita() {
		return ricercaArticoliHandler.ricercaArticoliPuntoVendita();
	}

	@PostMapping("scegliPuntoVendita")
	public void scegliPuntoVendita(@RequestParam Long idPuntoVendita) {
		ricercaArticoliHandler.scegliPuntoVendita(idPuntoVendita);
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}