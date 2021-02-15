package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.controller.GestionePuntiVenditaHandler;
import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.prenotazione.*;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commerciante")
public class ICommerciante implements IUtenteAutenticato{

	@Autowired
	GestionePuntiVenditaHandler gestionePuntiVenditaHandler;

	@Autowired
	private GestorePrenotazione gestorePrenotazione;

	@Autowired
	LogoutHandler logoutHandler;

	public void confermaAcquisto(Long idPrenotazione) {
		// TODO - implement ICommerciante.confermaAcquisto
		throw new UnsupportedOperationException();
	}

	public void confermaPagamento(Long idPacco) {
		// TODO - implement ICommerciante.confermaPagamento
		throw new UnsupportedOperationException();
	}

	public void aggiungiArticolo() {
		// TODO - implement ICommerciante.aggiungiArticolo
		throw new UnsupportedOperationException();
	}

	public void inserimentoDatiArticoloDaAggiungere(DescrizioneArticolo descArticolo) {
		// TODO - implement ICommerciante.inserimentoDatiArticoloDaAggiungere
		throw new UnsupportedOperationException();
	}

	public void modificaArticolo() {
		// TODO - implement ICommerciante.modificaArticolo
		throw new UnsupportedOperationException();
	}

	public void scegliArticolo(Long idArticolo) {
		// TODO - implement ICommerciante.scegliArticolo
		throw new UnsupportedOperationException();
	}

	public void inserimentoDatiArticoloDaModificare(DescrizioneArticolo descArticolo) {
		// TODO - implement ICommerciante.inserimentoDatiArticoloDaModificare
		throw new UnsupportedOperationException();
	}

	public void rimuoviArticolo() {
		// TODO - implement ICommerciante.rimuoviArticolo
		throw new UnsupportedOperationException();
	}

	public void rimozioneArticolo(Long idDescArticolo) {
		// TODO - implement ICommerciante.rimozioneArticolo
		throw new UnsupportedOperationException();
	}

	@PostMapping("getPuntiVendita")
	public List<PuntoVendita> getPuntiVendita(@RequestParam Long idCommerciante){
		return gestionePuntiVenditaHandler.getPuntiVendita(idCommerciante);
	}

	@PostMapping("aggiungiPuntoVendita")
	public void aggiungiPuntoVendita(@RequestParam Long idCommerciante, @RequestParam String nome, @RequestParam String ubicazione){
		gestionePuntiVenditaHandler.aggiungiPuntoVendita(idCommerciante, nome, ubicazione);
	}

	@PostMapping("modificaPuntoVendita")
	public void modificaPuntoVendita(@RequestParam Long idPuntoVendita, @RequestParam String nome, @RequestParam String ubicazione) {
		gestionePuntiVenditaHandler.modificaPuntoVendita(idPuntoVendita, nome, ubicazione);
	}

	@PostMapping("rimuoviPuntoVendita")
	public void rimuoviPuntoVendita(@RequestParam Long idPuntoVendita) {
		gestionePuntiVenditaHandler.rimuoviPuntoVendita(idPuntoVendita);
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}