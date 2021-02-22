package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CorriereLoginChecker implements ILoginChecker<UtenteAutenticato> {

    @Autowired
    IRepositoryMaster repositoryMaster;

    @Override
    public InterfaceCorriere check(Long id) {
        if (repositoryMaster.getCorriereRepository().findById(id).isEmpty())
            throw new NoSuchElementException("Nessun corriere trovato.");
        Corriere corriere = repositoryMaster.getCorriereRepository().findById(id).get();
        if (!corriere.getLogged())
            throw new IllegalStateException("Devi effettuare il login.");
        return corriere;
    }
}
