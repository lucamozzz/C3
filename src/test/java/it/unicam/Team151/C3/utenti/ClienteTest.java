package it.unicam.Team151.C3.utenti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    InterfaceCliente rossi;
    InterfaceCliente verdi;
    InterfaceCliente neri;

    @BeforeEach
    void init(){
        rossi= new Cliente("Claudio","Rossi","Via Sonnino 17", "Cliente","claudioRossi@gmail.com", "termosifone");
        verdi= new Cliente("Tommaso","Verdi","Viale Europa 104", "Cliente","tommyVerdi@gmail.com", "scatola");
        neri= new Cliente("Raimondo","Neri","P.zza s.Stefano 23", "Cliente","rayBlacks@gmail.com", "repository");
    }

    @Test
    void getNome() {
        assertEquals("Claudio", rossi.getNome());
        assertEquals("Tommaso", verdi.getNome());
        assertEquals("Raimondo", neri.getNome());
    }

    @Test
    void getCognome() {
        assertEquals("Rossi", rossi.getCognome());
        assertEquals("Verdi", verdi.getCognome());
        assertEquals("Neri", neri.getCognome());
    }

    @Test
    void getIndirizzo() {
        assertEquals("Via Sonnino 17", rossi.getIndirizzo());
        assertEquals("Viale Europa 104", verdi.getIndirizzo());
        assertEquals("P.zza s.Stefano 23", neri.getIndirizzo());
    }

    @Test
    void getEmail() {
        assertEquals("claudioRossi@gmail.com", rossi.getEmail());
        assertEquals("tommyVerdi@gmail.com", verdi.getEmail());
        assertEquals("rayBlacks@gmail.com", neri.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("termosifone", rossi.getPassword());
        assertEquals("scatola", verdi.getPassword());
        assertEquals("repository", neri.getPassword());
    }
}