package it.unicam.Team151.C3.prenotazione;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Classe che rappresenta un oggetto Armadietto, che ha il compito di contenere le prenotazioni consegnate dal corriere,
 * in attesa che il cliente la venga a ritirare
 */

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Armadietto.class)
@Entity
public class Armadietto {

	@Id
	@Column(name = "idArmadietto")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean disponibilita;
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "idPrenotazione")
	private Prenotazione prenotazione;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPuntoConsegna")
	private PuntoConsegna puntoConsegna;
	private int codice;

	public Armadietto() {
	}

	public Armadietto(PuntoConsegna puntoConsegna) {
		this.puntoConsegna = puntoConsegna;
		this.disponibilita= true;
		this.codice = (int) (Math.random() * (999999 - 100000)) + 100000;
		this.prenotazione = null;
	}

	/**
	 * Metodo che restituisce l'id dell'oggetto armadietto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che svuota l'armadietto dalla prenotazione associata
	 */
	public void svuota() {
		this.prenotazione = null;
	}

	/**
	 * Metodo che restituisce il punto di consegna in cui è situato l'armadietto
	 */
	public PuntoConsegna getPuntoConsegna() {
		return this.puntoConsegna;
	}

	/**
	 * Metodo che associa un punto consegna all'armadietto
	 */
	public void setPuntoConsegna(PuntoConsegna puntoConsegna) {
		this.puntoConsegna = puntoConsegna;
	}

	/**
	 * Metodo che cambia la disponibilita dell'armadietto
	 */
	public void setDisponibilita(boolean stato) {
		this.disponibilita = stato;
	}

	/**
	 * Metodo che ti restituisce la disponibilita dell'armadietto
	 */
	public boolean isDisponibile() {
		return this.disponibilita;
	}

	/**
	 * Metodo che ti restituisce il codice associato all'armadietto
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Metodo che ti genera un nuovo codice associato all'armadietto
	 */
	public void resetCodice() {
		this.codice = (int) (Math.random() * (999999 - 100000)) + 100000;
	}

	/**
	 * Metodo che ti restituisce la prenotazione associata all'armadietto
	 */
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	/**
	 * Metodo che 'riempie' l'armadietto di una prenotazione
	 */
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	@Override
	public String toString() {
		return "Armadietto{" +
				"id=" + id +
				", disponibilita=" + disponibilita +
				", prenotazione=" + prenotazione +
				", puntoConsegna=" + puntoConsegna +
				", codice=" + codice +
				'}';
	}
}