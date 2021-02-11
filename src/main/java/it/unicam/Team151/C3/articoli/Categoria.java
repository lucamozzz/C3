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
		// TODO - implement Categoria.Categoria
		throw new UnsupportedOperationException();
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		// TODO - implement Categoria.getDescrizione
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescrizione(String description) {
		// TODO - implement Categoria.setDescrizione
		throw new UnsupportedOperationException();
	}

}