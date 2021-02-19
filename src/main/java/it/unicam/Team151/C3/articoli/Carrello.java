package it.unicam.Team151.C3.articoli;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.utenti.Cliente;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Carrello.class)
@Entity
public class Carrello {

	@Id
	@Column(name = "idCarrello")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private List<ArticoloCarrello> articoliCarrello = new ArrayList<>();
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Carrello() {
	}

	public Carrello(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotale() {
		double totale = 0.0;
		for (ArticoloCarrello articoloCarrello : articoliCarrello)
			totale += articoloCarrello.getPrezzo();
		return totale;
	}

	public void svuota() {
		this.articoliCarrello.clear();
	}

	//TODO - eliminare metodo(?)
	public ArticoloCarrello aggiungiArticoloCarrello(ArticoloCarrello articoloCarrello, int quantita) {
		ArticoloCarrello articoloCarrelloDaAggiungere = articoloCarrello;
		boolean flag = false;
		for (ArticoloCarrello artCart : articoliCarrello) {
			if (artCart.getId().equals(articoloCarrelloDaAggiungere.getId())) {
				artCart.setQuantita(artCart.getQuantita() + quantita);
				articoloCarrelloDaAggiungere = artCart;
				flag = true;
				break;
			}
		}
		if (!flag){
			this.articoliCarrello.add(articoloCarrelloDaAggiungere);
		}
		return articoloCarrelloDaAggiungere;
	}

	//TODO - eliminare metodo(?)
	public void rimuoviArticoloCarrello(ArticoloCarrello articoloCarrello, int quantita) {
		if (articoloCarrello.getQuantita() < quantita)
			throw new IllegalArgumentException("La quantità da rimuovere è errata");
		ArticoloCarrello articoloCarrelloDaRimuovere = articoloCarrello;
		if (articoloCarrello.getQuantita() == quantita)
			this.articoliCarrello.remove(articoloCarrello);
		else articoloCarrello.setQuantita(articoloCarrello.getQuantita() - quantita);
	}

	public List<ArticoloCarrello> getArticoliCarrello() {
		return this.articoliCarrello;
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}
}