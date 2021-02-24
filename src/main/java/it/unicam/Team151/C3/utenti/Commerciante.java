package it.unicam.Team151.C3.utenti;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import javax.persistence.*;

/**
 * Classe che rappresenta un oggetto Commerciante. In C3, esso può gestire gli articoli ed i punti vendita del sistema, e confermare acquisti
 * da parte del cliente.
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Commerciante.class)
@Entity
public class Commerciante implements InterfaceCommerciante {

    @Id
    @Column(name = "idCommerciante")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String ruolo;
    private String email;
    private String password;
    private boolean logged;

    public Commerciante() {
    }

    public Commerciante(String nome, String cognome, String indirizzo, String ruolo, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.ruolo = ruolo;
        this.email = email;
        this.password = password;
        this.logged = false;
    }

    /**
     * Metodo che restituisce l'id associato al commerciante.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Metodo che restituisce il nome associato al commerciante.
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che restituisce il cognome associato al commerciante.
     */
    @Override
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo che restituisce l'indirizzo associato al commerciante.
     */
    @Override
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo che restituisce il ruolo 'Commerciante'.
     */
    @Override
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Metodo che restituisce l'email associata al commerciante.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Metodo che restituisce la password associata al commerciante.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Metodo che imposta il nome associato al commerciante.
     */
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che imposta il cognome associato al commerciante.
     */
    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo che imposta l'indirizzo associato al commerciante.
     */
    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo che imposta il ruolo associato al commerciante.
     */
    @Override
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Metodo che imposta l'email associata al commerciante.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo che imposta la pqssword associata al commerciante.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo che indica se il commerciante sia loggato o meno attualmente in C3.
     */
    @Override
    public boolean getLogged() {
        return logged;
    }

    /**
     * Metodo che cambia lo stato di log del commerciante.
     */
    @Override
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "Commerciante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Metodo che crea un punto vendita associato al commeciante
     */
    @Override
    public PuntoVendita createPuntoVendita(String nome, String ubicazione) {
        return new PuntoVendita(this, nome, ubicazione);
    }

    /**
     * Metodo che crea una descrizione articolo associata ad un punto vendita del commeciante
     */
    @Override
    public DescrizioneArticolo createDescrizioneArticolo(String nome, String descrizione, double prezzo, int quantita, PuntoVendita puntoVendita, Categoria categoria) {
        return new DescrizioneArticolo(nome,descrizione,prezzo,quantita,puntoVendita,categoria);
    }
}