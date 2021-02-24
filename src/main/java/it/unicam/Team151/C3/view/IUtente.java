package it.unicam.Team151.C3.view;

import it.unicam.Team151.C3.controller.LoginHandler;
import it.unicam.Team151.C3.controller.RegistrazioneHandler;
import it.unicam.Team151.C3.exceptions.AlreadyExistingUserException;
import it.unicam.Team151.C3.exceptions.NotExistingUserException;
import it.unicam.Team151.C3.exceptions.WrongPasswordException;
import it.unicam.Team151.C3.utenti.UtenteAutenticato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Classe che ha la responsabilità di controller dell'interfaccia UtenteAutenticato
 */
@RestController
@RequestMapping("utente")
public class IUtente {

	@Autowired
	RegistrazioneHandler registrazioneHandler;

	@Autowired
	LoginHandler loginHandler;

	/**
	 * Metodo che da il via al caso d'uso 'Login'
	 */
	@PostMapping("login")
	public UtenteAutenticato login(@RequestParam String email, @RequestParam String password, @RequestParam String ruolo) throws NotExistingUserException, WrongPasswordException {
		return loginHandler.autenticazione(email, password, ruolo);
	}

	/**
	 * Metodo che da il via al caso d'uso 'Registrazione'
	 */
	@PostMapping("registrazione")
	public void registrazione(@RequestParam String nome,
							  @RequestParam String cognome,
							  @RequestParam String indirizzo,
							  @RequestParam String ruolo,
							  @RequestParam String email,
							  @RequestParam String password) throws AlreadyExistingUserException {
		registrazioneHandler.compilaForm(nome, cognome, indirizzo, ruolo, email, password);
	}
}