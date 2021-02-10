package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.utenti.Cliente;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Carrello {

	@Id
	private Long id;
	@OneToMany
	private List<ArticoloCarrello> articoliCarrello;

	public Carrello() {
	}

	public Carrello(Long id) {
		this.id = id;
	}

	public double getTotale() {
		double totale = 0.0;
		for (ArticoloCarrello articoloCarrello : articoliCarrello) {
			totale += articoloCarrello.getPrezzo();
		}
		return totale;
	}

	public void svuota() {
		this.articoliCarrello.clear();
	}

	/**
	 * 
	 * @param idDescArticolo
	 * @param quantita
	 */
	public void createArticoloCarrello(Long idDescArticolo, int quantita) {
		// TODO - implement Carrello.createArticoloCarrello
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idDescArticolo
	 * @param quantita
	 */
	public void rimuoviArticoloCarrello(Long idDescArticolo, int quantita) {
		// TODO - implement Carrello.rimuoviArticoloCarrello
		throw new UnsupportedOperationException();
	}

	public List<ArticoloCarrello> getArticoliCarrello() {
		return this.articoliCarrello;
	}

	public Long getId() {
		return id;
	}
}