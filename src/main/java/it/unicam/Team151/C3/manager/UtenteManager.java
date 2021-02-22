package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteManager {

	private static UtenteManager instance;

	@Autowired
	CarrelloManager carrelloManager;

	private UtenteManager(){}

	public static UtenteManager getInstance(){
		if (instance == null)
			instance = new UtenteManager();
		return instance;
	}

	public UtenteAutenticato createUtente(String nome, String cognome, String indirizzo, String ruolo, String email, String password) {
		UtenteAutenticato newUser;
		switch (ruolo) {
			case "Cliente":
				newUser = new Cliente(nome, cognome, indirizzo, ruolo, email, password);
				carrelloManager.createCarrello((Cliente) newUser);
				break;
			case "Commerciante":
				newUser = new Commerciante(nome, cognome, indirizzo, ruolo, email, password);
				break;
			case "Corriere":
				newUser = new Corriere(nome, cognome, indirizzo, ruolo, email, password);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + ruolo);
		}
		return newUser;
	}
}