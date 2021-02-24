package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.controller.ConfermaAcquistoHandler;
import it.unicam.Team151.C3.controller.GestioneArticoliHandler;
import it.unicam.Team151.C3.controller.GestionePuntiVenditaHandler;
import it.unicam.Team151.C3.controller.LogoutHandler;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe che ha la responsabilità di controller dell'oggetto Commerciante
 */
@RestController
@RequestMapping("commerciante")
public class ICommerciante implements IUtenteAutenticato{

	@Autowired
	GestionePuntiVenditaHandler gestionePuntiVenditaHandler;
	@Autowired
	ConfermaAcquistoHandler confermaAcquistoHandler;
	@Autowired
	GestioneArticoliHandler gestioneArticoliHandler;
	@Autowired
	LogoutHandler logoutHandler;

	/**
	 * Metodo che permette al commerciante di visualizzare i pacchi associati ai punti vendita del commerciante
	 */
	@GetMapping("mostraPacchi")
	public List<Pacco> confermaAcquisto(@RequestParam Long idCommerciante) {
		return confermaAcquistoHandler.confermaAcquisto(idCommerciante);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Conferma acquisto'
	 */
	@PostMapping("confermaPagamento")
	public void confermaPagamento(@RequestParam Long idCommerciante, @RequestParam Long idPacco) {
		confermaAcquistoHandler.confermaPagamento(idCommerciante, idPacco);
	}

	/**
	 * Metodo che permette al commerciante di visualizzare le descrizioni articolo associate al commerciante
	 */
	@GetMapping("mostraArticoli")
	public List<DescrizioneArticolo> modificaArticolo(@RequestParam Long idCommerciante) {
		return gestioneArticoliHandler.getArticoliOf(idCommerciante);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Articoli - Aggiungi Articolo'
	 */
	@PostMapping("aggiungiArticolo")
	public DescrizioneArticolo aggiungiArticolo(@RequestParam Long idCommerciante,
												@RequestParam String nome,
												@RequestParam String descrizione,
												@RequestParam double prezzo,
												@RequestParam int quantita,
												@RequestParam Long idPuntoVendita,
												@RequestParam Long idCategoria) {
	return gestioneArticoliHandler.aggiungiArticolo(idCommerciante, nome, descrizione, prezzo, quantita, idPuntoVendita, idCategoria);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Articoli - Modifica Articolo'
	 */
	@PostMapping("modificaArticolo")
	public void inserimentoDatiArticoloDaModificare(@RequestParam Long idCommerciante,
													@RequestParam Long idDescrizioneArticolo,
													@RequestParam String nome,
													@RequestParam String descrizione,
													@RequestParam double prezzo,
													@RequestParam int quantita,
													@RequestParam Categoria categoria) {
		gestioneArticoliHandler.modificaArticolo(idCommerciante, idDescrizioneArticolo, nome, descrizione, prezzo, quantita, categoria);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Articoli - Rimuovi Articolo'
	 */
	@PostMapping("rimuoviArticolo")
	public void rimozioneArticolo(@RequestParam Long idCommerciante, @RequestParam Long idDescArticolo) {
		gestioneArticoliHandler.rimuoviArticolo(idCommerciante, idDescArticolo);
	}

	/**
	 * Metodo che permette al commerciante di visualizzare i punti vendita associati al commerciante
	 */
	@GetMapping("mostraPuntiVendita")
	public List<PuntoVendita> getPuntiVendita(@RequestParam Long idCommerciante){
		return gestionePuntiVenditaHandler.getPuntiVendita(idCommerciante);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Punti Vendita - Aggiungi punto vendita'
	 */
	@PostMapping("aggiungiPuntoVendita")
	public void aggiungiPuntoVendita(@RequestParam Long idCommerciante, @RequestParam String nome, @RequestParam String ubicazione){
		gestionePuntiVenditaHandler.aggiungiPuntoVendita(idCommerciante, nome, ubicazione);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Punti Vendita - Modifica punto vendita'
	 */
	@PostMapping("modificaPuntoVendita")
	public void modificaPuntoVendita(@RequestParam Long idCommerciante, @RequestParam Long idPuntoVendita, @RequestParam String nome, @RequestParam String ubicazione) {
		gestionePuntiVenditaHandler.modificaPuntoVendita(idCommerciante, idPuntoVendita, nome, ubicazione);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Gestione Punti Vendita - Rimuovi punto vendita'
	 */
	@PostMapping("rimuoviPuntoVendita")
	public void rimuoviPuntoVendita(@RequestParam Long idCommerciante, @RequestParam Long idPuntoVendita) {
		gestionePuntiVenditaHandler.rimuoviPuntoVendita(idCommerciante, idPuntoVendita);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Logout'
	 */
	@Override
	@PostMapping("logout")
	public void logout(@RequestParam Long id) {
		logoutHandler.logout(id);
	}
}