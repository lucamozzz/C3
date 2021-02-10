package it.unicam.Team151.C3.prenotazione;


import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Corriere;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idCliente;
	private Ricevuta ricevuta;
	@OneToMany
	private List<Pacco> pacchi;
	private Stato stato;
	private Corriere corriere;
	private PuntoConsegna puntoConsegna;

	public Prenotazione() {
	}

	//TODO un po' de refactoring
	public Prenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
		this.pacchi = new ArrayList<>();
		Set<PuntoVendita> puntiVendita = new HashSet<>();
		for (ArticoloCarrello articoloCarrello : carrello.getArticoliCarrello())
			puntiVendita.add(articoloCarrello.getDescrizioneArticolo().getPuntoVendita());
		for (PuntoVendita puntoVendita : puntiVendita)
			pacchi.add(new Pacco(this, carrello.getArticoliCarrello().stream().filter(articoloCarrello -> articoloCarrello.
																	getDescrizioneArticolo().
																	getPuntoVendita().
																	equals(puntoVendita)).
																	collect(Collectors.toList())));
		this.idCliente = carrello.getId();
		this.puntoConsegna = puntoConsegna;
		this.corriere = null;
		this.ricevuta = null;
	}

	/**
	 * getter del cliente della prenotazione.
	 */
	public Long getCliente() {
		return this.idCliente;
	}

	/**
	 * getter della ricevuta della prenotazione.
	 */
	public Ricevuta getRicevuta() {
		if (ricevuta == null)
			this.ricevuta = new Ricevuta(this);
		return this.ricevuta;
	}

	/**
	 * getter della lista degli articoli della prenotazione.
	 */
	public List<Articolo> getArticoli() {
		// TODO - implement Prenotazione.getArticoli
		throw new UnsupportedOperationException();
	}

	public Long getID() {
		// TODO - implement Prenotazione.getID
		throw new UnsupportedOperationException();
	}

	public Stato getStato() {
		return this.stato;
	}

	/**
	 * 
	 * @param nuovoStato
	 */
	public void setStato(Stato nuovoStato) {
		this.stato = nuovoStato;
	}

	/**
	 * 
	 * @param corriere
	 */
	public void setCorriere(Corriere corriere) {
		this.corriere = corriere;
	}

	public Corriere getCorriere() {
		return this.corriere;
	}

	public List<Pacco> getPacchi() {
		return this.pacchi;
	}

	public PuntoConsegna getPuntoConsegna() {
		// TODO - implement Prenotazione.getPuntoConsegna
		throw new UnsupportedOperationException();
	}

}