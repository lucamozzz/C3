package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.repositories.CarrelloRepository;
import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestoreCarrello {

	@Autowired
	CarrelloRepository carrelloRepository;

	private static GestoreCarrello instance;
	private List<Carrello> carrelli;

	private GestoreCarrello() {
		this.carrelli = new ArrayList<>();
	}

	public static GestoreCarrello getInstance() {
		if (instance == null)
			instance = new GestoreCarrello();
		return instance;
	}

	//Qui avevamo messo findById() ma in quel modo avrebbe ritornato il carrello
	// con idCarrello = idCliente invece che il carrello con idCliente = idCliente.
	// Perciò ho aggiunto il metodo findByCliente alla classe CarrelloRepository.

	//ALESSANDRO TESTA
	//ho cambiato questo metodo e vi spiego perche.
	//l'id del cliente non è lo stesso dell'id del carrello! se noi ci prendiamo un carrello passandogli
	//l'id del cliente ovviamente darà un eccezione perche nel repository non esiste nessun carrello che ha
	//un id uguale a quello del cliente.
	//la soluzione da discutere (potrebbe rimanere questa perche ha senso) è questa, ovvero
	//quando viene creato un cliente, viene creato contemporaneamente un Carrello e di conseguenza
	//l'id del carrello sarà l'id del cliente + 1.
	public Carrello getCarrello(Long idCliente) {
		return carrelloRepository.findById(idCliente+1).get();
	}

	public void createCarrello(Cliente cliente) {
		Carrello carrello = new Carrello(cliente);
		carrelloRepository.save(carrello);
	}

	public void saveCarrello(Carrello carrello) {
		carrelloRepository.save(carrello);
	}
}