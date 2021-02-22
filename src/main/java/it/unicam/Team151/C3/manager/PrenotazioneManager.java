package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.ArticoloRepository;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneManager {

    @Autowired
    PaccoRepository paccoRepository;
    @Autowired
    ArticoloRepository articoloRepository;
    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
        Prenotazione prenotazione = new Prenotazione(carrello, puntoConsegna);
        for (Pacco pacco : prenotazione.getPacchi()){
            paccoRepository.save(pacco);
            for (Articolo articolo : pacco.getArticoli())
                articoloRepository.save(articolo);
        }
        prenotazioneRepository.save(prenotazione);
        return prenotazione;
    }
}
