package it.unicam.Team151.C3.puntoVendita;

import it.unicam.Team151.C3.utenti.Commerciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntoVenditaTest {

    Commerciante verdi;
    PuntoVendita puntoVendita1;
    PuntoVendita puntoVendita2;

    @BeforeEach
    void init(){
        verdi= new Commerciante("Tommaso","Verdi","Viale Europa 104", "Commerciante","tommyVerdi@gmail.com", "scatola");
        puntoVendita1= new PuntoVendita(verdi,"Da tommaso", "Via nazareth 33");
        puntoVendita2= new PuntoVendita(verdi, "Da rosa","Via recanati 56");
    }

    @Test
    void getCommerciante() {
        assertEquals(verdi, puntoVendita1.getCommerciante());
        assertEquals(verdi, puntoVendita2.getCommerciante());
    }

    @Test
    void getUbicazione() {
        assertEquals("Via nazareth 33",puntoVendita1.getUbicazione());
        assertNotEquals("Via recanati 56", puntoVendita1.getUbicazione());
        assertEquals("Via recanati 56",puntoVendita2.getUbicazione());
    }

    @Test
    void getNome() {
        assertEquals("Da tommaso",puntoVendita1.getNome());
        assertNotEquals("Da rosa", puntoVendita1.getNome());
        assertEquals("Da rosa",puntoVendita2.getNome());
    }
}