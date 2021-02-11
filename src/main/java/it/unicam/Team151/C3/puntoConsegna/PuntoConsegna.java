package it.unicam.Team151.C3.puntoConsegna;

import it.unicam.Team151.C3.prenotazione.Prenotazione;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PuntoConsegna {

	@Id
	@Column(name = "idPuntoConsegna")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ubicazione;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Armadietto> armadietti;

	public PuntoConsegna() {
	}

	public PuntoConsegna(String ubicazione, int nArmadietti) {
		this.ubicazione = ubicazione;
		this.armadietti = new ArrayList<>();
		for (int i = 0; i < nArmadietti; i++)
			this.armadietti.add(new Armadietto());
	}

	/**
	 * metodo che restituisce true se il codice inserito dal cliente � corretto.
	 * @param codice
	 */
	public Armadietto checkCodice(int codice) {
		// TODO - implement PuntoConsegna.checkCodice
		throw new UnsupportedOperationException();
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public List<Armadietto> getArmadietti() {
		return this.armadietti;
	}

	/**
	 * 
	 * @param prenotazione
	 */
	public void assegnaArmadietto(Prenotazione prenotazione) {
		// TODO - implement PuntoConsegna.assegnaArmadietto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nArmadietti
	 */
	public void setArmadietti(int nArmadietti) {
		// TODO - implement PuntoConsegna.setArmadietti
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ubicazione
	 */
	public void setUbicazione(int ubicazione) {
		// TODO - implement PuntoConsegna.setUbicazione
		throw new UnsupportedOperationException();
	}

}