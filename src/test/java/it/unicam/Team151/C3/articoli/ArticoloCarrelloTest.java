package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArticoloCarrelloTest{

    Cliente rossi;
    Carrello carrelloRossi;
    Categoria categoria1;
    Categoria categoria2;
    Commerciante verdi;
    PuntoVendita puntoVendita;
    DescrizioneArticolo descrizioneArticolo1;
    DescrizioneArticolo descrizioneArticolo2;
    ArticoloCarrello articoloCarrello1;

    @BeforeEach
    void init() {
        rossi = new Cliente("Claudio", "Rossi", "Via Sonnino 17", "Cliente", "claudioRossi@gmail.com", "termosifone");
        verdi = new Commerciante("Tommaso", "Verdi", "Viale Europa 104", "Commerciante", "tommyVerdi@gmail.com", "scatola");
        carrelloRossi = new Carrello(rossi);
        categoria1 = new Categoria("Ferramenta", "Oggetti che si trovano in una ferramenta");
        categoria2 = new Categoria("Elettronica", "Oggettini elettronici");
        puntoVendita = new PuntoVendita(verdi, "Da tommaso", "Via nazareth 33");
        descrizioneArticolo1 = new DescrizioneArticolo("Cacciavite a stella", "cacciavite", 46.0, 3, puntoVendita, categoria1);
        descrizioneArticolo2 = new DescrizioneArticolo("Laptop Samsung", "Samsung Galaxy Laptop 4", 450.0, 10, puntoVendita, categoria2);
        articoloCarrello1 = new ArticoloCarrello(descrizioneArticolo2, 2, carrelloRossi);
    }

    @Test
    void getDescrizioneArticolo() {
        assertEquals(descrizioneArticolo2,articoloCarrello1.getDescrizioneArticolo());
        assertNotEquals(descrizioneArticolo1, articoloCarrello1.getDescrizioneArticolo());
    }

    @Test
    void getQuantita() {
        assertEquals(2, articoloCarrello1.getQuantita());
    }

    @Test
    void getCarrello() {
        assertEquals(carrelloRossi, articoloCarrello1.getCarrello());
    }

    @Test
    void getPrezzo() {
        assertEquals(900, articoloCarrello1.getPrezzo());
    }
}
