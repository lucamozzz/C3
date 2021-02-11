package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.controller.ConfermaPrenotazioneHandler;
import it.unicam.Team151.C3.controller.ElaboraPrenotazioneHandler;
import it.unicam.Team151.C3.controller.LogoutHandler;
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

	public void aggiungiArticoloCarrello(Long idDescArticolo, int quantita) {
		// TODO - implement ICliente.aggiungiArticoloCarrello
		throw new UnsupportedOperationException();
	}

	public void rimuoviArticoloCarrello(Long idDescArticolo, int quantita) {
		// TODO - implement ICliente.rimuoviArticoloCarrello
		throw new UnsupportedOperationException();
	}

	public void rimuoviArticoloCarrello() {
		// TODO - implement ICliente.rimuoviArticoloCarrello
		throw new UnsupportedOperationException();
	}

	public List<Categoria> ricercaArticoliCategoria() {
		// TODO - implement ICliente.ricercaArticoliCategoria
		throw new UnsupportedOperationException();
	}

	public void scegliCategoria(Long idCategoria) {
		// TODO - implement ICliente.scegliCategoria
		throw new UnsupportedOperationException();
	}

	public List<PuntoVendita> ricercaArticoliPuntoVendita() {
		// TODO - implement ICliente.ricercaArticoliPuntoVendita
		throw new UnsupportedOperationException();
	}

	public void scegliPuntoVendita(Long idPuntoVendita) {
		// TODO - implement ICliente.scegliPuntoVendita
		throw new UnsupportedOperationException();
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}