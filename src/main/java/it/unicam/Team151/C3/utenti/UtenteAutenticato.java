package it.unicam.Team151.C3.utenti;

/**
 * Interfaccia che generalizza tutti gli autenticati a C3, fornendo loro metodi prestabiliti.
 */
public interface UtenteAutenticato {

     Long getId();

     String getNome();

     String getCognome();

     String getIndirizzo();

     String getRuolo();

     String getEmail();

     String getPassword();

     void setNome(String nome);

     void setCognome(String cognome);

     void setIndirizzo(String indirizzo);

     void setRuolo(String ruolo);

     void setEmail(String email);

     void setPassword(String password);

     boolean getLogged();

     void setLogged(boolean logged);
}
