package it.unicam.Team151.C3.utenti;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;

public interface InterfaceCommerciante extends UtenteAutenticato{

    PuntoVendita createPuntoVendita(String nome, String ubicazione);

    DescrizioneArticolo createDescrizioneArticolo(String nome, String descrizione, double prezzo, int quantita, PuntoVendita puntoVendita, Categoria categoria);
}
