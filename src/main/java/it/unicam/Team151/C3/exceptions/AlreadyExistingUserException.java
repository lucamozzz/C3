package it.unicam.Team151.C3.exceptions;

public class AlreadyExistingUserException extends Exception{

    public AlreadyExistingUserException() {
        super("L'utente esiste già.");
    }
}
