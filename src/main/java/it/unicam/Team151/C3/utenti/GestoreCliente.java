package it.unicam.Team151.C3.utenti;


import it.unicam.Team151.C3.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GestoreCliente extends GestoreUtenti {

    private static GestoreCliente instance;

    @Autowired
    ClienteRepository clienteRepository;

    private GestoreCliente() {
    }

    public static GestoreCliente getInstance() {
        if (instance == null)
            instance = new GestoreCliente();
        return instance;
    }

    public Cliente getCliente(Long idCliente){
        if (clienteRepository.findById(idCliente).isEmpty())
            throw new NoSuchElementException("Nessun cliente trovato.");
        else return clienteRepository.findById(idCliente).get();
    }
}