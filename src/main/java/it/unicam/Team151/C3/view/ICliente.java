package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.controller.*;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
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
	public List<PuntoConsegna> elaboraPrenotazione() {
		return visualizzaPuntiConsegnaHandler.getPuntiConsegna();
	}

	@PostMapping("confermaPrenotazione")
	public void confermaPrenotazione(@RequestParam Long idPuntoConsegna, @RequestParam Long idCliente) {
		confermaPrenotazioneHandler.confermaPrenotazione(idPuntoConsegna, idCliente);
	}

	@PostMapping("ritiraPrenotazione")
	public void ritiraPrenotazione(@RequestParam Long idPuntoConsegna, @RequestParam Long idArmadietto) {
		ritiraPrenotazioneHandler.ritiraPrenotazione(idPuntoConsegna, idArmadietto);
	}

	@PostMapping("aggiungiArticolo")
	public void aggiungiArticoloCarrello(@RequestParam Long idDescArticolo, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.aggiungiArticoloCarrello(idDescArticolo, quantita, idCliente);
	}

	//TODO - potrebbe prendere in input un idArticoloCarrello(?)
	@PostMapping("rimuoviArticolo")
	public void rimuoviArticoloCarrello(@RequestParam Long idDescArticolo, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.rimuoviArticoloCarrello(idDescArticolo, quantita, idCliente);
	}

	@PostMapping("getCarrello")
	public List<ArticoloCarrello> mostraArticoliCarrello(@RequestParam Long idCliente){
		return gestioneCarrelloHandler.mostraArticoliCarrello(idCliente);
	}

	@GetMapping("mostraCategorie")
	public List<Categoria> ricercaArticoliCategoria() {
		return visualizzaCategorieHandler.getCategorie();
	}

	//Metodo che mostra gli articoli per categoria
	@PostMapping("scegliCategoria")
	public List<DescrizioneArticolo> scegliCategoria(@RequestParam Long idCategoria) {
		return ricercaArticoliHandler.scegliCategoria(idCategoria);
	}

	//Metodo che prende tutti punti vendita
	@GetMapping("puntiVendita")
	public List<PuntoVendita> ricercaArticoliPuntoVendita() {
		return ricercaArticoliHandler.ricercaArticoliPuntoVendita();
	}

	//Metodo che mostra gli articoli per punto vendita
	@PostMapping("scegliPuntoVendita")
	public List<DescrizioneArticolo> scegliPuntoVendita(@RequestParam Long idPuntoVendita) {
		return ricercaArticoliHandler.scegliPuntoVendita(idPuntoVendita);
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}