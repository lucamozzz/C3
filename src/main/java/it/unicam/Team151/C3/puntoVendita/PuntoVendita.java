package it.unicam.Team151.C3.puntoVendita;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.utenti.Commerciante;

import javax.persistence.*;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = PuntoVendita.class)
@Entity
public class PuntoVendita {

	@Id
	@Column(name = "idPuntoVendita",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "idCommerciante")
	private Commerciante commerciante;
	private String ubicazione;
	private String nome;

	public PuntoVendita(){
	}

	public PuntoVendita(Commerciante commerciante, String nome, String ubicazione) {
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