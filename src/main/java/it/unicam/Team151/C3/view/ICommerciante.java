package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.prenotazione.*;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ICommerciante implements IUtenteAutenticato{
	@Autowired
	private GestorePrenotazione gestorePrenotazione;

	@Autowired
	LogoutHandler logoutHandler;


	/**
	 * 
	 * @param idPrenotazione
	 */
	public void confermaAcquisto(Long idPrenotazione) {
		// TODO - implement ICommerciante.confermaAcquisto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idPacco
	 */
	public void confermaPagamento(Long idPacco) {
		// TODO - implement ICommerciante.confermaPagamento
		throw new UnsupportedOperationException();
	}

	public void aggiungiArticolo() {
		// TODO - implement ICommerciante.aggiungiArticolo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param descArticolo
	 */
	public void inserimentoDatiArticoloDaAggiungere(DescrizioneArticolo descArticolo) {
		// TODO - implement ICommerciante.inserimentoDatiArticoloDaAggiungere
		throw new UnsupportedOperationException();
	}

	public void modificaArticolo() {
		// TODO - implement ICommerciante.modificaArticolo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idArticolo
	 */
	public void scegliArticolo(Long idArticolo) {
		// TODO - implement ICommerciante.scegliArticolo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param descArticolo
	 */
	public void inserimentoDatiArticoloDaModificare(DescrizioneArticolo descArticolo) {
		// TODO - implement ICommerciante.inserimentoDatiArticoloDaModificare
		throw new UnsupportedOperationException();
	}

	public void rimuoviArticolo() {
		// TODO - implement ICommerciante.rimuoviArticolo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idDescArticolo
	 */
	public void rimozioneArticolo(Long idDescArticolo) {
		// TODO - implement ICommerciante.rimozioneArticolo
		throw new UnsupportedOperationException();
	}

	public void aggiungiPuntoVendita() {
		// TODO - implement ICommerciante.aggiungiPuntoVendita
		throw new UnsupportedOperationException();
	}

	public void modificaPuntoVendita() {
		// TODO - implement ICommerciante.modificaPuntoVendita
		throw new UnsupportedOperationException();
	}

	public void rimuoviPuntoVendita() {
		// TODO - implement ICommerciante.rimuoviPuntoVendita
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param commerciante
	 * @param ubicazione
	 */
	public void inserimentoDatiPuntoVenditaDaAggiungere(String nome, Commerciante commerciante, int ubicazione) {
		// TODO - implement ICommerciante.inserimentoDatiPuntoVenditaDaAggiungere
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param commerciante
	 * @param ubicazione
	 */
	public void inserimentoDatiPuntoVenditaDaModificare(String nome, Commerciante commerciante, int ubicazione) {
		// TODO - implement ICommerciante.inserimentoDatiPuntoVenditaDaModificare
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idPuntoVendita
	 */
	public PuntoVendita selezionaPuntoVendita(Long idPuntoVendita) {
		// TODO - implement ICommerciante.selezionaPuntoVendita
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idPuntoVendita
	 */
	public void rimozionePuntoVendita(Long idPuntoVendita) {
		// TODO - implement ICommerciante.rimozionePuntoVendita
		throw new UnsupportedOperationException();
	}

	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}