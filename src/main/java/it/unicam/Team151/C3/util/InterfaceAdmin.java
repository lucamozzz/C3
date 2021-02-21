package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;

public interface InterfaceAdmin {

    Categoria createCategoria(String nome, String descrizione);

    PuntoConsegna createPuntoConsegna(String ubicazione, int nArmadietti);

}
