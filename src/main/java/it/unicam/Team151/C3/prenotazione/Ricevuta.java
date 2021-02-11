package it.unicam.Team151.C3.prenotazione;

import javax.persistence.*;

@Entity
public class Ricevuta {

	@Id
	@Column(name = "idRicevuta")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPrenotazione")
	private Prenotazione prenotazione;

	public Ricevuta() {
	}

	public Ricevuta(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public Long getId() {
		return id;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	//TODO implementare il resto
}