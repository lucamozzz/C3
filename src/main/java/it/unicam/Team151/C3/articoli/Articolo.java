package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.puntoVendita.Pacco;

import javax.persistence.*;

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

	public Articolo(DescrizioneArticolo descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

	public Long getId() {
		return id;
	}

	public DescrizioneArticolo getDescrizioneArticolo() {
		return descrizioneArticolo;
	}

	public Pacco getPacco() {
		return pacco;
	}

	public void setDescrizioneArticolo(DescrizioneArticolo descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

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