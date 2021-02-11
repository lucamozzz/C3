package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Cliente;
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
	@Column(name = "idPrenotazione")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Ricevuta ricevuta;
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.ALL)
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
		this.cliente = carrello.getCliente();
		this.puntoConsegna = puntoConsegna;
		this.corriere = null;
		this.ricevuta = null;
	}

	/**
	 * getter del cliente della prenotazione.
	 */
	public Cliente getCliente() {
		return this.cliente;
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
		List<Articolo> articoli = new ArrayList<>();
		for (Pacco pacco : pacchi) {
			articoli.addAll(pacco.getArticoli());
		}
		return articoli;
	}

	public Long getID() {
		return this.id;
	}

	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato nuovoStato) {
		this.stato = nuovoStato;
	}

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
		return this.puntoConsegna;
	}
}