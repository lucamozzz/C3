package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.controller.GestioneCategorieHandler;
import it.unicam.Team151.C3.controller.GestionePuntiConsegnaHandler;
import it.unicam.Team151.C3.controller.VisualizzaCategorieHandler;
import it.unicam.Team151.C3.controller.VisualizzaPuntiConsegnaHandler;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class IAmministratoreDiSistema {

	@Autowired
	GestionePuntiConsegnaHandler gestionePuntiConsegnaHandler;
	@Autowired
	GestioneCategorieHandler gestioneCategorieHandler;
	@Autowired
	VisualizzaPuntiConsegnaHandler visualizzaPuntiConsegnaHandler;
	@Autowired
	VisualizzaCategorieHandler visualizzaCategorieHandler;

	@GetMapping("mostraCategorie")
	public List<Categoria> getCategorie() {
		return visualizzaCategorieHandler.getCategorie((long) -1);
	}

	@PostMapping("creaCategoria")
	public void creaCategoria(@RequestParam String nome,@RequestParam String descrizione) {
		gestioneCategorieHandler.creaCategoria(nome, descrizione);
	}

	@PostMapping("aggiornaCategoria")
	public void aggiornaCategoria(@RequestParam Long idCategoria, @RequestParam String nome,@RequestParam String descrizione) {
		gestioneCategorieHandler.aggiornaCategoria(idCategoria,nome,descrizione);
	}

	@GetMapping("mostraPuntiConsegna")
	public List<PuntoConsegna> getPuntiConsegna(){
		return visualizzaPuntiConsegnaHandler.getPuntiConsegna((long) -1);
	}

	@PostMapping("aggiungiPuntoConsegna")
	public void aggiungiPuntoConsegna(@RequestParam String ubicazione, @RequestParam int numeroArmadietti) {
		gestionePuntiConsegnaHandler.aggiungiPuntoConsegna(ubicazione, numeroArmadietti);
	}

	@PostMapping("modificaPuntoConsegna")
	public void modificaPuntoConsegna(@RequestParam Long idPuntoConsegna, @RequestParam String ubicazione) {
		gestionePuntiConsegnaHandler.modificaPuntoConsegna(idPuntoConsegna, ubicazione);
	}

	@PostMapping("rimuoviPuntoConsegna")
	public void rimuoviPuntoConsegna(@RequestParam Long idPuntoConsegna) {
		gestionePuntiConsegnaHandler.rimuoviPuntoConsegna(idPuntoConsegna);
	}
}