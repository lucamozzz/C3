package it.unicam.Team151.C3.manager;

public interface IManager<E> {

    E get(Long id);

    void save(E e);

    void delete(E e);
}
