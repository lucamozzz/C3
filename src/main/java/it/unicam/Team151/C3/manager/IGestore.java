package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Articolo;

import java.util.List;
import java.util.function.Predicate;

public interface IGestore<E> {

    E get(Long id);

    void save(E e);

    void delete(E e);

    //List<E> getBy(Predicate<? super Object> predicate);
}
