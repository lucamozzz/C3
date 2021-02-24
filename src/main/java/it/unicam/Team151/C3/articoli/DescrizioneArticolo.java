package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import javax.persistence.*;

/**
 * Classe che rappresenta un oggetto DescrizioneArticolo.
 */

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = DescrizioneArticolo.class)
@Entity
public class DescrizioneArticolo {

	@Id
	@Column(name = "idDescrizioneArticolo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	private double prezzo;
	private int quantita;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idPuntoVendita")
	private PuntoVendita puntoVendita;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

	public DescrizioneArticolo() {
	}

	public DescrizioneArticolo(String nome, String descrizione, double prezzo, int quantita, PuntoVendita puntoVendita, Categoria categoria) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.puntoVendita = puntoVendita;
		this.categoria = categoria;
	}

	/**
	 * Metodo che ti restituisce l'id associato alla descrizione articolo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo che ti restituisce la descrizione associato alla descrizione articolo
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Metodo che ti restituisce il prezzo associato alla descrizione articolo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Metodo che ti restituisce l'id associato alla descrizione articolo
	 */
	public int getQuantita() {
		return quantita;
	}

	/**
	 * Metodo che ti restituisce il punto vendita associato alla descrizione articolo
	 */
	public PuntoVendita getPuntoVendita() {
		return puntoVendita;
	}

	/**
	 * Metodo che ti restituisce la categoria associata alla descrizione articolo
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Metodo che imposta la descrizione alla descrizione articolo
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Metodo che imposta il prezzo alla descrizione articolo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Metodo che imposta la quantita alla descrizione articolo
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	/**
	 * Metodo che imposta il punto vendita alla descrizione articolo
	 */
	public void setPuntoVendita(PuntoVendita puntoVendita) {
		this.puntoVendita = puntoVendita;
	}

	/**
	 * Metodo che imposta la categoria alla descrizione articolo
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Metodo che imposta il nome alla descrizione articolo
	 */
    public void setNome(String nome) {
		this.nome = nome;
    }
}