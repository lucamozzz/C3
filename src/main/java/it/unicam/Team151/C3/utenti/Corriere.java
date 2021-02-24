package it.unicam.Team151.C3.utenti;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

/**
 * Classe che rappresenta un oggetto Corriere. In C3, esso può prelevare prenotazioni dai punti vendita e consegnale al punto consegna.
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Cliente.class)
@Entity
public class Corriere implements InterfaceCorriere {

    @Id
    @Column(name = "idCorriere")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String ruolo;
    private String email;
    private String password;
    private boolean logged;

    public Corriere() {
    }

    public Corriere(String nome, String cognome, String indirizzo, String ruolo, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.ruolo = ruolo;
        this.email = email;
        this.password = password;
        this.logged = false;
    }

    /**
     * Metodo che restituisce l'id associato al corriere.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Metodo che restituisce il nome associato al corriere.
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che restituisce il cognome associato al corriere.
     */
    @Override
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo che restituisce l'indirizzo associato al corriere.
     */
    @Override
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo che restituisce il ruolo 'Corriere'.
     */
    @Override
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Metodo che restituisce l'email associata al corriere.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Metodo che restituisce la password associata al corriere.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Metodo che imposta il nome associato al corriere.
     */
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che imposta il cognome associato al corriere.
     */
    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo che imposta l'indirizzo associato al corriere.
     */
    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo che imposta il ruolo associato al corriere.
     */
    @Override
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Metodo che imposta l'email associata al corriere.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo che imposta la password associata al corriere.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo che indica se il corriere sia loggato o meno attualmente in C3.
     */
    @Override
    public boolean getLogged() {
        return logged;
    }

    /**
     * Metodo che cambia lo stato di log del corriere.
     */
    @Override
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "Corriere{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}