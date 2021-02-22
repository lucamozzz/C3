package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class LoginChecker implements ILoginChecker{

    @Autowired
    IRepositoryMaster repositoryMaster;

    @Override
    public Cliente checkCliente(Long id) {
        if (repositoryMaster.getClienteRepository().findById(id).isEmpty())
            throw new NoSuchElementException("Nessun cliente trovato.");
        Cliente cliente = repositoryMaster.getClienteRepository().findById(id).get();
        if (!cliente.getLogged())
            throw new IllegalStateException("Devi effettuare il login.");
        return cliente;
    }

    @Override
    public Commerciante checkCommerciante(Long id) {
        if (repositoryMaster.getCommercianteRepository().findById(id).isEmpty())
            throw new NoSuchElementException("Nessun commerciante trovato.");
        Commerciante commerciante = repositoryMaster.getCommercianteRepository().findById(id).get();
        if (!commerciante.getLogged())
            throw new IllegalStateException("Devi effettuare il login.");
        return commerciante;
    }

    @Override
    public Corriere checkCorriere(Long id) {
        if (repositoryMaster.getCorriereRepository().findById(id).isEmpty())
            throw new NoSuchElementException("Nessun corriere trovato.");
        Corriere corriere = repositoryMaster.getCorriereRepository().findById(id).get();
        if (!corriere.getLogged())
            throw new IllegalStateException("Devi effettuare il login.");
        return corriere;
    }
}
