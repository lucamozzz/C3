package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.utenti.Commerciante;

public class PuntoVendita {

	private Commerciante commerciante;
	private int ubicazione;
	private String nome;

	/**
	 * 
	 * @param nome
	 * @param commerciante
	 * @param ubicazione
	 */
	public PuntoVendita(String nome, Commerciante commerciante, String ubicazione) {
		// TODO - implement PuntoVendita.PuntoVendita
		throw new UnsupportedOperationException();
	}

	public Commerciante getCommerciante() {
		return this.commerciante;
	}

	/**
	 * 
	 * @param commerciante
	 */
	public void setCommerciante(Commerciante commerciante) {
		this.commerciante = commerciante;
	}

	public String getUbicazione() {
		// TODO - implement PuntoVendita.getUbicazione
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ubicazione
	 */
	public void setUbicazione(int ubicazione) {
		this.ubicazione = ubicazione;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		// TODO - implement PuntoVendita.setNome
		throw new UnsupportedOperationException();
	}

}