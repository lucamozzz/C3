package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.utenti.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntoConsegnaTest {

    Cliente cliente;
    Carrello carrello;
    Prenotazione prenotazione;
    PuntoConsegna puntoConsegna;
    Ricevuta ricevuta;


    @BeforeEach
    void init(){
        cliente=new Cliente("Yuri","Chechi","Via Parma 12", "Cliente","yuri@gmail.com","anello");
        carrello=new Carrello(cliente);
        puntoConsegna= new PuntoConsegna("Le mosse",7);
        prenotazione= new Prenotazione(carrello,puntoConsegna);
        prenotazione.createRicevuta();
        ricevuta= prenotazione.getRicevuta();
    }

    @Test
    void checkCodice() {
        Armadietto armadietto= puntoConsegna.getArmadietti().get(5);
        ricevuta.setCodice(armadietto.getCodice());
        Armadietto armadietto1= puntoConsegna.checkCodice(ricevuta.getCodice());
        assertEquals(armadietto1, armadietto);
    }

    @Test
    void getUbicazione() {
        assertEquals("Le mosse", puntoConsegna.getUbicazione());
    }

    @Test
    void getArmadietti() {
        assertFalse(puntoConsegna.getArmadietti().isEmpty());
        assertEquals(7,puntoConsegna.getArmadietti().size());
    }

    @Test
    void liberaArmadietto() {
        Armadietto armadietto= puntoConsegna.getArmadietti().get(5);
        armadietto.setDisponibilita(false);
        armadietto.setPrenotazione(prenotazione);
        puntoConsegna.liberaArmadietto(armadietto);
        assertTrue(armadietto.isDisponibile());
        assertNull(armadietto.getPrenotazione());
    }
}