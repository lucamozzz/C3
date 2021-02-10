package it.unicam.Team151.C3.exceptions;

public class NotExistingUserException extends Exception{

    public NotExistingUserException() {
        super("L'utente non esiste. Occorre prima registrarsi.");
    }
}
