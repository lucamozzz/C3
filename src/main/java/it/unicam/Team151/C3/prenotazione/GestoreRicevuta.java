package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.manager.IGestore;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.repositories.RicevutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GestoreRicevuta implements IGestore<Ricevuta> {

    @Autowired
    RicevutaRepository ricevutaRepository;

    @Override
    public Ricevuta get(Long id) {
        if (ricevutaRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Nessuna ricevuta trovata.");
        return ricevutaRepository.findById(id).get();
    }

    @Override
    public void save(Ricevuta ricevuta) {
        ricevutaRepository.save(ricevuta);
    }

    @Override
    public void delete(Ricevuta ricevuta) {
        ricevutaRepository.delete(ricevuta);
    }

    public Ricevuta getBy(Prenotazione prenotazione){
        if (ricevutaRepository.findByPrenotazione(prenotazione).isEmpty())
            throw new NoSuchElementException("Nessuna ricevuta trovata.");
        return ricevutaRepository.findByPrenotazione(prenotazione).get();
    }
}
