package it.unicam.Team151.C3.articoli;

import javax.persistence.*;

@Entity
public class ArticoloCarrello {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quantita;
	private DescrizioneArticolo descrizioneArticolo;

	public ArticoloCarrello() {
	}

	//TODO da rivedere
	public ArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita) {
		this.quantita = quantita;
		this.descrizioneArticolo = descrizioneArticolo;
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
}
