package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.Articolo;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.GestoreArticolo;
import it.unicam.Team151.C3.prenotazione.GestorePrenotazione;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.GestorePacco;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneManager {

    @Autowired
    GestorePacco gestorePacco;
    @Autowired
    GestoreArticolo gestoreArticolo;
    @Autowired
    GestorePrenotazione gestorePrenotazione;

    public Prenotazione createPrenotazione(Carrello carrello, PuntoConsegna puntoConsegna) {
        Prenotazione prenotazione = new Prenotazione(carrello, puntoConsegna);
        for (Pacco pacco : prenotazione.getPacchi()){
            gestorePacco.save(pacco);
            for (Articolo articolo : pacco.getArticoli())
                gestoreArticolo.save(articolo);
        }
        gestorePrenotazione.save(prenotazione);
        return prenotazione;
    }
}
