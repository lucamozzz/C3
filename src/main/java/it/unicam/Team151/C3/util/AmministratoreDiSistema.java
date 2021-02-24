package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import org.springframework.stereotype.Service;

/**
 * Classe che rappresenta l'oggetto Amministratore di Sistema. Esso è responsabile di gestire gli oggetti Categoria e PuntoConsegna.
 */
@Service
public class AmministratoreDiSistema implements InterfaceAdmin{

    private boolean logged;

    /**
     * Metodo che permette all'admin di creare una nuova categoria
     */
    @Override
    public Categoria createCategoria(String nome, String descrizione) {
        return new Categoria(nome, descrizione);
    }

    /**
     * Metodo che permette all'admin di creare un nuovo punto consegna, indicando inoltre quanti armadietti deve avere
     */
    @Override
    public PuntoConsegna createPuntoConsegna(String ubicazione, int nArmadietti) {
        return new PuntoConsegna(ubicazione, nArmadietti);
    }

    /**
     * Metodo che cambia lo stato di log dell'admin.
     */
    @Override
    public void setLogged(boolean b) {
        this.logged = b;
    }

    /**
     * Metodo che indica se l'admin sia loggato o meno attualmente in C3.
     */
    @Override
    public boolean getLogged() {
        return logged;
    }
}