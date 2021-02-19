package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.GestoreCarrello;
import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrelloManager {

    @Autowired
    GestoreCarrello gestoreCarrello;

    public void createCarrello(Cliente cliente) {
        Carrello carrello = new Carrello(cliente);
        gestoreCarrello.save(carrello);
    }
}
