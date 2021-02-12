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
	private int codice;

	public Ricevuta() {
	}

	public Ricevuta(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
		this.generateCodice();
	}

	public Long getId() {
		return id;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public int getCodice(){
		return codice;
	}

	public void generateCodice() {
		this.codice = (int)(Math.random() * (999999999 - 100000000 + 1) + 100000000);
	}
}