package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.PuntoConsegnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisualizzaPuntiConsegnaHandler {

    @Autowired
    PuntoConsegnaRepository puntoConsegnaRepository;

    public List<PuntoConsegna> getPuntiConsegna() {
        List<PuntoConsegna> puntiConsegna = new ArrayList<>();
        puntoConsegnaRepository.findAll().forEach(puntiConsegna::add);
        return puntiConsegna;
    }
}
