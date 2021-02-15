package it.unicam.Team151.C3.controller;

import it.unicam.Team151.C3.articoli.*;
import it.unicam.Team151.C3.puntoVendita.GestorePuntoVendita;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RicercaArticoliHandler {

	@Autowired
	CatalogoArticoli catalogoArticoli;
	@Autowired
	GestorePuntoVendita gestorePuntoVendita;

	public List<Categoria> ricercaArticoliCategoria() {
		return catalogoArticoli.getCategorie();
	}


	public List<DescrizioneArticolo> scegliCategoria(Long idCategoria) {
		return catalogoArticoli.getArticoliPerCategoria(idCategoria);
	}

	public List<PuntoVendita> ricercaArticoliPuntoVendita() {
		return gestorePuntoVendita.getPuntiVendita();
	}

	public void scegliPuntoVendita(Long idPuntoVendita) {
		List<DescrizioneArticolo> descrizioneArticoli = new ArrayList<>();
		PuntoVendita puntoVenditaScelto = gestorePuntoVendita.get(idPuntoVendita);
		descrizioneArticoli.addAll(catalogoArticoli.getArticoliPerPuntoVendita(puntoVenditaScelto.getId()));
	}

}