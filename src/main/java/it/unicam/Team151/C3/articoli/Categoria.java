package it.unicam.Team151.C3.articoli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {

	@Id
	@Column(name = "idCategoria")
	private Long id;
	private String nome;
	private String descrizione;

	public Categoria() {
	}

	public Categoria(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}