package it.unicam.Team151.C3.utenti;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Cliente.class)
@Entity
public class Cliente implements InterfaceCliente {

    @Id
    @Column(name = "idCliente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String ruolo;
    private String email;
    private String password;
    private boolean logged;

    public Cliente() {
    }

    public Cliente(String nome, String cognome, String indirizzo, String ruolo, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.ruolo = ruolo;
        this.email = email;
        this.password = password;
        this.logged = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    @Override
    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public String getRuolo() {
        return ruolo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean getLogged() {
        return logged;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "Cliente{" +
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