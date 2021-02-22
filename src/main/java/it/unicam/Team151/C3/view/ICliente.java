package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.controller.*;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
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
	ConfermaPrenotazioneHandler confermaPrenotazioneHandler;
	@Autowired
	GestioneCarrelloHandler gestioneCarrelloHandler;
	@Autowired
	RicercaArticoliHandler ricercaArticoliHandler;
	@Autowired
	RitiraPrenotazioneHandler ritiraPrenotazioneHandler;
	@Autowired
	VisualizzaPuntiConsegnaHandler visualizzaPuntiConsegnaHandler;
	@Autowired
	VisualizzaCategorieHandler visualizzaCategorieHandler;

	@GetMapping("mostraPuntiConsegna")
	public List<PuntoConsegna> elaboraPrenotazione(@RequestParam Long idCliente) {
		return visualizzaPuntiConsegnaHandler.getPuntiConsegna(idCliente);
	}

	@PostMapping("confermaPrenotazione")
	public Ricevuta confermaPrenotazione(@RequestParam Long idPuntoConsegna, @RequestParam Long idCliente) {
		return confermaPrenotazioneHandler.confermaPrenotazione(idPuntoConsegna, idCliente);
	}

	@PostMapping("ritiraPrenotazione")
	public void ritiraPrenotazione(@RequestParam Long idCliente, @RequestParam Long idPuntoConsegna, @RequestParam Long idArmadietto) {
		ritiraPrenotazioneHandler.ritiraPrenotazione(idCliente, idPuntoConsegna, idArmadietto);
	}

	@PostMapping("aggiungiArticolo")
	public void aggiungiArticoloCarrello(@RequestParam Long idDescArticolo, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.aggiungiArticoloCarrello(idDescArticolo, quantita, idCliente);
	}

	@PostMapping("rimuoviArticolo")
	public void rimuoviArticoloCarrello(@RequestParam Long idArticoloCarrello, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.rimuoviArticoloCarrello(idArticoloCarrello, quantita, idCliente);
	}

	@PostMapping("getCarrello")
	public List<ArticoloCarrello> mostraArticoliCarrello(@RequestParam Long idCliente){
		return gestioneCarrelloHandler.mostraArticoliCarrello(idCliente);
	}

	@GetMapping("mostraCategorie")
	public List<Categoria> ricercaArticoliCategoria(@RequestParam Long idCliente) {
		return visualizzaCategorieHandler.getCategorie(idCliente);
	}

	@PostMapping("scegliCategoria")
	public List<DescrizioneArticolo> scegliCategoria(@RequestParam Long idCliente, @RequestParam Long idCategoria) {
		return ricercaArticoliHandler.scegliCategoria(idCliente, idCategoria);
	}

	@GetMapping("puntiVendita")
	public List<PuntoVendita> ricercaArticoliPuntoVendita(@RequestParam Long idCliente) {
		return ricercaArticoliHandler.ricercaArticoliPuntoVendita(idCliente);
	}

	@PostMapping("scegliPuntoVendita")
	public List<DescrizioneArticolo> scegliPuntoVendita(@RequestParam Long idCliente, @RequestParam Long idPuntoVendita) {
		return ricercaArticoliHandler.scegliPuntoVendita(idCliente, idPuntoVendita);
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long idCliente) {
		logoutHandler.logout(idCliente);
	}
}