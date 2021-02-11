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
	public Carrello getCarrello(Long idCliente) {
		return carrelloRepository.findByCliente(idCliente).get();
	}

	public void createCarrello(Cliente cliente) {
		Carrello carrello = new Carrello(cliente);
		carrelloRepository.save(carrello);
	}

	public void saveCarrello(Carrello carrello) {
		carrelloRepository.save(carrello);
	}
}