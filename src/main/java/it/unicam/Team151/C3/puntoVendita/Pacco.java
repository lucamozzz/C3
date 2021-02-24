package it.unicam.Team151.C3.puntoVendita;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Stato;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un oggetto Pacco.
 * Ha la responsabilita di creator di oggetti Articolo.
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Pacco.class)
@Entity
public class Pacco {

	@Id
	@Column(name = "idPacco")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private List<Articolo> articoli = new ArrayList<>();
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPuntoVendita")
	private PuntoVendita puntoVendita;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPrenotazione")
	private Prenotazione prenotazione;
	private Stato stato;

	public Pacco() {
	}

	public Pacco(Prenotazione prenotazione, List<ArticoloCarrello> articoliCarrello) {
		this.puntoVendita = articoliCarrello.get(0).getDescrizioneArticolo().getPuntoVendita();
		this.prenotazione = prenotazione;
		this.stato = Stato.PresoInCarico;
		for (ArticoloCarrello articoloCarrello : articoliCarrello) {
			for (int i = 0; i < articoloCarrello.getQuantita(); i++)
				this.articoli.add(new Articolo(articoloCarrello.getDescrizioneArticolo(), this));
		}
	}

	/**
	 * Metodo che restituisce gli articoli relativi al pacco.
	 */
	public List<Articolo> getArticoli() {
		return this.articoli;
	}

	/**
	 * Metodo che restituisce il punto vendita associato al pacco.
	 */
	public PuntoVendita getPuntoVendita() {
		return this.puntoVendita;
	}

	/**
	 * Metodo che restituisce la prenotazione associata al pacco.
	 */
	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}

	/**
	 * Metodo che restituisce l'id associato al pacco.
	 */
	public Long getId(){ return this.id; }

	/**
	 * Metodo che restituisce lo stato in cui si trova il pacco.
	 */
	public Stato getStato() {
		return this.stato;
	}

	/**
	 * Metodo che imposta lo stato associato al pacco.
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

}