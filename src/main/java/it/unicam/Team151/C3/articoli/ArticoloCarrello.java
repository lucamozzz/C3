package it.unicam.Team151.C3.articoli;

import javax.persistence.*;

@Entity
public class ArticoloCarrello {

	@Id
	@Column(name = "idArticoloCarrello")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quantita;
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "idDescrizioneArticolo")
	private DescrizioneArticolo descrizioneArticolo;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "idCarrello")
	private Carrello carrello;

	public ArticoloCarrello() {
	}

	public ArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita, Carrello carrello) {
		this.quantita = quantita;
		this.descrizioneArticolo = descrizioneArticolo;
		this.carrello = carrello;
	}

	public DescrizioneArticolo getDescrizioneArticolo() {
		return this.descrizioneArticolo;
	}

    public double getPrezzo() {
		return this.getDescrizioneArticolo().getPrezzo() * this.quantita;
    }

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	//ho aggiunto questo semplice get.
	public Carrello getCarrello(){
		return carrello;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ArticoloCarrello{" +
				"id=" + id +
				'}';
	}
}
