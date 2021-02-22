package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.utenti.UtenteAutenticato;

public interface ILoginChecker<E extends UtenteAutenticato> {

    E check(Long id);
}
