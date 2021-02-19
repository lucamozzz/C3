package it.unicam.Team151.C3.utenti;


import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GestoreCliente implements IGestore<Cliente> {

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

    @Override
    public Cliente get(Long id) {
        if (clienteRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessun cliente trovato.");
        else return clienteRepository.findById(id).get();
    }

    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}