package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.prenotazione.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pacco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<Articolo> articoli;
	private PuntoVendita puntoVendita;
	private Prenotazione prenotazione;
	private Stato stato;

	public Pacco() {
	}

	/**
	 * 
	 * @param prenotazione
	 * @param articoli
	 */
	public Pacco(Prenotazione prenotazione, List<ArticoloCarrello> articoli) {
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

	/**
	 * 
	 * @param stato
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

}