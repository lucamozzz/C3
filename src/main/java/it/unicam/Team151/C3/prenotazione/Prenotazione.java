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

/**
 * Classe che rappresenta un oggetto Prenotazione.
 * Ha la responsabilità di creator di oggetti Ricevuta e Pacco.
 */
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
	 * Metodo che restituisce il cliente associato alla prenotazione.
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Metodo che restituisce la ricevuta associata alla prenotazione.
	 */
	public Ricevuta getRicevuta() {
		return this.ricevuta;
	}

	/**
	 * Metodo che restituisce l'id associato alla prenotazione.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Metodo che restituisce lo stato in cui si trova la prenotazione.
	 */
	public Stato getStato() {
		return this.stato;
	}

	/**
	 * Metodo che restituisce il corriere incaricato della consegna della prenotazione.
	 */
	public Corriere getCorriere() {
		return this.corriere;
	}

	/**
	 * Metodo che restituisce la lista di pacchi contenuti nella prenotazione.
	 */
	public List<Pacco> getPacchi() {
		return this.pacchi;
	}

	/**
	 * Metodo che restituisce il punto consegna associato alla prenotazione.
	 */
	public PuntoConsegna getPuntoConsegna() {
		return this.puntoConsegna;
	}

	/**
	 * Metodo che imposta uno stato alla prenotazione.
	 */
	public void setStato(Stato nuovoStato) {
		this.stato = nuovoStato;
	}

	/**
	 * Metodo che associa un corriere alla prenotazione
	 */
	public void setCorriere(Corriere corriere) {
		this.corriere = corriere;
	}

	/**
	 * Metodo che crea tutti i pacchi associati alla prenotazione (metodo chiamato solamente nel costruttore della prenotazione).
	 * Ricorsivamente ogni pacco creato creerà a sua volta gli articoli.
	 */
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

	/**
	 * Metodo che crea una ricevuta
	 */
	public void createRicevuta(){
		this.ricevuta = new Ricevuta(this);
	}

	/**
	 * Metodo che imposta una ricevuta alla prenotazione
	 */
	public void setRicevuta(Ricevuta ricevuta) {
		this.ricevuta = ricevuta;
	}
}