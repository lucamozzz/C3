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

/**
 * Classe che ha la responsabilità di controller dell'oggetto Cliente
 */
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

	/**
	 * Metodo che da il via al caso d'uso 'Visualizza Punti Consegna'
	 */
	@GetMapping("mostraPuntiConsegna")
	public List<PuntoConsegna> elaboraPrenotazione(@RequestParam Long idCliente) {
		return visualizzaPuntiConsegnaHandler.getPuntiConsegna(idCliente);
	}


	/**
	 * Metodo che da il via al caso d'uso 'Conferma Prenotazione'
	 */
	@PostMapping("confermaPrenotazione")
	public Ricevuta confermaPrenotazione(@RequestParam Long idPuntoConsegna, @RequestParam Long idCliente) {
		return confermaPrenotazioneHandler.confermaPrenotazione(idPuntoConsegna, idCliente);
	}


	/**
	 * Metodo che da il via al caso d'uso 'Ritira Prenotazione'
	 */
	@PostMapping("ritiraPrenotazione")
	public void ritiraPrenotazione(@RequestParam Long idCliente, @RequestParam Long idPuntoConsegna, @RequestParam Long idArmadietto) {
		ritiraPrenotazioneHandler.ritiraPrenotazione(idCliente, idPuntoConsegna, idArmadietto);
	}


	/**
	 * Metodo che da il via al caso d'uso 'Gestione Carrello - Aggiungi articolo'
	 */
	@PostMapping("aggiungiArticolo")
	public void aggiungiArticoloCarrello(@RequestParam Long idDescArticolo, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.aggiungiArticoloCarrello(idDescArticolo, quantita, idCliente);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Carrello - Rimuovi articolo'
	 */
	@PostMapping("rimuoviArticolo")
	public void rimuoviArticoloCarrello(@RequestParam Long idArticoloCarrello, @RequestParam int quantita, @RequestParam Long idCliente) {
		gestioneCarrelloHandler.rimuoviArticoloCarrello(idArticoloCarrello, quantita, idCliente);
	}

	/**
	 * Metodo che permette al cliente di visualizzare il suo carrello
	 */
	@GetMapping("getCarrello")
	public List<ArticoloCarrello> mostraArticoliCarrello(@RequestParam Long idCliente){
		return gestioneCarrelloHandler.mostraArticoliCarrello(idCliente);
	}

	/**
	 * Metodo che permette al cliente di visualizzare le categorie di articoli in C3
	 */
	@GetMapping("mostraCategorie")
	public List<Categoria> getCategorie(@RequestParam Long idCliente) {
		return visualizzaCategorieHandler.getCategorie(idCliente);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Ricerca articoli - Per categoria'
	 */
	@GetMapping("scegliCategoria")
	public List<DescrizioneArticolo> scegliCategoria(@RequestParam Long idCliente, @RequestParam Long idCategoria) {
		return ricercaArticoliHandler.scegliCategoria(idCliente, idCategoria);
	}

	/**
	 * Metodo che permette al cliente di visualizzare i punti vendita in C3
	 */
	@GetMapping("puntiVendita")
	public List<PuntoVendita> getPuntiVendita(@RequestParam Long idCliente) {
		return ricercaArticoliHandler.getPuntiVendita(idCliente);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Ricerca articoli - Per punto vendita'
	 */
	@GetMapping("scegliPuntoVendita")
	public List<DescrizioneArticolo> scegliPuntoVendita(@RequestParam Long idCliente, @RequestParam Long idPuntoVendita) {
		return ricercaArticoliHandler.scegliPuntoVendita(idCliente, idPuntoVendita);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Logout'
	 */
	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long idCliente) {
		logoutHandler.logout(idCliente);
	}
}