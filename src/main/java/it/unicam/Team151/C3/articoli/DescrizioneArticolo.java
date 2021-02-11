package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import javax.persistence.*;
import java.util.List;

@Entity
public class DescrizioneArticolo {

	@Id
	@Column(name = "idDescrizioneArticolo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descrizione;
	private double prezzo;
	private int quantita;
	@OneToOne
	@JoinColumn(name = "idPuntoVendita")
	private PuntoVendita puntoVendita;
	@OneToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

	public DescrizioneArticolo() {
	}

	public DescrizioneArticolo(List<String> datiArticolo) {
		// TODO implementare costruttore
	}

	public Long getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public PuntoVendita getPuntoVendita() {
		return puntoVendita;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public void setPuntoVendita(PuntoVendita puntoVendita) {
		this.puntoVendita = puntoVendita;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}