package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

/**
 * Classe che rappresenta una descrizione articolo contenuta in uno specifico Carrello
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = ArticoloCarrello.class)
@Entity
public class ArticoloCarrello {

	@Id
	@Column(name = "idArticoloCarrello")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quantita;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idDescrizioneArticolo")
	private DescrizioneArticolo descrizioneArticolo;
	@ManyToOne
	@JoinColumn(name = "idCarrello")
	private Carrello carrello;

	public ArticoloCarrello() {
	}

	public ArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita, Carrello carrello) {
		this.quantita = quantita;
		this.descrizioneArticolo = descrizioneArticolo;
		this.carrello = carrello;
	}

	/**
	 * Metodo che ti restituisce l'id associato all'articolo carrello
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che ti restituisce la descrizione associata all'articolo carrello
	 */
	public DescrizioneArticolo getDescrizioneArticolo() {
		return this.descrizioneArticolo;
	}

	/**
	 * Metodo che ti restituisce la quantita associata all'articolo carrello
	 */
	public int getQuantita() {
		return this.quantita;
	}

	/**
	 * Metodo che ti restituisce il carrello in cui è contenuto l'articolo carrello
	 */
	public Carrello getCarrello(){
		return carrello;
	}

	/**
	 * Metodo che ti restituisce il prezzo totale dell'articolo carrello, considerandone anche la quantita
	 */
	public double getPrezzo() {
		return this.getDescrizioneArticolo().getPrezzo() * this.quantita;
	}

	/**
	 * Metodo che imposta una descrizione articolo all'articolo carrello
	 */
	public void setDescrizioneArticolo(DescrizioneArticolo descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

	/**
	 * Metodo che imposta un carrello all'articolo carrello
	 */
	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	/**
	 * Metodo che imposta una quantita di articoli all'articolo carrello
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "ArticoloCarrello{" +
				"id=" + id +
				", quantita=" + quantita +
				", descrizioneArticolo=" + descrizioneArticolo +
				", carrello=" + carrello +
				'}';
	}
}
