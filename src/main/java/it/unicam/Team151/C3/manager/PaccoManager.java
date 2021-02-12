package it.unicam.Team151.C3.manager;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaccoManager {

    private static PaccoManager instance = null;

    @Autowired
    PaccoRepository paccoRepository;

    private PaccoManager() {
    }

    public static PaccoManager getInstance(){
        if (instance == null)
            instance = new PaccoManager();
        return instance;
    }

    public Pacco createPacco(Prenotazione prenotazione, List<ArticoloCarrello> articoli){
        return new Pacco(prenotazione, articoli);
    }

    public void savePacco(Pacco pacco) {
        paccoRepository.save(pacco);
    }
}
