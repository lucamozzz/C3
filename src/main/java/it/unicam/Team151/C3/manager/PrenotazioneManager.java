package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.ArticoloRepository;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe con la unica responsabilita di essere il creator di un oggetto Prenotazione
 */
@Service
public class PrenotazioneManager {

    @Autowired
    IRepositoryMaster repositoryMaster;

    /**
     * Metodo che crea una prenotazione, dato un carrello ed un punto consegna e che salva poi anche tutti i pacchi
     * creati dalla classe Prenotazione e tutti gli articoli creati da ogni istanza della classe Pacco
     */
    public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
        Prenotazione prenotazione = new Prenotazione(carrello, puntoConsegna);
        for (Pacco pacco : prenotazione.getPacchi()){
            repositoryMaster.getPaccoRepository().save(pacco);
            for (Articolo articolo : pacco.getArticoli())
                repositoryMaster.getArticoloRepository().save(articolo);
        }
        repositoryMaster.getPrenotazioneRepository().save(prenotazione);
        return prenotazione;
    }
}
