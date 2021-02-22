package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.IRepositoryMaster;
import it.unicam.Team151.C3.utenti.*;
import it.unicam.Team151.C3.util.ILoginChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class ConfermaAcquistoHandler {

	@Autowired
	IRepositoryMaster repositoryMaster;
	@Autowired
	ILoginChecker loginChecker;

	public List<Pacco> confermaAcquisto(Long idCommerciante) {
		Commerciante commerciante = loginChecker.checkCommerciante(idCommerciante);
		List<PuntoVendita> puntiVenditaCommerciante = repositoryMaster.getPuntoVenditaRepository().findAllByCommerciante(commerciante);
		List<Pacco> pacchiCommerciante = new ArrayList<>();
		for (PuntoVendita puntoVendita : puntiVenditaCommerciante)
			pacchiCommerciante.addAll(getAllPacchiOf(puntoVendita));
		return pacchiCommerciante;
	}

	public void confermaPagamento(Long idCommerciante, Long idPacco) {
		loginChecker.checkCommerciante(idCommerciante);
		boolean flag = true;
		if (repositoryMaster.getPaccoRepository().findById(idPacco).isEmpty())
			throw new NoSuchElementException("Nessun pacco trovato.");
		Pacco pacco = repositoryMaster.getPaccoRepository().findById(idPacco).get();
		pacco.setStato(Stato.Pronto);
		repositoryMaster.getPaccoRepository().save(pacco);
		Prenotazione prenotazione = this.getPrenotazione(pacco.getPrenotazione().getID());
		for(Pacco p : prenotazione.getPacchi()) {
			if (p.getStato() != Stato.Pronto) {
				flag = false;
				break;
			}
		}
		if(flag)
			this.inConsegna(prenotazione);
	}

	public void assegnaCorriere(Prenotazione prenotazione) {
		List<Long> idCorrieri = new ArrayList<>();
		Random rand = new Random();
		for(Corriere corriere : repositoryMaster.getCorriereRepository().findAll())
			idCorrieri.add(corriere.getId());
		Long randomElement = idCorrieri.get(rand.nextInt(idCorrieri.size()));
		if (repositoryMaster.getCorriereRepository().findById(randomElement).isEmpty())
			throw new NoSuchElementException("Nessun corriere trovato.");
		Corriere corriereDaAssegnare = repositoryMaster.getCorriereRepository().findById(randomElement).get();
		prenotazione.setCorriere(corriereDaAssegnare);
		repositoryMaster.getPrenotazioneRepository().save(prenotazione);
	}

	//*********************************************************************//
	//                 METODI PRIVATI A SCOPO IMPLEMENTATIVO               //
	//*********************************************************************//

	private List<Pacco> getAllPacchiOf(PuntoVendita puntoVendita){
		List<Pacco> pacchi = new ArrayList<>();
		for (Pacco pacco : repositoryMaster.getPaccoRepository().findAllByPuntoVendita(puntoVendita))
			pacchi.add(riempiPacco(pacco));
		return pacchi;
	}

	private List<Pacco> getAllPacchiOf(Prenotazione prenotazione){
		List<Pacco> pacchi = new ArrayList<>();
		for (Pacco pacco : repositoryMaster.getPaccoRepository().findAllByPrenotazione(prenotazione))
			pacchi.add(riempiPacco(pacco));
		return pacchi;
	}

	private Pacco riempiPacco(Pacco pacco) {
		repositoryMaster.getArticoloRepository().findAllByPacco(pacco).forEach(pacco.getArticoli()::add);
		return pacco;
	}

	private Prenotazione getPrenotazione(Long idPrenotazione){
		if (repositoryMaster.getPrenotazioneRepository().findById(idPrenotazione).isEmpty())
			throw new NoSuchElementException("Nessuna prenotazione trovata.");
		Prenotazione prenotazione = repositoryMaster.getPrenotazioneRepository().findById(idPrenotazione).get();
		prenotazione.setRicevuta(this.getRicevutaOf(prenotazione));
		this.getAllPacchiOf(prenotazione).forEach(prenotazione.getPacchi()::add);
		return prenotazione;
	}

	private Ricevuta getRicevutaOf(Prenotazione prenotazione){
		if (repositoryMaster.getRicevutaRepository().findByPrenotazione(prenotazione).isEmpty())
			throw new NoSuchElementException("Nessuna ricevuta trovata.");
		return repositoryMaster.getRicevutaRepository().findByPrenotazione(prenotazione).get();
	}

	private void inConsegna(Prenotazione prenotazione) {
		Ricevuta ricevuta = prenotazione.getRicevuta();
		List<Armadietto> armadiettiOfPuntoConsegna = repositoryMaster.getArmadiettoRepository().findAllByPuntoConsegna(prenotazione.getPuntoConsegna());
		for (Armadietto armadietto : armadiettiOfPuntoConsegna) {
			if (armadietto.isDisponibile()){
				ricevuta.setCodice(armadietto.getCodice());
				armadietto.setDisponibilita(false);
			}
		}
		this.assegnaCorriere(prenotazione);
	}
}