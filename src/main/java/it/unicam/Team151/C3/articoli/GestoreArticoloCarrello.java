package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GestoreArticoloCarrello implements IGestore<ArticoloCarrello> {

    @Autowired
    ArticoloCarrelloRepository articoloCarrelloRepository;

    @Override
    public ArticoloCarrello get(Long id) {
        if (articoloCarrelloRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessun articolo carrello trovato.");
        return articoloCarrelloRepository.findById(id).get();
    }

    @Override
    public void save(ArticoloCarrello articoloCarrello) {
        articoloCarrelloRepository.save(articoloCarrello);
    }

    @Override
    public void delete(ArticoloCarrello articoloCarrello) {
        articoloCarrelloRepository.delete(articoloCarrello);
    }

    public ArticoloCarrello get(DescrizioneArticolo descrizioneArticolo){
        return articoloCarrelloRepository.findByDescrizioneArticolo(descrizioneArticolo).get();
    }
}
