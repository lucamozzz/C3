package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;

//TODO implementare bene le cosine
public interface IManager<T> {

    Articolo create(DescrizioneArticolo descrizioneArticolo);

    void save(Articolo articolo);
}
