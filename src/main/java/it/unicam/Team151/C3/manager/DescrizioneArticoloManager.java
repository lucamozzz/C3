package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;

public class DescrizioneArticoloManager {

    public DescrizioneArticolo create(String nome, String descrizione, double prezzo, int quantita, PuntoVendita puntoVendita, Categoria categoria){
        return new DescrizioneArticolo(nome, descrizione, prezzo, quantita, puntoVendita, categoria);
    }
}
