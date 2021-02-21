package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
public class AmministratoreDiSistema implements InterfaceAdmin{
    @Override
    public Categoria createCategoria(String nome, String descrizione) {
        return new Categoria(nome, descrizione);
    }

    @Override
    public PuntoConsegna createPuntoConsegna(String ubicazione, int nArmadietti) {
        return null;
    }
}