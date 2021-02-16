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
	private List<Armadietto> armadietti;

	public PuntoConsegna() {
	}

	public PuntoConsegna(String ubicazione, int nArmadietti) {
		this.ubicazione = ubicazione;
		this.armadietti = new ArrayList<>();
		for (int i = 0; i < nArmadietti; i++)
			this.armadietti.add(new Armadietto(this));
	}

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

	public Long getId(){
		return this.id;
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
}