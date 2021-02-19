package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.prenotazione.GestorePrenotazione;
import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.Ricevuta;
import it.unicam.Team151.C3.prenotazione.Stato;
import it.unicam.Team151.C3.prenotazione.Armadietto;
import it.unicam.Team151.C3.puntoConsegna.GestoreArmadietto;
import it.unicam.Team151.C3.puntoVendita.GestorePacco;
import it.unicam.Team151.C3.puntoVendita.GestorePuntoVendita;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.GestoreCorriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ConfermaAcquistoHandler {

	@Autowired
	GestorePrenotazione gestorePrenotazione;
	@Autowired
	GestoreCorriere gestoreCorriere;
	@Autowired
	GestorePacco gestorePacco;
	@Autowired
	GestorePuntoVendita gestorePuntoVendita;
	@Autowired
	GestoreArmadietto gestoreArmadietto;

	public List<Pacco> confermaAcquisto(Long idCommerciante) {
		List<PuntoVendita> puntiVendita = gestorePuntoVendita.getPuntiVendita(idCommerciante);
		List<Pacco> pacchi = new ArrayList<>();
		for (PuntoVendita puntoVendita : puntiVendita)
			pacchi.addAll(gestorePacco.getAll(puntoVendita));
		return pacchi;
	}

	public void confermaPagamento(Long idPacco) {
		boolean flag = true;
		Pacco pacco = gestorePacco.get(idPacco);
		pacco.setStato(Stato.Pronto);
		gestorePacco.save(pacco);
		Prenotazione prenotazione = gestorePrenotazione.get(pacco.getPrenotazione().getID());
		for(Pacco p : prenotazione.getPacchi()) {
			if (p.getStato() != Stato.Pronto) {
				flag = false;
				break;
			}
		}
		if(flag)
			this.inConsegna(prenotazione);
	}

	private void inConsegna(Prenotazione prenotazione) {
		Ricevuta ricevuta = prenotazione.getRicevuta();
		List<Armadietto> armadietti = gestoreArmadietto.getAll(prenotazione.getPuntoConsegna());
		for (Armadietto armadietto : armadietti) {
			if (armadietto.isDisponibile()){
				ricevuta.setCodice(armadietto.getCodice());
				armadietto.setDisponibilita(false);
			}
		}
		this.assegnaCorriere(prenotazione);
	}

	public void assegnaCorriere(Prenotazione prenotazione) {
		List<Long> idCorrieri = new ArrayList<>();
		Random rand = new Random();
		for(Corriere corriere : gestoreCorriere.getAll())
			idCorrieri.add(corriere.getId());
		Long randomElement = idCorrieri.get(rand.nextInt(idCorrieri.size()));
		Corriere corriereDaAssegnare = gestoreCorriere.get(randomElement);
		prenotazione.setCorriere(corriereDaAssegnare);
		gestorePrenotazione.save(prenotazione);
	}
}