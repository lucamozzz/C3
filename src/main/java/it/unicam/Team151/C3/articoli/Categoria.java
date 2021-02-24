package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

/**
 * Classe che rappresenta un oggetto Categoria.
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Categoria.class)
@Entity
public class Categoria {

	@Id
	@Column(name = "idCategoria")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;

	public Categoria() {
	}

	public Categoria(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}

	/**
	 * Metodo che ti restituisce l'id associato alla categoria
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che ti restituisce il nome associato alla categoria
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che ti restituisce la descrizione associata alla categoria
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Metodo che imposta un nome alla categoria
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che imposta una descrizione alla categoria
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}