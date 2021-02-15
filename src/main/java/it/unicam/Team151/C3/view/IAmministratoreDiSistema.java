package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.controller.GestionePuntiConsegnaHandler;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class IAmministratoreDiSistema {

	@Autowired
	GestionePuntiConsegnaHandler gestionePuntiConsegnaHandler;

	public void creaCategoria() {
		// TODO - implement IAmministratoreDiSistema.creaCategoria
		throw new UnsupportedOperationException();
	}

	public void aggiornaCategoria() {
		// TODO - implement IAmministratoreDiSistema.aggiornaCategoria
		throw new UnsupportedOperationException();
	}

	public void inserimentoDatiCategoriaDaCreare(String nome, String descrizione) {
		// TODO - implement IAmministratoreDiSistema.inserimentoDatiCategoriaDaCreare
		throw new UnsupportedOperationException();
	}

	public void inserimentoDatiCategoriaDaAggiornare(String nome, String descrizione) {
		// TODO - implement IAmministratoreDiSistema.inserimentoDatiCategoriaDaAggiornare
		throw new UnsupportedOperationException();
	}

	public Categoria selezioneCategoria(String idCategoria) {
		// TODO - implement IAmministratoreDiSistema.selezioneCategoria
		throw new UnsupportedOperationException();
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
	public void rimuoviPuntoConsegna(Long idPuntoConsegna) {
		gestionePuntiConsegnaHandler.rimuoviPuntoConsegna(idPuntoConsegna);
	}

	@GetMapping("getPuntiConsegna")
	public List<PuntoConsegna> getPuntiConsegna(){
		return gestionePuntiConsegnaHandler.getPuntiConsegna();
	}
}