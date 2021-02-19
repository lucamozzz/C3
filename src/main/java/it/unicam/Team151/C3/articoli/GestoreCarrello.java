package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
import it.unicam.Team151.C3.repositories.CarrelloRepository;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.GestoreCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class GestoreCarrello implements IGestore<Carrello> {

	@Autowired
	CarrelloRepository carrelloRepository;
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;
	@Autowired
	GestoreCliente gestoreCliente;

	private static GestoreCarrello instance;

	private GestoreCarrello() {}

	public static GestoreCarrello getInstance() {
		if (instance == null)
			instance = new GestoreCarrello();
		return instance;
	}

	@Override
	public Carrello get(Long idCliente) {
		Cliente cliente = gestoreCliente.get(idCliente);
		if (carrelloRepository.findByCliente(cliente).isEmpty())
			throw new NoSuchElementException("Nessun carrello trovato.");
		Carrello carrello = carrelloRepository.findByCliente(cliente).get();
		articoloCarrelloRepository.findAllByCarrello(carrello).forEach(carrello.getArticoliCarrello()::add);
		return carrello;
	}

	@Override
	public void save(Carrello carrello) {
		carrelloRepository.save(carrello);
	}

	@Override
	public void delete(Carrello carrello) {
		carrelloRepository.delete(carrello);
	}
}