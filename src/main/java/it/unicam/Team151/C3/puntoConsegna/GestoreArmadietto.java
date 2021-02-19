package it.unicam.Team151.C3.puntoConsegna;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestoreArmadietto implements IGestore<Armadietto> {

    @Autowired
    ArmadiettoRepository armadiettoRepository;

    @Override
    public Armadietto get(Long id) {
        if (armadiettoRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessun armadietto trovato.");
        return armadiettoRepository.findById(id).get();
    }

    @Override
    public void save(Armadietto armadietto) {
        armadiettoRepository.save(armadietto);
    }

    @Override
    public void delete(Armadietto armadietto) {
        armadiettoRepository.delete(armadietto);
    }

    public List<Armadietto> getAll(PuntoConsegna puntoConsegna){
        return armadiettoRepository.findAllByPuntoConsegna(puntoConsegna);
    }

    public Armadietto get(int codice){
        if (armadiettoRepository.findByCodice(codice).isEmpty())
            throw new NoSuchElementException("Nessun armadietto trovato.");
        return armadiettoRepository.findByCodice(codice).get();
    }
}
