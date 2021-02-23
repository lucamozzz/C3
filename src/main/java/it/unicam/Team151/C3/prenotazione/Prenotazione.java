package it.unicam.Team151.C3.prenotazione;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
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

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Prenotazione.class)
@Entity
public class Prenotazione {

	@Id
	@Column(name = "idPrenotazione")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private Ricevuta ricevuta;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	@Transient
	private List<Pacco> pacchi = new ArrayList<>();
	private Stato stato;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCorriere")
	private Corriere corriere;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPuntoConsegna")
	private PuntoConsegna puntoConsegna;

	public Prenotazione() {
	}

	public Prenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
		this.pacchi = this.createPacchi(carrello);
		this.cliente = carrello.getCliente();
		this.puntoConsegna = puntoConsegna;
		this.corriere = null;
		this.ricevuta = null;
		this.stato = Stato.PresoInCarico;
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
		return this.ricevuta;
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

	public List<Pacco> createPacchi(Carrello carrello){
		Set<PuntoVendita> puntiVendita = new HashSet<>();
		List<Pacco> pacchi = new ArrayList<>();
		for (ArticoloCarrello ac : carrello.getArticoliCarrello())
			puntiVendita.add(ac.getDescrizioneArticolo().getPuntoVendita());
		for (PuntoVendita puntoVendita : puntiVendita) {
			Pacco pacco = new Pacco(this, carrello.getArticoliCarrello().stream().filter(articoloCarrello -> articoloCarrello.
					getDescrizioneArticolo().
					getPuntoVendita().
					equals(puntoVendita)).
					collect(Collectors.toList()));
			pacchi.add(pacco);
		}
		return pacchi;
	}

	public void createRicevuta(){
		this.ricevuta = new Ricevuta(this);
	}

	public void setRicevuta(Ricevuta ricevuta) {
		this.ricevuta = ricevuta;
	}
}