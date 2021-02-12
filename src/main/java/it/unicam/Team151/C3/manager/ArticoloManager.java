package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.repositories.ArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticoloManager implements IManager {

    private static ArticoloManager instance;

    @Autowired
    ArticoloRepository articoloRepository;

    private ArticoloManager() {
    }

    public static ArticoloManager getInstance(){
        if (instance == null)
            instance = new ArticoloManager();
        return instance;
    }

    @Override
    public Articolo create(DescrizioneArticolo descrizioneArticolo){
        return new Articolo(descrizioneArticolo);
    }

    @Override
    public void save(Articolo articolo) {
        articoloRepository.save(articolo);
    }
}
