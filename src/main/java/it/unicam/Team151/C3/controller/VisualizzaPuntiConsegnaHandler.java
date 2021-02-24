package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.util.ILoginChecker;
import it.unicam.Team151.C3.util.InterfaceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta il caso d'uso 'Visualizza Punti Consegna'
 */
@Service
public class VisualizzaPuntiConsegnaHandler {

    @Autowired
    IRepositoryMaster repositoryMaster;
    @Autowired
    ILoginChecker loginChecker;
    @Autowired
    InterfaceAdmin admin;

    /**
     * Metodo che restituisce tutti i punti consegna presenti in C3
     */
    public List<PuntoConsegna> getPuntiConsegna(Long id) {
        if (id == -1 && admin.getLogged() || loginChecker.checkCliente(id) != null) {
            List<PuntoConsegna> puntiConsegna = new ArrayList<>();
            repositoryMaster.getPuntoConsegnaRepository().findAll().forEach(puntiConsegna::add);
            for (PuntoConsegna puntoConsegna : puntiConsegna)
                puntoConsegna.getArmadietti().addAll(repositoryMaster.getArmadiettoRepository().findAllByPuntoConsegna(puntoConsegna));
            return puntiConsegna;
        }
        return null;
    }
}
