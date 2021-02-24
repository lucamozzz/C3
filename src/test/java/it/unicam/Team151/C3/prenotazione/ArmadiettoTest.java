package it.unicam.Team151.C3.prenotazione;

import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.utenti.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmadiettoTest {

    Cliente cliente;
    Carrello carrello;
    Prenotazione prenotazione;
    PuntoConsegna puntoConsegna;
    Armadietto armadietto;
    Armadietto armadietto1;

    @BeforeEach
    void init(){
        cliente=new Cliente("Yuri","Chechi","Via Parma 12", "Cliente","yuri@gmail.com","anello");
        carrello=new Carrello(cliente);
        puntoConsegna= new PuntoConsegna("Le mosse",7);
        prenotazione= new Prenotazione(carrello,puntoConsegna);
        prenotazione.createRicevuta();
        armadietto= puntoConsegna.getArmadietti().get(0);
        armadietto1= puntoConsegna.getArmadietti().get(1);
    }

    @Test
    @Order(4)
    void svuota() {
        assertNull(armadietto.getPrenotazione());
        armadietto.setPrenotazione(prenotazione);
        armadietto.svuota();
        assertNull(armadietto.getPrenotazione());
    }

    @Test
    @Order(1)
    void getPuntoConsegna() {
        assertEquals(puntoConsegna, armadietto.getPuntoConsegna());
        assertEquals(puntoConsegna, armadietto1.getPuntoConsegna());
    }

    @Test
    @Order(2)
    void isDisponibile() {
        assertTrue(armadietto.isDisponibile());
        armadietto.setDisponibilita(false);
        assertFalse(armadietto.isDisponibile());
    }

    @Test
    @Order(3)
    void resetCodice() {
        assertNotNull(armadietto.getCodice());
        int codice= armadietto.getCodice();
        armadietto.resetCodice();
        assertNotEquals(codice, armadietto.getCodice());
    }
}