package it.unicam.Team151.C3.articoli;

import javax.persistence.*;

@Entity
public class Articolo {

	@Id
	@Column(name = "idArticolo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "idDescrizioneArticolo")
	private DescrizioneArticolo descrizioneArticolo;
	@ManyToOne
	@JoinColumn(name = "idPacco")
	private Long idPacco;

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