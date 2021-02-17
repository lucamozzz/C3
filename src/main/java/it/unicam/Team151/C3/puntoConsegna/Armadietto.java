package it.unicam.Team151.C3.puntoConsegna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import javax.persistence.*;

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

	public Long getId() {
		return id;
	}

	public void svuota() {
		this.prenotazione = null;
	}

	public PuntoConsegna getPuntoConsegna() {
		return this.puntoConsegna;
	}

	public void setPuntoConsegna(PuntoConsegna puntoConsegna) {
		this.puntoConsegna = puntoConsegna;
	}

	public void setDisponibilita(boolean stato) {
		this.disponibilita = stato;
	}

	public boolean isDisponibile() {
		return this.disponibilita;
	}

	public int getCodice() {
		return codice;
	}

	public void resetCodice() {
		this.codice = (int) (Math.random() * (999999 - 100000)) + 100000;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

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