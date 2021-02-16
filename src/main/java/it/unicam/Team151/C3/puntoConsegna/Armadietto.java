package it.unicam.Team151.C3.puntoConsegna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.utenti.Commerciante;

import javax.persistence.*;
import java.util.List;

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
	@Transient
	private List<Pacco> pacchi;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPuntoConsegna")
	private PuntoConsegna puntoConsegna;

	public Armadietto(PuntoConsegna puntoConsegna) {
		this.puntoConsegna=puntoConsegna;
		disponibilita= true;
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

	public void setDisponibilita(boolean stato) {
		this.disponibilita = stato;
	}

	public boolean isDisponibile() {
		return this.disponibilita;
	}

}