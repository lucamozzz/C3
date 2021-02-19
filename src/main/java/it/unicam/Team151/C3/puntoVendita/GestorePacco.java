package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.articoli.GestoreArticolo;
import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestorePacco implements IGestore<Pacco> {

    @Autowired
    PaccoRepository paccoRepository;
    @Autowired
    GestoreArticolo gestoreArticolo;

    @Override
    public Pacco get(Long id) {
        if (paccoRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessun pacco trovato.");
        Pacco pacco = paccoRepository.findById(id).get();
        return riempi(pacco);
    }

    @Override
    public void save(Pacco pacco) {
        paccoRepository.save(pacco);
    }

    @Override
    public void delete(Pacco pacco) {
        paccoRepository.delete(pacco);
    }

    public List<Pacco> getAll(Prenotazione prenotazione){
        List<Pacco> pacchi = new ArrayList<>();
        for (Pacco pacco : paccoRepository.findAllByPrenotazione(prenotazione))
            pacchi.add(riempi(pacco));
        return pacchi;
    }

    public List<Pacco> getAll(PuntoVendita puntoVendita){
        List<Pacco> pacchi = new ArrayList<>();
        for (Pacco pacco : paccoRepository.findAllByPuntoVendita(puntoVendita))
            pacchi.add(riempi(pacco));
        return pacchi;
    }

    private Pacco riempi(Pacco pacco) {
        gestoreArticolo.getArticoli(pacco).forEach(pacco.getArticoli()::add);
        return pacco;
    }
}
