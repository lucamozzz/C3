package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.controller.*;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe che ha la responsabilità di controller dell'oggetto AmminstratoreDiSistema
 */
@RestController
@RequestMapping("admin")
public class IAmministratoreDiSistema implements IUtenteAutenticato{

	@Autowired
	GestionePuntiConsegnaHandler gestionePuntiConsegnaHandler;
	@Autowired
	GestioneCategorieHandler gestioneCategorieHandler;
	@Autowired
	VisualizzaPuntiConsegnaHandler visualizzaPuntiConsegnaHandler;
	@Autowired
	VisualizzaCategorieHandler visualizzaCategorieHandler;
	@Autowired
	LogoutHandler logoutHandler;

	/**
	 * Metodo che permette all'admin di visualizzare le categorie di articoli in C3
	 */
	@GetMapping("mostraCategorie")
	public List<Categoria> getCategorie() {
		return visualizzaCategorieHandler.getCategorie((long) -1);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Categorie - Crea categoria'
	 */
	@PostMapping("creaCategoria")
	public void creaCategoria(@RequestParam String nome,@RequestParam String descrizione) {
		gestioneCategorieHandler.creaCategoria(nome, descrizione);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Categorie - Aggiorna categoria'
	 */
	@PostMapping("aggiornaCategoria")
	public void aggiornaCategoria(@RequestParam Long idCategoria, @RequestParam String nome,@RequestParam String descrizione) {
		gestioneCategorieHandler.aggiornaCategoria(idCategoria,nome,descrizione);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Visualizza Punti Consegna'
	 */
	@GetMapping("mostraPuntiConsegna")
	public List<PuntoConsegna> getPuntiConsegna(){
		return visualizzaPuntiConsegnaHandler.getPuntiConsegna((long) -1);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Punti consegna - Aggiungi punti consegna'
	 */
	@PostMapping("aggiungiPuntoConsegna")
	public void aggiungiPuntoConsegna(@RequestParam String ubicazione, @RequestParam int numeroArmadietti) {
		gestionePuntiConsegnaHandler.aggiungiPuntoConsegna(ubicazione, numeroArmadietti);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Punti consegna - Modifica punti consegna'
	 */
	@PostMapping("modificaPuntoConsegna")
	public void modificaPuntoConsegna(@RequestParam Long idPuntoConsegna, @RequestParam String ubicazione) {
		gestionePuntiConsegnaHandler.modificaPuntoConsegna(idPuntoConsegna, ubicazione);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Punti consegna - Rimuovi punti consegna'
	 */
	@PostMapping("rimuoviPuntoConsegna")
	public void rimuoviPuntoConsegna(@RequestParam Long idPuntoConsegna) {
		gestionePuntiConsegnaHandler.rimuoviPuntoConsegna(idPuntoConsegna);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Logout'
	 */
	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}