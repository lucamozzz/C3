package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.Corriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VisualizzaPrenotazioniHandler {

    @Autowired
    IRepositoryMaster repositoryMaster;

    public List<Prenotazione> getPrenotazioni(Long idCorriere) {
        if (repositoryMaster.getCorriereRepository().findById(idCorriere).isEmpty())
            throw new NoSuchElementException("Nessun corriere trovato.");
        Corriere corriere = repositoryMaster.getCorriereRepository().findById(idCorriere).get();
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
