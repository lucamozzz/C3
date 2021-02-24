package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.utenti.Cliente;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Classe che rappresenta un oggetto Carrello. Esso è univoco per ogni oggetto Cliente.
 *  Ha la responsabilita di creator nei confronti della classe 'ArticoloCarrello'
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Carrello.class)
@Entity
public class Carrello {

	@Id
	@Column(name = "idCarrello")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private List<ArticoloCarrello> articoliCarrello = new ArrayList<>();
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Carrello() {
	}

	public Carrello(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Metodo che restituisce il costo totale di tutti gli articoli presenti nel carrello
	 */
	public double getTotale() {
		double totale = 0.0;
		for (ArticoloCarrello articoloCarrello : articoliCarrello)
			totale += articoloCarrello.getPrezzo();
		return totale;
	}

	/**
	 * Metodo che rimuove tutti gli articoli carrello presenti nel carrello
	 */
	public void svuota() {
		this.articoliCarrello.clear();
	}

	/**
	 * Metodo che ti restituisce tutti gli articolo carrello associati al carrello
	 */
	public List<ArticoloCarrello> getArticoliCarrello() {
		return this.articoliCarrello;
	}

	/**
	 * Metodo che ti restituisce l'id associato al carrello
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che ti restituisce il cliente associato alcarrello
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Metodo che aggiunge un articolo carrello al carrello
	 */
	public ArticoloCarrello createArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita){
		return new ArticoloCarrello(descrizioneArticolo, quantita, this);
	}
}