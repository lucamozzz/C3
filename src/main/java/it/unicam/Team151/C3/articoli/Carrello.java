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

	public List<ArticoloCarrello> getArticoliCarrello() {
		return this.articoliCarrello;
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public ArticoloCarrello createArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita){
		return new ArticoloCarrello(descrizioneArticolo, quantita, this);
	}
}