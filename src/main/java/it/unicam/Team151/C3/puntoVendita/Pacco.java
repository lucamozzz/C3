package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.prenotazione.*;
import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import javax.persistence.*;
import java.util.List;

@Entity
public class Pacco {

	@Id
	@Column(name = "idPacco")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private List<Articolo> articoli;
	@Transient
	private PuntoVendita puntoVendita;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPrenotazione")
	private Prenotazione prenotazione;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idArmadietto")
	private Armadietto armadietto;
	private Stato stato;

	public Pacco() {
	}

	public Pacco(Prenotazione prenotazione, List<ArticoloCarrello> articoli) {
		this.puntoVendita = articoli.get(0).getDescrizioneArticolo().getPuntoVendita();
		this.armadietto = null;
		this.prenotazione = prenotazione;
		for (ArticoloCarrello articoloCarrello : articoli) {
			for (int i = 0; i < articoloCarrello.getQuantita(); i++)
				this.articoli.add(new Articolo(articoloCarrello.getDescrizioneArticolo()));
		}
	}

	/**
	 * metodo che restituisce gli articoli relativi alla prenotazione in un punto vendita.
	 */
	public List<Articolo> getArticoli() {
		return this.articoli;
	}

	public PuntoVendita getPuntoVendita() {
		return this.puntoVendita;
	}

	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}

	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

}