package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import javax.persistence.*;

/**
 * Classe che rappresenta l'oggetto Articolo
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Articolo.class)
@Entity
public class Articolo {

	@Id
	@Column(name = "idArticolo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idDescrizioneArticolo")
	private DescrizioneArticolo descrizioneArticolo;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPacco")
	private Pacco pacco;

	public Articolo() {
	}

	public Articolo(DescrizioneArticolo descrizioneArticolo, Pacco pacco) {
		this.descrizioneArticolo = descrizioneArticolo;
		this.pacco = pacco;
	}

	/**
	 * Metodo che ti restituisce l'id associato all'articolo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che ti restituisce la descrizione articolo associata all'articolo
	 */
	public DescrizioneArticolo getDescrizioneArticolo() {
		return descrizioneArticolo;
	}

	/**
	 * Metodo che ti restituisce il pacco in cui è contenuto l'articolo
	 */
	public Pacco getPacco() {
		return pacco;
	}

	/**
	 * Metodo che assegna una descrizione articolo all'articolo
	 */
	public void setDescrizioneArticolo(DescrizioneArticolo descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

	/**
	 * Metodo che assegna un pacco all'articolo
	 */
	public void setPacco(Pacco pacco) {
		this.pacco = pacco;
	}

	@Override
	public String toString() {
		return "Articolo{" +
				"id=" + id +
				", descrizioneArticolo=" + descrizioneArticolo +
				", pacco=" + pacco +
				'}';
	}
}