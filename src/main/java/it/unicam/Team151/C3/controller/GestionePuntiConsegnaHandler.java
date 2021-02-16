package it.unicam.Team151.C3.controller;


import it.unicam.Team151.C3.puntoConsegna.GestorePuntoConsegna;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionePuntiConsegnaHandler {

	@Autowired
	GestorePuntoConsegna gestorePuntoConsegna;

	public List<PuntoConsegna> getPuntiConsegna() {
		return gestorePuntoConsegna.getPuntiConsegna();
	}

	public void aggiungiPuntoConsegna(String ubicazione, int numeroArmadietti) {
		gestorePuntoConsegna.createPuntoConsegna(ubicazione, numeroArmadietti);
	}

	public void modificaPuntoConsegna(Long idPuntoConsegna, String ubicazione) {
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		if (!ubicazione.isEmpty())
			puntoConsegna.setUbicazione(ubicazione);
		gestorePuntoConsegna.save(puntoConsegna);
	}

	public void rimuoviPuntoConsegna(Long idPuntoConsegna) {
		PuntoConsegna puntoConsegna = gestorePuntoConsegna.get(idPuntoConsegna);
		gestorePuntoConsegna.delete(puntoConsegna);
	}

	//TODO implementare
	public boolean checkDatiInseriti(String ubicazione, int nArmadietti) {
		return false;
	}
}