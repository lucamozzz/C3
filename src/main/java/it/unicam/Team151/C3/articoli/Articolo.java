package it.unicam.Team151.C3.articoli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private DescrizioneArticolo descrizioneArticolo;

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

}