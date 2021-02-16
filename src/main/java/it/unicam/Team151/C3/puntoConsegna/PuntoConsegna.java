package it.unicam.Team151.C3.puntoConsegna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
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

	public PuntoConsegna(String ubicazione, int nArmadietti) {
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

	public Armadietto assegnaArmadietto(Prenotazione prenotazione, List<Armadietto> armadietti) {
		Armadietto result= null;
		for (Armadietto armadietto : armadietti) {
			if (armadietto.isDisponibile()){
				armadietto.setDisponibilita(false);
				armadietto.riempiArmadietto(prenotazione);
				result=armadietto;
				break;
			}
		}
		return result;
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