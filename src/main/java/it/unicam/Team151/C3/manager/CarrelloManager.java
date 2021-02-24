package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.repositories.CarrelloRepository;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe con la unica responsabilita di essere il creator di un oggetto Carrello
 */
@Service
public class CarrelloManager {

    @Autowired
    IRepositoryMaster repositoryMaster;

    /**
     * Metodo che dato un cliente, ti crea un carrello ad associato
     */
    public void createCarrello(Cliente cliente) {
        Carrello carrello = new Carrello(cliente);
        repositoryMaster.getCarrelloRepository().save(carrello);
    }
}
