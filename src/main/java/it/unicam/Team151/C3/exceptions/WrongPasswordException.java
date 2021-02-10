package it.unicam.Team151.C3.exceptions;

public class WrongPasswordException extends Exception{

    public WrongPasswordException() {
        super("Password errata.");
    }
}
