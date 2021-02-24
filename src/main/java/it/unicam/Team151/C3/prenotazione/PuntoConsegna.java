package it.unicam.Team151.C3.prenotazione;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un oggetto PuntoConsegna.
 * Essa ha la responsabilita di creator degli oggetti Armadietto.
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = PuntoConsegna.class)
@Entity
public class PuntoConsegna {

	@Id
	@Column(name = "idPuntoConsegna")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ubicazione;
	@Transient
	private List<Armadietto> armadietti = new ArrayList<>();

	public PuntoConsegna() {
	}

	public PuntoConsegna(String ubicazione, int nArmadietti) {
		this.ubicazione = ubicazione;
		for (int i =0; i < nArmadietti; i++)
			armadietti.add(new Armadietto(this));
	}

	/**
	 * Metodo che dato un codice un input, lo confronta con tutti i codici degli armadietti associati al punto consegna
	 * e restituisce l'armadietto corretto
	 */
	public Armadietto checkCodice(int codice) {
		for (Armadietto armadietto : armadietti)
			if (armadietto.getCodice() == codice)
				return armadietto;
		return null;
	}

	/**
	 * Metodo che restituisce l'id associato al punto consegna.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che restituisce l'ubicazione associata al punto consegna.
	 */
	public String getUbicazione() {
		return this.ubicazione;
	}

	/**
	 * Metodo che restituisce la lista degli armadietti contenuti nel punto consegna.
	 */
	public List<Armadietto> getArmadietti() {
		return this.armadietti;
	}

	/**
	 * Metodo che imposta l'ubicazione al punto consegna.
	 */
	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

	/**
	 * Metodo che svuota un armadietto presente nella lista degli armadietti
	 */
	public void liberaArmadietto(Armadietto armadietto) {
		for (Armadietto a : armadietti) {
			if (a.equals(armadietto)){
				armadietto.svuota();
				armadietto.setDisponibilita(true);
				armadietto.resetCodice();
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "PuntoConsegna{" +
				"id=" + id +
				", ubicazione='" + ubicazione + '\'' +
				", armadietti=" + armadietti +
				'}';
	}
}