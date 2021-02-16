package it.unicam.Team151.C3.puntoConsegna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.utenti.Commerciante;

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
		for (Armadietto armadietto : armadietti)
			if (armadietto.getCodice() == codice)
				return armadietto;
		return null;
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public List<Armadietto> getArmadietti() {
		return this.armadietti;
	}

	public void assegnaArmadietto(Prenotazione prenotazione) {
		// TODO - implement PuntoConsegna.assegnaArmadietto
		throw new UnsupportedOperationException();
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}
}