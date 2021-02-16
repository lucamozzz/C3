package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.puntoConsegna.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.repositories.ArmadiettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ArmadiettoManager implements IManager<Armadietto> {

    @Autowired
    ArmadiettoRepository armadiettoRepository;

    private static ArmadiettoManager instance = null;

    private ArmadiettoManager() {
    }

    public static ArmadiettoManager getInstance(){
        if (instance == null)
            instance = new ArmadiettoManager();
        return instance;
    }

    public Armadietto create(PuntoConsegna puntoConsegna){
        Armadietto armadietto = new Armadietto(puntoConsegna);
        armadiettoRepository.save(armadietto);
        return armadietto;
    }

    @Override
    public Armadietto get(Long idArmadietto) {
        if (armadiettoRepository.findById(idArmadietto).isEmpty())
            throw new NoSuchElementException("Nessun armadietto trovato.");
        return armadiettoRepository.findById(idArmadietto).get();
    }

    @Override
    public void save(Armadietto armadietto) {
        armadiettoRepository.save(armadietto);
    }

    @Override
    public void delete(Armadietto armadietto) {
        armadiettoRepository.delete(armadietto);
    }
}
