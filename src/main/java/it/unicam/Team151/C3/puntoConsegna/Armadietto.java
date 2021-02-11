package it.unicam.Team151.C3.puntoConsegna;

import it.unicam.Team151.C3.puntoVendita.Pacco;

import javax.persistence.*;
import java.util.List;

@Entity
public class Armadietto {

	@Id
	@Column(name = "idArmadietto")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean disponibilita;
	@Transient
	private List<Pacco> pacchi;
	@ManyToOne
	@JoinColumn(name = "idPuntoConsegna")
	private PuntoConsegna puntoConsegna;

	public Armadietto() {
	}

	public void svuota() {
		this.pacchi.clear();
	}

	public PuntoConsegna getPuntoConsegna() {
		return this.puntoConsegna;
	}

	public void setPuntoConsegna(PuntoConsegna puntoConsegna) {
		this.puntoConsegna = puntoConsegna;
	}

	/**
	 * 
	 * @param stato
	 */
	public void setDisponibilita(boolean stato) {
		this.disponibilita = stato;
	}

	public boolean isDisponibile() {
		return this.disponibilita;
	}

}