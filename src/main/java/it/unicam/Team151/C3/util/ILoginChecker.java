package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.utenti.*;

public interface ILoginChecker {

    Cliente checkCliente(Long id);

    Commerciante checkCommerciante(Long id);

    Corriere checkCorriere(Long id);
}
