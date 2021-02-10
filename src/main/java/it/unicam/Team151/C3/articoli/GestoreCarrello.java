package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.repositories.CarrelloRepository;
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

	public Carrello getCarrello(Long idCliente) {
		return carrelloRepository.findById(idCliente).get();
	}

	public void createCarrello(Long id) {
		Carrello carrello = new Carrello(id);
		carrelloRepository.save(carrello);
	}

	public void saveCarrello(Carrello carrello) {
		carrelloRepository.save(carrello);
	}
}