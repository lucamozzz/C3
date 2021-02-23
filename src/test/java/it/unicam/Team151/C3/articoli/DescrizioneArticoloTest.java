package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescrizioneArticoloTest {
    Categoria categoria1;
    Categoria categoria2;
    Commerciante verdi;
    PuntoVendita puntoVendita;
    DescrizioneArticolo descrizioneArticolo1;
    DescrizioneArticolo descrizioneArticolo2;

    @BeforeEach
    void init(){
        verdi= new Commerciante("Tommaso","Verdi","Viale Europa 104", "Commerciante","tommyVerdi@gmail.com", "scatola");
        categoria1= new Categoria("Ferramenta","Oggetti che si trovano in una ferramenta");
        categoria2= new Categoria("Elettronica","Oggettini elettronici");
        puntoVendita= new PuntoVendita(verdi,"Da tommaso", "Via nazareth 33");
        descrizioneArticolo1= new DescrizioneArticolo("Cacciavite a stella","cacciavite",46.0, 3,puntoVendita,categoria1);
        descrizioneArticolo2= new DescrizioneArticolo("Laptop Samsung","Samsung Galaxy Laptop 4", 450.0,10,puntoVendita,categoria2);
    }

    @Test
    void getDescrizione() {
        assertEquals("cacciavite",descrizioneArticolo1.getDescrizione());
        assertNotEquals("Samsung Galaxy Laptop 4", descrizioneArticolo1.getDescrizione());
        assertEquals("Samsung Galaxy Laptop 4", descrizioneArticolo2.getDescrizione());
    }

    @Test
    void getPrezzo() {
        assertEquals(450.0,descrizioneArticolo2.getPrezzo());
        descrizioneArticolo2.setPrezzo(445.0);
        assertEquals(445.0,descrizioneArticolo2.getPrezzo());
    }

    @Test
    void getQuantita() {
        assertEquals(3,descrizioneArticolo1.getQuantita());
        descrizioneArticolo1.setQuantita(5);
        assertEquals(5,descrizioneArticolo1.getQuantita());
    }

    @Test
    void getPuntoVendita() {
        assertEquals(puntoVendita,descrizioneArticolo2.getPuntoVendita());
    }

    @Test
    void getCategoria() {
        assertEquals(categoria1,descrizioneArticolo1.getCategoria());
        assertNotEquals(categoria1,descrizioneArticolo2.getCategoria());
    }
}