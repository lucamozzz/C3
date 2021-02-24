package it.unicam.Team151.C3.prenotazione;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

/**
 * Classe che rappresenta un oggetto Ricevuta.
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Ricevuta.class)
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

	/**
	 * Metodo che restituisce l'id associato alla ricevuta.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che restituisce la prenotazione associata alla ricevuta.
	 */
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	/**
	 * Metodo che restituisce il codice associato alla ricevuta.
	 */
	public int getCodice(){
		return codice;
	}
	/**
	 * Metodo che imposta l'id associato alla ricevuta.
	 */
	public void setCodice(int codice){
		this.codice = codice;
	}

	/**
	 * Metodo che genera automaticamente un nuovo id associato alla ricevuta.
	 */
	public void generateCodice() {
		this.codice = (int)(Math.random() * (999999999 - 100000000 + 1) + 100000000);
	}
}