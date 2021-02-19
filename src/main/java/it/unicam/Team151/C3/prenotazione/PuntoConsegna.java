package it.unicam.Team151.C3.prenotazione;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	public PuntoConsegna(String ubicazione) {
		this.ubicazione = ubicazione;
		this.armadietti = new ArrayList<>();
	}

	public Armadietto checkCodice(int codice) {
		for (Armadietto armadietto : armadietti)
			if (armadietto.getCodice() == codice)
				return armadietto;
		return null;
	}

	public Long getId() {
		return id;
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public List<Armadietto> getArmadietti() {
		return this.armadietti;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

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