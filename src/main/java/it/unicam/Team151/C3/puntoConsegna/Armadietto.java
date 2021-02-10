package it.unicam.Team151.C3.puntoConsegna;

public class Armadietto {

	private int idArmadietto;
	/**
	 * attributo per verificare se un armadietto � disponibile o no per la consegna di una merce.
	 */
	private boolean disponibilita;
	private PuntoConsegna puntoConsegna;

	public PuntoConsegna getPuntoConsegna() {
		return this.puntoConsegna;
	}

	public void svuota() {
		// TODO - implement Armadietto.svuota
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param stato
	 */
	public void setDisponibilita(boolean stato) {
		this.disponibilita = stato;
	}

	public boolean isDisponibile() {
		// TODO - implement Armadietto.isDisponibile
		throw new UnsupportedOperationException();
	}

}