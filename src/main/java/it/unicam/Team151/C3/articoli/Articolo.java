package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.utenti.Commerciante;

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
		return this.id;
	}

	public DescrizioneArticolo getDescrizioneArticolo() {
		return this.descrizioneArticolo;
	}

	public Pacco getIdPacco(){
		return this.pacco;
	}

}