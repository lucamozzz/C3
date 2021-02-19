package it.unicam.Team151.C3.utenti;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;

public interface InterfaceCommerciante extends UtenteAutenticato{

    PuntoVendita createPuntoVendita(String nome, String ubicazione);
}
