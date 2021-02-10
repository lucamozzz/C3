package it.unicam.Team151.C3.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteRepository {

    private static UtenteRepository instance = null;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CommercianteRepository commercianteRepository;
    @Autowired
    private CorriereRepository corriereRepository;

    private UtenteRepository() {}

    public static UtenteRepository getInstance(){
        if (instance == null)
            instance = new UtenteRepository();
        return instance;
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public CommercianteRepository getCommercianteRepository() {
        return commercianteRepository;
    }

    public CorriereRepository getCorriereRepository() {
        return corriereRepository;
    }
}