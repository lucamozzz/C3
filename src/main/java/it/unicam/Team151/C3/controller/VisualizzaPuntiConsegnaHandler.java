package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisualizzaPuntiConsegnaHandler {

    @Autowired
    IRepositoryMaster repositoryMaster;
    @Autowired
    ILoginChecker loginChecker;

    public List<PuntoConsegna> getPuntiConsegna(Long id) {
        if (id != -1)
            loginChecker.checkCliente(id);
        List<PuntoConsegna> puntiConsegna = new ArrayList<>();
        repositoryMaster.getPuntoConsegnaRepository().findAll().forEach(puntiConsegna::add);
        for (PuntoConsegna puntoConsegna : puntiConsegna)
            puntoConsegna.getArmadietti().addAll(repositoryMaster.getArmadiettoRepository().findAllByPuntoConsegna(puntoConsegna));
        return puntiConsegna;
    }
}
