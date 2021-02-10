package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.utenti.Commerciante;

import java.util.List;

public class GestorePuntoVendita {

	private List<PuntoVendita> puntiVendita;

	/**
	 * 
	 * @param nome
	 * @param commerciante
	 * @param ubicazione
	 */
	public void createPuntoVendita(String nome, Commerciante commerciante, int ubicazione) {
		// TODO - implement GestorePuntoVendita.createPuntoVendita
		throw new UnsupportedOperationException();
	}

	public List<PuntoVendita> getPuntiVendita() {
		return this.puntiVendita;
	}

	/**
	 * 
	 * @param idCommerciante
	 */
	public List<PuntoVendita> getPuntiVendita(Long idCommerciante) {
		return this.puntiVendita;
	}

	/**
	 * 
	 * @param pvDaEliminare
	 */
	public void rimozionePuntoVendita(PuntoVendita pvDaEliminare) {
		// TODO - implement GestorePuntoVendita.rimozionePuntoVendita
		throw new UnsupportedOperationException();
	}

}