package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisualizzaPuntiConsegnaHandler {

    @Autowired
    PuntoConsegnaRepository puntoConsegnaRepository;
    @Autowired
    IRepositoryMaster repositoryMaster;
    @Autowired
    ILoginChecker<Cliente> loginChecker;

    public List<PuntoConsegna> getPuntiConsegna(Long id) {
        if (id != -1){
            loginChecker.check(id);
        }
        List<PuntoConsegna> puntiConsegna = new ArrayList<>();
        puntoConsegnaRepository.findAll().forEach(puntiConsegna::add);
        return puntiConsegna;
    }
}
