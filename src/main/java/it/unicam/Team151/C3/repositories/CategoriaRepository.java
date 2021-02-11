package it.unicam.Team151.C3.repositories;

import it.unicam.Team151.C3.articoli.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
}
