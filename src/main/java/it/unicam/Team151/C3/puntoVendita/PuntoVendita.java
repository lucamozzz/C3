package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.utenti.Commerciante;

import javax.persistence.*;

@Entity
public class PuntoVendita {

	@Id
	@Column(name = "idPuntoVendita")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCommerciante")
	private Commerciante commerciante;
	private String ubicazione;
	private String nome;

	public PuntoVendita() {
	}

	public PuntoVendita(String nome, Commerciante commerciante, String ubicazione) {
		this.nome = nome;
		this.commerciante = commerciante;
		this.ubicazione = ubicazione;
	}

	public Long getId() {
		return id;
	}

	public Commerciante getCommerciante() {
		return commerciante;
	}

	public String getUbicazione() {
		return ubicazione;
	}

	public String getNome() {
		return nome;
	}

	public void setCommerciante(Commerciante commerciante) {
		this.commerciante = commerciante;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}