package it.unicam.Team151.C3.manager;

import it.unicam.Team151.C3.articoli.GestoreCarrello;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import it.unicam.Team151.C3.utenti.Corriere;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;

import java.util.List;

public class UtenteManager {

	private static UtenteManager instance = null;

	private UtenteManager(){}

	public static UtenteManager getInstance(){
		if (instance == null)
			instance = new UtenteManager();
		return instance;
	}

	public UtenteAutenticato createUtente(List<String> form) {
		UtenteAutenticato newUser;
		switch (form.get(3)) {
			case "Cliente":
				newUser = new Cliente(form);
				GestoreCarrello.getInstance().createCarrello((Cliente) newUser);
				break;
			case "Commerciante":
				newUser = new Commerciante(form);
				break;
			case "Corriere":
				newUser = new Corriere(form);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + form.get(3));
		}
		return newUser;
	}
}