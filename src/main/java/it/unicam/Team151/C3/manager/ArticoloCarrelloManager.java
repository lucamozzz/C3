package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.ArticoloCarrelloRepository;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoloCarrelloManager {

    private static ArticoloCarrelloManager instance;

    @Autowired
    ArticoloCarrelloRepository articoloCarrelloRepository;

    private ArticoloCarrelloManager() {
    }

    public static ArticoloCarrelloManager getInstance(){
        if (instance == null)
            instance = new ArticoloCarrelloManager();
        return instance;
    }

    public ArticoloCarrello createArticoloCarrello(DescrizioneArticolo descrizioneArticolo, int quantita, Carrello carrello){
        return new ArticoloCarrello(descrizioneArticolo, quantita, carrello);
    }

    public void saveArticoloCarrello(ArticoloCarrello articoloCarrello) {
        articoloCarrelloRepository.save(articoloCarrello);
    }
}
