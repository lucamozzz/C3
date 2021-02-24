package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Classe che rappresenta il caso d'uso 'Visualizza Prenotazioni'
 */
@Service
public class VisualizzaPrenotazioniHandler {

    @Autowired
    IRepositoryMaster repositoryMaster;
    @Autowired
    ILoginChecker loginChecker;

    /**
     * Metodo che restituisce tutte le prenotazioni associate al corriere
     */
    public List<Prenotazione> getPrenotazioni(Long idCorriere) {
        Corriere corriere = loginChecker.checkCorriere(idCorriere);
        List<Prenotazione> prenotazioni = repositoryMaster.getPrenotazioneRepository().findAllByCorriere(corriere);
        for (Prenotazione prenotazione : prenotazioni) {
            prenotazione.getPacchi().addAll(repositoryMaster.getPaccoRepository().findAllByPrenotazione(prenotazione));
            for (Pacco pacco : prenotazione.getPacchi()) {
                pacco.getArticoli().addAll(repositoryMaster.getArticoloRepository().findAllByPacco(pacco));
            }
        }
        return prenotazioni;
    }

}
