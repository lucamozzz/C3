package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

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
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCarrello")
	private Carrello carrello;

	public ArticoloCarrello() {
	}

	public ArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita, Carrello carrello) {
		this.quantita = quantita;
		this.descrizioneArticolo = descrizioneArticolo;
		this.carrello = carrello;
	}

	public Long getId() {
		return id;
	}

	public DescrizioneArticolo getDescrizioneArticolo() {
		return this.descrizioneArticolo;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public Carrello getCarrello(){
		return carrello;
	}

	public double getPrezzo() {
		return this.getDescrizioneArticolo().getPrezzo() * this.quantita;
	}

	public void setDescrizioneArticolo(DescrizioneArticolo descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

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
