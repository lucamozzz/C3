package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrelloTest {
    Cliente rossi;
    Carrello carrelloRossi;
    Categoria categoria1;
    Categoria categoria2;
    Commerciante verdi;
    PuntoVendita puntoVendita;
    DescrizioneArticolo descrizioneArticolo1;
    DescrizioneArticolo descrizioneArticolo2;
    ArticoloCarrello articoloCarrello1;
    ArticoloCarrello articoloCarrello2;

    @BeforeEach
    void init(){
        rossi= new Cliente("Claudio","Rossi","Via Sonnino 17", "Cliente","claudioRossi@gmail.com", "termosifone");
        verdi= new Commerciante("Tommaso","Verdi","Viale Europa 104", "Commerciante","tommyVerdi@gmail.com", "scatola");
        carrelloRossi= new Carrello(rossi);
        categoria1= new Categoria("Ferramenta","Oggetti che si trovano in una ferramenta");
        categoria2= new Categoria("Elettronica","Oggettini elettronici");
        puntoVendita= new PuntoVendita(verdi,"Da tommaso", "Via nazareth 33");
        descrizioneArticolo1= new DescrizioneArticolo("Cacciavite a stella","cacciavite",46.0, 3,puntoVendita,categoria1);
        descrizioneArticolo2= new DescrizioneArticolo("Laptop Samsung","Samsung Galaxy Laptop 4", 450.0,10,puntoVendita,categoria2);
        articoloCarrello1= new ArticoloCarrello(descrizioneArticolo2,2,carrelloRossi);
        articoloCarrello2= new ArticoloCarrello(descrizioneArticolo1,3,carrelloRossi);
        carrelloRossi.getArticoliCarrello().add(articoloCarrello1);
    }

    @Test
    @Order(2)
    void getTotale() {
        assertEquals(900.0,carrelloRossi.getTotale());
        carrelloRossi.getArticoliCarrello().add(articoloCarrello2);
        assertEquals(1038.0,carrelloRossi.getTotale());
    }

    @Test
    @Order(3)
    void svuota() {
        assertFalse(carrelloRossi.getArticoliCarrello().isEmpty());
        carrelloRossi.svuota();
        assertTrue(carrelloRossi.getArticoliCarrello().isEmpty());
    }

    @Test
    @Order(2)
    void getArticoliCarrello() {
        assertEquals(1,carrelloRossi.getArticoliCarrello().size());
        carrelloRossi.getArticoliCarrello().add(articoloCarrello2);
        assertEquals(2,carrelloRossi.getArticoliCarrello().size());
    }

    @Test
    @Order(1)
    void getCliente() {
        assertEquals(rossi,carrelloRossi.getCliente());
    }

    @Test
    @Order(2)
    void createArticoloCarrello() {
        carrelloRossi.getArticoliCarrello().add(carrelloRossi.createArticoloCarrello(descrizioneArticolo2, 1));
        assertEquals(1, carrelloRossi.getArticoliCarrello().get(1).getQuantita());
    }
}