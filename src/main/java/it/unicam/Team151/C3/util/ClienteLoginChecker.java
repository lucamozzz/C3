package it.unicam.Team151.C3.util;

import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClienteLoginChecker implements ILoginChecker<UtenteAutenticato> {

    @Autowired
    IRepositoryMaster repositoryMaster;

    @Override
    public InterfaceCliente check(Long id) {
        if (repositoryMaster.getClienteRepository().findById(id).isEmpty())
            throw new NoSuchElementException("Nessun cliente trovato.");
        InterfaceCliente cliente = repositoryMaster.getClienteRepository().findById(id).get();
        if (!cliente.getLogged())
            throw new IllegalStateException("Devi effettuare il login.");
        return cliente;
    }
}
