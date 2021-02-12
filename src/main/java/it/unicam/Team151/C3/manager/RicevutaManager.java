package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.PaccoRepository;
import it.unicam.Team151.C3.repositories.RicevutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RicevutaManager {

    private static RicevutaManager instance = null;

    @Autowired
    RicevutaRepository ricevutaRepository;

    private RicevutaManager() {
    }

    public static RicevutaManager getInstance(){
        if (instance == null)
            instance = new RicevutaManager();
        return instance;
    }

    public Ricevuta createRicevuta(Prenotazione prenotazione){
        return new Ricevuta(prenotazione);
    }

    public void saveRicevuta(Ricevuta ricevuta) {
        ricevutaRepository.save(ricevuta);
    }
}
